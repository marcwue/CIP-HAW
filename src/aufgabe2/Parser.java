package aufgabe2;

import nodes.AbstractNode;
import nodes.AssignmentNode;
import nodes.FactorNode;
import nodes.WhileNode;
import sun.security.pkcs.ParsingException;

/**
 * 
 * @author Marc W�seke
 * 
 */
public class Parser {
	static MyFlexScanner scanner = null;
	static MyToken nextsymbol;
	static String[] argv;
	static String inFile;
	public static final int FACTOR = 1;
	public static final int VARIABLE = 2;
	public static final int WHILE = 3;

	public static void print(MyToken token) {
		System.out.println(token);
	}

	public static void error(String str) {
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

	public static AbstractNode program() {
		AbstractNode exprSeqRes = null;
		while (nextsymbol != null && nextsymbol.id() == TokenID.BEGIN) {
			exprSeqRes = exprSeq();
		}
		return exprSeqRes;
	}

	private static AbstractNode exprSeq() {
		AbstractNode simpleExprRes = null;
		if (nextsymbol.id() == TokenID.BEGIN) {
			print(nextsymbol);
			inSymbol();
		} else {
			error("BEGIN expected\n");
		}
		while ((nextsymbol.id() == TokenID.LPAR)
				|| (nextsymbol.id() == TokenID.ID)
				|| (nextsymbol.id() == TokenID.INT)) {
			simpleExprRes = simpleExpr();
		}
		if (nextsymbol.id() == TokenID.END) {
			print(nextsymbol);
			inSymbol();
		} else {
			error("END expected\n");
		}
		return simpleExprRes;
	}

	private static AbstractNode assignment(MyToken ident) {

		inSymbol();
		AbstractNode simpleExpr = simpleExpr();
		return new AssignmentNode(ident, simpleExpr);
	}

	private static AbstractNode statementSequence() {

		return simpleExpr();
	}

	private static AbstractNode simpleExpr() {

		MyToken lsy;
		term();
		if ((nextsymbol.id() == TokenID.PLUS)
				|| (nextsymbol.id() == TokenID.MINUS)) {
			lsy = nextsymbol;
			inSymbol();
			simpleExpr();
			if (lsy.id() == TokenID.PLUS)
				print(lsy);
			else
				print(lsy);
		}

		return term();
	}

	private static AbstractNode term() {

		MyToken lsy;
		AbstractNode f = factor();
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

		return f;
	}

	private static AbstractNode factor() {
		FactorNode f = null;
		MyToken next;
		// Klammern
		if (nextsymbol.id() == TokenID.LPAR) {
			print(nextsymbol);
			inSymbol();
			simpleExpr();
			if (nextsymbol.id() == TokenID.RPAR) {
				print(nextsymbol);
				inSymbol();
			} else {
				error(" ) expected");
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
					simpleExpr();
				} else {
					error("SEMICOLON expected\n");
				}
			}
		}

		return f;
	}

	private static AbstractNode whileStatement() throws ParsingException {
		AbstractNode e = null, st = null;
		if (nextsymbol.id() == TokenID.WHILE)
			inSymbol();
		else
			throw new ParsingException("WHILE expected " + nextsymbol.line()
					+ nextsymbol.column());
		e = expression();
		if (nextsymbol.id() == TokenID.DO) {
			inSymbol();
		} else {
			throw new ParsingException("DO expected" + nextsymbol.line()
					+ nextsymbol.column());
		}
		st = statementSequence();
		if (nextsymbol.id() == TokenID.ENDSY) {
			inSymbol();
		} else {
			throw new ParsingException("END expected" + nextsymbol.line()
					+ nextsymbol.column());
		}
		return new WhileNode(e, st);
	}

	private static AbstractNode expression() {

		return null;
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
