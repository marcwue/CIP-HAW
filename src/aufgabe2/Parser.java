package aufgabe2;

import nodes.AbstractNode;
import nodes.AssignmentNode;
import nodes.FactorNode;
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
	public static final int FACTOR = 1;
	public static final int VARIABLE = 2;
	public static final int WHILE = 3;

	public static void compile(String str) {
		System.out.println(str);
	}

	public static void outStr(String str) {
		System.out.print(str + " ");
	}

	public static void outInt(int i) {
		System.out.print(i + " ");
	}

	public static void outOp(String op) {
		System.out.print(op);
	}

	public static void error(String str) {
		System.out.println("Error: " + str);
	}

	public static void inSymbol() {

		try {
			while ((nextsymbol = scanner.yylex()).id() == TokenID.WHITESPACE) {

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
		// while(nextsymbol.id() == TokenID.BEGIN){
		
		// }
		return null;
	}

	private static AbstractNode statementSequence() {

		
		
		simpleExpr();
		return null;
	}

	private static AbstractNode assignment() {

		AbstractNode factor;
		// assignment
		if (nextsymbol.id() == TokenID.ASSIGN) {
			inSymbol();
			factor = factor();
		}// Semicolon
//		else if (nextsymbol.id() == TokenID.SEMICOLON) {
//			inSymbol();
//			factor();
//		}

		//new AssignmentNode();
		return null;
	}
	
	private static void simpleExpr() {

		term();
	}
	
	private static void term() {
		factor();
	}

	private static AbstractNode factor() {
		FactorNode f = null;
		// Klammern
		if (nextsymbol.id() == TokenID.LPAR) {
			inSymbol();
			simpleExpr();
			if (nextsymbol.id() == TokenID.RPAR)
				inSymbol();
			else
				error(" ) expected");

		}
		// integer
		else if (nextsymbol.id() == TokenID.INT) {
			outInt(Integer.parseInt(nextsymbol.text()));
			f = new FactorNode(Integer.parseInt(nextsymbol.text()));
			inSymbol();

		}
		// identifier
		else if (nextsymbol.id() == TokenID.ID) {
			outStr(nextsymbol.text());
			f = new FactorNode(nextsymbol.text());
			inSymbol();
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

					while (nextsymbol.id() != null) {
						System.out.println(nextsymbol);
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
