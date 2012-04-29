package aufgabe2;

import nodes.AbstractNode;
import nodes.AssignmentNode;
import nodes.ExpressionNode;
import nodes.FactorNode;
import nodes.IfNode;
import nodes.TermNode;
import nodes.WhileNode;
import sun.security.pkcs.ParsingException;

/**
 * 
 * @author Marc Wüseke
 * 
 */
public class Parser {
	static MyFlexScanner scanner = null;
	static MyToken nextsymbol;
	static String[] argv;
	static String inFile;

	public static void print(MyToken token) {
		System.out.println(token);
	}

	public void error(String str) {
		System.out.println("Error: " + str);
	}

	public static void inSymbol() {

		try {
			while (((nextsymbol = scanner.yylex()) != null)
					&& (nextsymbol.id() == TokenID.WHITESPACE)) {

			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + inFile + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + inFile + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
	}

	public static AbstractNode program() throws ParsingException {
		AbstractNode res = null;
		while (nextsymbol != null && nextsymbol.id() == TokenID.BEGIN) {
			res = exprSeq();
		}
		return res;
	}

	private static AbstractNode exprSeq() throws ParsingException {
		AbstractNode simpleExprRes = null;
		if (nextsymbol.id() == TokenID.BEGIN) {
			print(nextsymbol);
			inSymbol();
		} else {
			throw new ParsingException("BEGIN expected\n");
		}
		while ((nextsymbol.id() == TokenID.LPAR)
				|| (nextsymbol.id() == TokenID.ID)
				|| (nextsymbol.id() == TokenID.INT)) {
			simpleExprRes = simpleExp();
		}

		// while (nextsymbol.id() == TokenID.WHILE) {
		// simpleExprRes = whileStatement();
		// }

		if (nextsymbol.id() == TokenID.END) {
			print(nextsymbol);
			inSymbol();
		} else {
			throw new ParsingException("END expected\n");
		}
		return simpleExprRes;
	}

	private static AbstractNode assignment(MyToken ident)
			throws ParsingException {

		inSymbol();
		AbstractNode simpleExpr = simpleExp();
		return new AssignmentNode(ident, simpleExpr);
	}

	private static AbstractNode statementSeq() throws ParsingException {

		return simpleExp();
	}
	
	private static AbstractNode expression() throws ParsingException {

		AbstractNode simpleExp1 = null;
		AbstractNode simpleExp2 = null;
		MyToken relop = null;
		
		inSymbol();
		if(nextsymbol.id() == TokenID.EQ
				|| nextsymbol.id() == TokenID.NEQ
				|| nextsymbol.id() == TokenID.LO
				|| nextsymbol.id() == TokenID.LOEQ
				|| nextsymbol.id() == TokenID.HI
				|| nextsymbol.id() == TokenID.HIEQ){
			relop = nextsymbol;
			
			inSymbol();
			
		}
		
		
		
		return new ExpressionNode(simpleExp1, relop, simpleExp2);
	}


	private static AbstractNode simpleExp() throws ParsingException {

		MyToken lsy;
		term();
		if ((nextsymbol.id() == TokenID.PLUS)
				|| (nextsymbol.id() == TokenID.MINUS)) {
			lsy = nextsymbol;
			inSymbol();
			simpleExp();
			if (lsy.id() == TokenID.PLUS)
				print(lsy);
			else
				print(lsy);
		}

		return term();
	}

	
	private static AbstractNode term() throws ParsingException {

		MyToken lsy;
		AbstractNode t = factor();
		if ((nextsymbol.id() == TokenID.MUL)
				|| (nextsymbol.id() == TokenID.DIV)) {
			lsy = nextsymbol;
			inSymbol();
			term();
			if (lsy.id() == TokenID.MUL)
				print(lsy);
			else
				print(lsy);
		}

		return new TermNode(t);
	}

	private static AbstractNode factor() throws ParsingException {
		FactorNode f = null;
		MyToken next;
		// Klammern
		if (nextsymbol.id() == TokenID.LPAR) {
			print(nextsymbol);
			inSymbol();
			simpleExp();
			if (nextsymbol.id() == TokenID.RPAR) {
				print(nextsymbol);
				inSymbol();
			} else {
				throw new ParsingException(" ) expected");
			}
		}
		// integer
		else if (nextsymbol.id() == TokenID.INT) {
			print(nextsymbol);
			f = new FactorNode(Integer.parseInt(nextsymbol.text()));
			inSymbol();

		}
		// identifier
		else if ((next = nextsymbol).id() == TokenID.ID) {
			print(nextsymbol);
			f = new FactorNode(nextsymbol.text());
			inSymbol();
			if (nextsymbol.id() == TokenID.ASSIGN) {
				print(nextsymbol);
				assignment(next);
				if (nextsymbol.id() == TokenID.SEMICOLON) {
					print(nextsymbol);
					inSymbol();
					simpleExp();
				} else {
					throw new ParsingException("SEMICOLON expected\n");
				}
			}
		}

		return f;
	}

	private static IfNode conditionalStatement() throws ParsingException {
		AbstractNode e = null, st1 = null, st2 = null;
		if (nextsymbol.id() == TokenID.IF) {
			inSymbol();
		} else {
			throw new ParsingException("IF expected");
		}
		e = expression();
		if (nextsymbol.id() == TokenID.THEN) {
			inSymbol();
		} else {
			throw new ParsingException("THEN expected");
		}
		st1 = statementSeq();
		if (nextsymbol.id() == TokenID.ELSE) {
			inSymbol();
			st2 = statementSeq();
		}
		if (nextsymbol.id() == TokenID.END) {
			inSymbol();
		} else {
			throw new ParsingException("END expected");
		}
		return new IfNode(e, st1, st2);
	}

	private static ConditionNode condition() {

		if (nextsymbol.id() == TokenID.ID || nextsymbol.id() == TokenID.INT) {

		}

		return new ConditionNode();
	}

	private static WhileNode whileStatement() throws ParsingException {
		AbstractNode e = null, st = null;
		if (nextsymbol.id() == TokenID.WHILE) {
			print(nextsymbol);
			inSymbol();
		} else {
			throw new ParsingException("WHILE expected " + nextsymbol);
		}
		e = exprSeq();
		if (nextsymbol.id() == TokenID.DO) {
			print(nextsymbol);
			inSymbol();
		} else {
			throw new ParsingException("DO expected " + nextsymbol);
		}
		st = statementSeq();
		if (nextsymbol.id() == TokenID.ENDSY) {
			print(nextsymbol);
			inSymbol();
		} else {
			throw new ParsingException("END expected " + nextsymbol);
		}
		return new WhileNode(e, st);
	}

	public static void main(String[] argv) {
		System.out.println("MyStandalone Version 0.1");

		if (argv.length == 0) {
			System.out.println("Usage : java MyStandalone <inputfile>");
		} else {

			for (int i = 0; i < argv.length; i++) {
				try {
					inFile = argv[i];
					scanner = new MyFlexScanner(new java.io.FileReader(inFile));
					inSymbol();

					while (nextsymbol != null) {
						program();
					}
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + inFile + "\"");
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}

		}
	}
	/**
	 * x := Selector "X"; x := integer "X"; x := string "X"; x := read "X";
	 */

}
