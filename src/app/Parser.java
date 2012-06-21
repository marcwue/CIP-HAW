package app;

import nodes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static app.TokenID.*;

/**
 * @author Marc Wüseke
 */
public class Parser {
	private MyFlexScanner scanner;
	private MyToken nextSymbol;
	private static String inFile;
	static String spaces = "";

	// private final static String nodeFile = "nodeFile.txt";

	public Parser(MyFlexScanner scanner) {
		this.scanner = scanner;
		inSymbol();
	}

	public static void indent() {
		spaces = spaces + "  ";
	}

	public static void unindent() {
		spaces = spaces.substring(2);
	}
	
	private AbstractNode program() {
		AbstractNode tree = null;

		while (nextSymbol != null) {
			tree = module();
			inSymbol();
		}

		return tree;
	}

	public AbstractNode parse() {
		return program();
	}

	// IdentList = ident {’,’ ident}
	private IdentListNode identList() {
		List<IdentNode> idents = new ArrayList<IdentNode>();
		if (test(ID)) {
			idents.add(constIdent());
			while (test(COMMA)) {
				read(COMMA, ",");
				idents.add(constIdent());
			}
		} else {
			failExpectation("ident");
		}
		return new IdentListNode(idents);
	}

	// ident
	private IdentNode constIdent() {
		IdentNode fu = new IdentNode(read(ID, "identifier").text()+"");
		return fu;
	}

	// ArrayType = = 'ARRAY' '[' IndexExpression ']' 'OF' Type
	private ArrayNode arrayType() {
		read(ARRAY, "ARRAY");
		read(LBRAC, "[");
		AbstractNode node = indexExpr();
		read(RBRAC, "]");
		read(OF, "OF");
		AbstractNode t = type();
		return new ArrayNode(node, t);
	}

	// FieldList = IdentList ’:’ Type]
	private FieldListNode fieldList() {
		if (test(ID)) {
			AbstractNode il = identList();
			read(COLON, ":");
			AbstractNode t = type();
			return new FieldListNode(il, t);
		} else {
			return null;
		}
	}

	// RecordType = ’RECORD’ FieldList {’;’ FieldList} ’END’
	private RecordTypeNode recordType() {
		read(RECORD, "RECORD");
		List<FieldListNode> fieldLists = new ArrayList<FieldListNode>();
		FieldListNode node = fieldList();
		if (node != null)
			fieldLists.add(node);
		while (test(SEMICOLON)) {
			read(SEMICOLON, ";");
			node = fieldList();
			if (node != null)
				fieldLists.add(node);
		}
		read(END, "END");
		return new RecordTypeNode(fieldLists);
	}

	// Type = ident | ArrayType | RecordType
	private AbstractNode type() {
		AbstractNode node = null;
		if (test(ID)) {
			node = constIdent();
		} else if (test(ARRAY)) {
			node = arrayType();
		} else if (test(RECORD)) {
			node = recordType();
		} else {
			failExpectation("type");
		}
		return node;
	}

	// FPSection = [’VAR’] IdentList ’:’ Type.
	private FPSectionNode fpSection() {
		if (test(VAR)) {
			read(VAR, "VAR");
		}
		AbstractNode var = identList();
		read(COLON, ":");
		AbstractNode type = type();
		return new FPSectionNode(var, type);
	}

	// FormalParameters = PSection {’;’ FPSection}
	private FormalParametersNode formalParameters() {
		List<FPSectionNode> fpsections = new ArrayList<FPSectionNode>();
		FPSectionNode fpsection = (FPSectionNode) fpSection();
		fpsections.add(fpsection);
		while (test(SEMICOLON)) {
			read(SEMICOLON, ";");
			fpsection = (FPSectionNode) fpSection();
			fpsections.add(fpsection);
		}
		return new FormalParametersNode(fpsections);

	}

	// ProcedureHeading = 'PROCEDURE' ident ' (' [FormalParameters] ')'
	private ProcedureHeadingNode procedureHeading() {
		read(PROCEDURE, "PROCEDURE");
		IdentNode ident = constIdent();
		read(LPAR, "(");
		FormalParametersNode params = new FormalParametersNode(
				new ArrayList<FPSectionNode>());
		
		if (test(VAR) || test(ID)) {
			params = formalParameters();
		}
		read(RPAR, ")");
		return new ProcedureHeadingNode(ident, params);
	}

	// ProcedureBody = Declarations 'BEGIN' StatementSequence 'END'
	private ProcedureBodyNode procedureBody() {
		DeclarationsNode declarations = (DeclarationsNode) declarations();

		read(BEGIN);

		AbstractNode statementSeqNode = statementSeq();

		read(END);

		IdentNode decIdent = constIdent();
		
		return new ProcedureBodyNode(decIdent, declarations, statementSeqNode);
	}

	// ProcedureDeclaration = ProcedureHeading ’;’
	// ProcedureBody ident
	private ProcedureDeclarationNode procedureDeclaration() {
		ProcedureHeadingNode head = procedureHeading();
		read(SEMICOLON, ";");
		ProcedureBodyNode body = procedureBody();
		return new ProcedureDeclarationNode(head, body);
	}

	private DeclarationsNode declarations() {
		
		List<ConstNode> constList = new LinkedList<ConstNode>();
		List<TypeNode> typeList = new LinkedList<TypeNode>();
		List<VarNode> varListe = new LinkedList<VarNode>();
		List<AbstractNode> procListe = new LinkedList<AbstractNode>();

		if (test(CONST)) {
			read(CONST, "const");

			IdentNode constIdent;
			AbstractNode exp;
			do {
				constIdent = constIdent();
				read(EQ, "=");
				exp = expression();
				read(SEMICOLON, ";");
				constList.add(new ConstNode(constIdent, exp));
			} while (test(ID));

		}
		if (test(TYPE)) {
			read(TYPE, "TYPE");

			IdentNode ident;
			AbstractNode type;
			do {
				ident = constIdent();
				read(EQ, "=");
				type = type();
				read(SEMICOLON, ";");
				typeList.add(new TypeNode(ident, type));
			} while (test(ID));
		}
		if (test(VAR)) {
			read(VAR, "VAR");
			IdentListNode identList;
			AbstractNode type;
			do {
				identList = identList();
				read(COLON, ":");
				type = type();
				read(SEMICOLON, ";");
				varListe.add(new VarNode(identList, type));
			} while (test(ID));

		}
		while (test(PROCEDURE)) {
			ProcedureDeclarationNode pdn = procedureDeclaration();
			procListe.add(pdn);
			read(SEMICOLON, ";");
		}
		return new DeclarationsNode(constList, typeList, varListe, procListe);
	}

	// Module = ’MODULE’ ident ’;’ Declarations
	// ’BEGIN’ StatementSequence
	// ’END’ ident ’
	private ModuleNode module() {
		read(MODULE, "MODULE");
		IdentNode moduleName = constIdent();

		read(SEMICOLON, ";");

		DeclarationsNode declaration = declarations();

		read(BEGIN, "BEGIN");
		AbstractNode stmtseq = statementSeq();

		read(END, "END");

		IdentNode moduleEndName = constIdent();

		if (!moduleName.equals(moduleEndName)) {
			failExpectation("identifiers of module and end are supposed to be the same");
		}
		return new ModuleNode(moduleName, declaration, stmtseq);
	}

	// IndexExpression = integer | ConstIdent
	private AbstractNode indexExpr() {
		AbstractNode node = null;
		if (test(ID)) {
			node = new ContentNode(constIdent());
		} else if (test(INT)) {
			node = integer();
		} else {
			failExpectation("type");
		}
		return node;
	}

	// integer
	private IntNode integer() {
		return new IntNode(Integer.parseInt(read(INT, "integer").text()));
	}

	// string
	private StringNode string() {
		return new StringNode(read(STR, "str").text());
	}

	private ReadNode readParser() {
		read(READ, "READ");
		return test(STR) ? new ReadNode(string()) : new ReadNode();
	}

	// selector
	private SelectorNode selector() {
		IdentNode subject = constIdent();
		SelectorNode node = null;

		if (test(DOT)) {
			read(DOT, ".");
			node = new RecordSelectorNode(subject, constIdent());
		} else if (test(LBRAC)) {
			read(LBRAC, "[");
			node = new ArraySelectorNode(subject, expression());
			read(RBRAC, "]");
		} else {
			failExpectation(". or [");
		}

		while (test(DOT) || test(LBRAC)) {
			if (test(DOT)) {
				read(DOT, ".");
				node = new RecordSelectorNode(node, constIdent());
			} else {
				read(LBRAC, "[");
				node = new ArraySelectorNode(node, expression());
				read(RBRAC, "]");
			}
		}

		return node;
	}

	public static void print(MyToken token) {
		System.out.println(token);
	}

	private AbstractNode assignment() {

		AssignmentNode node;
		AbstractNode expr;

		if (testLookAhead(DOT) || testLookAhead(LBRAC)) {
			SelectorNode selector = selector();
			read(ASSIGN, ":=");
			expr = expression();
			node = new AssignmentNode(selector, expr);
		} else {
			IdentNode selector = constIdent();
			read(ASSIGN, ":=");
			expr = expression();
			node = new AssignmentNode(selector, expr);
		}
		return node;
	}

	private AbstractNode repeatStatement() {
		AbstractNode exp1 = null;
		AbstractNode stateSeq1 = null;

		read(REPEAT, "REPEAT");
		stateSeq1 = statementSeq();
		read(UNTIL, "UNTIL");
		exp1 = expression();

		return new RepeatNode(stateSeq1, exp1);
	}

	// Statement = [Assignment | ProcedureCall | IfStatement | PRINT
	// Expression | WhileStatement | RepeatStatement].
	private AbstractNode statement() {
		AbstractNode resNode = null;

		if (test(IF)) {
			resNode = ifStatement();
			return resNode;
		} else if (test(PRINT)) {
			read(PRINT, "PRINT");
			resNode = new PrintNode(expression());
			return resNode;
		} else if (test(WHILE)) {
			resNode = whileStatement();
			return resNode;
		} else if (test(REPEAT)) {
			resNode = repeatStatement();
			return resNode;
		} else if (test(ID)) {
			if (testLookAhead(LPAR)) {
				resNode = procedureCall();
			} else {
				resNode = assignment();
			}
		}
		return resNode;
	}

	private StatementSequenceNode statementSeq() {
		List<AbstractNode> list = new LinkedList<AbstractNode>();
		AbstractNode statement = statement();
		if (statement != null) {
			list.add(statement);
		}
		while (test(SEMICOLON)) {
			read(SEMICOLON, ";");
			statement = statement();
			if (statement != null) {
				list.add(statement);
			}
		}
		return new StatementSequenceNode(list);
	}

	private AbstractNode expression() {

		AbstractNode res = simpleExp();

		if (test(EQ)) {
			read(EQ, "=");
			res = new BinOpNode(EQ, res, simpleExp());
		}
		if (test(NEQ)) {
			read(NEQ, "#");
			res = new BinOpNode(NEQ, res, simpleExp());
		}
		if (test(LO)) {
			read(LO, "<");
			res = new BinOpNode(LO, res, simpleExp());
		}
		if (test(LOEQ)) {
			read(LOEQ, "<=");
			res = new BinOpNode(LOEQ, res, simpleExp());
		}
		if (test(HI)) {
			read(HI, ">");
			res = new BinOpNode(HI, res, simpleExp());
		}
		if (test(HIEQ)) {
			read(HIEQ, ">=");
			res = new BinOpNode(HIEQ, res, simpleExp());
		}

		return res;
	}

	private ActualParametersNode actualParameters() {
		ArrayList<AbstractNode> list = new ArrayList<AbstractNode>();

		list.add(expression());
		while (test(COMMA)) {
			read(COMMA, ",");
			list.add(expression());
		}

		return new ActualParametersNode(list);
	}

	private ProcedureCallNode procedureCall() {
		IdentNode ident = null;
		AbstractNode actualParameters = null;

		ident = constIdent();
		read(LPAR, "(");
		if (!test(RPAR))
			actualParameters = actualParameters();
		read(RPAR, ")");
		return new ProcedureCallNode(ident, actualParameters);
	}

	private AbstractNode simpleExp() {

		AbstractNode node;

		if (test(MINUS)) {
			read(MINUS, "-");
			node = new NegativNode(term());
		} else {
			node = term();
		}

		while (test(PLUS) || test(MINUS)) {
			if (test(PLUS)) {
				read(PLUS, "+");
				node = new BinOpNode(PLUS, node, term());
			} else if (test(MINUS)) {
				read(MINUS, "-");
				node = new BinOpNode(MINUS, node, term());
			}
		}

		return node;
	}

	private AbstractNode term() {

		AbstractNode factorNode = factor();

		while (test(MUL) || test(DIV)) {
			if (test(MUL)) {
				read(MUL, "*");
				factorNode = new BinOpNode(MUL, factorNode, factor());
			} else if (test(DIV)) {
				read(DIV, "/");
				factorNode = new BinOpNode(DIV, factorNode, factor());
			}
		}
		return factorNode;
	}

	private AbstractNode factor() {
		AbstractNode node = null;

		if (test(ID)) {
			if (testLookAhead(DOT) || testLookAhead(LBRAC)) {
				node = new ContentNode(selector());
			} else {
				node = new ContentNode(constIdent());
			}
		} else if (test(INT)) {
			node = integer();
		} else if (test(STR)) {
			node = string();
		} else if (test(READ)) {
			read(READ, "READ");
			node = string();
		} else if (test(LPAR)) {
			read(LPAR, "(");
			node = expression();
			read(RPAR, ")");
		} else {
			failExpectation("identifier, integer, string, read or (expression)");
		}

		return node;
	}

	private IfNode ifStatement() {
		IfNode node = null;
		AbstractNode exp1 = null;
		AbstractNode stateSeq1 = null;
		AbstractNode stateSeq2 = null;
		// abwechselnd expr und statementSeq
		AbstractNode elseifs = null;

		read(IF, "IF");
		exp1 = expression();
		read(THEN, "THEN");
		stateSeq1 = statementSeq();
		if (test(ELSIF)) {
			elseifs = ifStatement_();
		}
		if (test(END)) {
			read(END, "END");
			node = new IfNode(exp1, stateSeq1, elseifs, stateSeq2);
		} else {
			read(ELSE, "ELSE");
			stateSeq2 = statementSeq();
			read(END, "END");
			node = new IfNode(exp1, stateSeq1, elseifs, stateSeq2);
		}

		return node;

	}

	private IfNode ifStatement_() {
		IfNode node = null;
		AbstractNode exp1 = null;
		AbstractNode stateSeq1 = null;

		read(ELSIF, "ELSIF");
		exp1 = expression();
		read(THEN, "THEN");
		stateSeq1 = statementSeq();
		if (test(ELSIF)) {
			node = new IfNode(exp1, stateSeq1, ifStatement_(), null);
		} else {
			node = new IfNode(exp1, stateSeq1, null, null);
		}

		return node;
	}

	private WhileNode whileStatement() {
		AbstractNode exp1 = null;
		AbstractNode stateSeq1 = null;

		read(WHILE, "WHILE");
		exp1 = expression();
		read(DO, "DO");
		stateSeq1 = statementSeq();
		read(END, "END");
		return new WhileNode(exp1, stateSeq1);
	}

	/**
	 * Check if the next token is of the same type as the provided token.
	 * <p/>
	 * A token value of null will test if the remaining scanner output is empty.
	 * 
	 * @param token
	 *            the token to test against
	 * @return true, if the next token is of the same type as the provided
	 *         token, false otherwise
	 */
	boolean test(TokenID token) {
		return nextSymbol == null ? token == null : nextSymbol.id() == token;
	}

	/**
	 * Read the next token from the scanner and verify that its of type
	 * expectedToken. If it's not then fail with expectedString.
	 * <p/>
	 * A token value of null will test if the remaining scanner output is empty.
	 * 
	 * @param expectedToken
	 *            the expected token
	 * @param expectedString
	 *            the expected token in human readable form
	 * @return the next token
	 */
	MyToken read(TokenID expectedToken, String expectedString) {
		expect(expectedToken, expectedString);
		return read();
	}

	MyToken read(TokenID expectedToken) {
		expect(expectedToken, expectedToken.name());
		return read();
	}

	/**
	 * Read the next token from the scanner.
	 * 
	 * @return the next token or null if the scanner output is empty
	 */
	MyToken read() {
		MyToken curSymbol = nextSymbol;
		inSymbol();
		return curSymbol;
	}

	public void inSymbol() {
		try {
			while (((nextSymbol = scanner.yylex()) != null)
					&& (nextSymbol.id() == WHITESPACE)) {

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

	/**
	 * Check if the token after the next token is of the same type as the
	 * provided token.
	 * 
	 * A token value of null will test if the remaining scanner output is empty.
	 * 
	 * @param token
	 *            the token to test against
	 * @return true, if the token after the next token is of the same type as
	 *         the provided token, false otherwise
	 */
	boolean testLookAhead(TokenID token) {
		MyToken afterNextSymbol = null;

		try {
			afterNextSymbol = scanner.yylex();
			if (afterNextSymbol != null) {
				scanner.yypushback(afterNextSymbol.text().length());
			}
		} catch (java.lang.Error e) {
			// will be thrown when next symbol is EOF
			// in this case null is a good value for afterNextSymbol
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + inFile + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + inFile + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}

		return afterNextSymbol == null ? token == null
				: afterNextSymbol.id() == token;
	}

	/**
	 * Check if the next token is expectedToken or fail with an error message
	 * otherwise.
	 * <p/>
	 * An expectedToken value of null will verify that the remaining scanner
	 * output is empty.
	 * 
	 * @param expectedToken
	 *            the expected token
	 * @param expectedString
	 *            the expected token in human readable form
	 */
	void expect(TokenID expectedToken, String expectedString) {
		if (!test(expectedToken)) {
			failExpectation(expectedString);
		}
	}

	/**
	 * Stop parsing and show the provided error message together with the
	 * current line and column.
	 * 
	 * @param expectation
	 */
	void failExpectation(String expectation) {
		String location;

		if (nextSymbol != null) {
			location = "line " + (nextSymbol.line() + 1) + ", column "
					+ (nextSymbol.column() + 1);
		} else {
			location = "end of file";
		}

		error("expected " + expectation + " at " + location);
	}

	static void error(String str) {
		System.out.println("==> Error: " + str);
		// throw new ParserException("==> Error: " + str);
	}

	// public static void main(String[] argv) {
	// System.out.println("MyStandalone Version 0.1");
	//
	// if (argv.length == 0) {
	// System.out.println("Usage : java MyStandalone <inputfile>");
	// } else {
	//
	// for (int i = 0; i < argv.length; i++) {
	// try {
	// inFile = argv[i];
	//
	// Parser parser = new Parser(new MyFlexScanner(
	// new java.io.FileReader(argv[i])));
	//
	// AbstractNode erg = parser.parse();
	// System.out.println(erg);
	//
	// ObjectOutputStream os = new ObjectOutputStream(new
	// FileOutputStream(nodeFile));
	// os.writeObject(erg);
	// os.flush();
	//
	//
	// } catch (java.io.FileNotFoundException e) {
	// System.out.println("File not found : \"" + inFile + "\"");
	// } catch (Exception e) {
	// System.out.println("Unexpected exception:");
	// e.printStackTrace();
	// }
	// }
	//
	// }
	// }

}
