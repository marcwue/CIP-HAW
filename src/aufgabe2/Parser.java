package aufgabe2;

import nodes.*;
import sun.security.pkcs.ParsingException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static aufgabe2.TokenID.*;

/**
 * @author Marc Wüseke
 */
public class Parser {
    private MyFlexScanner scanner;
    private MyToken nextSymbol;
    private String[] argv;
    private String inFile;

    public Parser(MyFlexScanner scanner) {
        this.scanner = scanner;
    }

    public AbstractNode parse() {
        return module();
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
        return new IdentNode(read(ID, "identifier").text());
    }

    // ArrayType = = 'ARRAY' '[' IndexExpression ']' 'OF' Type
    private ArrayTypeNode arrayType() {
        read(ARRAY, "ARRAY");
        read(LBRAC, "[");
        AbstractNode node = indexExpr();
        read(RBRAC, "]");
        read(OF, "OF");
        AbstractNode t = type();
        return new ArrayTypeNode(node, t);
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
        fieldLists.add((FieldListNode) fieldList());
        while (test(SEMICOLON)) {
            fieldLists.add((FieldListNode) fieldList());
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
        FormalParametersNode params;
        if (test(VAR) || test(ID)) {
            params = formalParameters();
        }
        params = formalParameters();
        read(RPAR, ")");
        return new ProcedureHeadingNode(ident, params);
    }

    // ProcedureBody = Declarations 'BEGIN' StatementSequence 'END'
    private ProcedureBodyNode procedureBody() {
        //TODO
        /*
        DeclarationsNode declarations = declarations();
        read(BEGIN, "BEGIN");
        StatemantSequenceNode statementSeqNode = statementSeq();
        read(END, "END");
        return new ProcedureBodyNode(declarations, statementSeqNode);*/
        return null;
    }

    // ProcedureDeclaration =   ProcedureHeading ’;’
    //                          ProcedureBody ident
    private ProcedureDeclarationNode procedureDeclaration() {
        ProcedureHeadingNode head = procedureHeading();
        read(SEMICOLON, ";");
        ProcedureBodyNode body = procedureBody();
        return new ProcedureDeclarationNode(head, body);
    }

    private AbstractNode declarations() {
        AbstractNode result = null;
        if (test(CONST)) {
            read(CONST, "const");

            List<ConstNode> liste = new LinkedList();
            IdentNode constIdent;
            ExpressionNode exp;
            do {
                constIdent = constIdent();
                read(ASSIGN, "=");
                exp = expr();
                read(SEMICOLON, ";");
                liste.add(new ConstNode(constIdent, exp));
            } while (test(ID));

            result = new ConstListNode(liste);
        } else if (test(TYPE)) {
            read(TYPE, "type");

            List<TypeNode> liste = new LinkedList();
            IdentNode ident;
            AbstractNode type;
            do {
                ident = constIdent();
                read(ASSIGN, "=");
                type = type();
                read(SEMICOLON, ";");
                liste.add(new TypeNode(ident, type));
            } while (test(TYPE));

            result = new TypeListNode(liste);
        } else if (test(VAR)) {
            read(VAR, "var");

            List<VarNode> liste = new LinkedList();

            IdentListNode identList;
            AbstractNode type;
            do {
                read(COLON, ":");
                identList = identList();
                read(SEMICOLON, ";");
                type  = type();
                liste.add(new VarNode(identList, type));
            } while(test(VAR));

            result = new VarListNode(liste);
        }else if (test(PROCEDURE)) {
            procedureDeclaration();
            read(SEMICOLON, ";");
        } else {
            failExpectation("const, type, var or procedure declaration");
        }
        return result;
    }


    // Module =     ’MODULE’ ident ’;’ Declarations
    //              ’BEGIN’ StatementSequence
    //              ’END’ ident ’
    private ModuleNode module() {
        read(MODULE, "MODULE");
        IdentNode moduleName = constIdent();
        read(SEMICOLON, ";");
        AbstractNode declaration = declarations();
        read(BEGIN, "BEGIN");
        StatemantSequenceNode stmtseq = statementSeq();
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
            node = constIdent();
        } else if (test(INT)) {
            node = integer();
        } else {
            failExpectation("type");
        }
        return node;
    }

    // integer
    private IntNode integer() {
        return new IntNode(
                Integer.parseInt(
                        read(ID, "identifier").text()));
    }

    public static void print(MyToken token) {
        System.out.println(token);
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

    private AbstractNode exprSeq() throws ParsingException {
        AbstractNode simpleExprRes = null;
        if (nextSymbol.id() == BEGIN) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("BEGIN expected\n");
        }
        while ((nextSymbol.id() == TokenID.LPAR)
                || (nextSymbol.id() == TokenID.ID)
                || (nextSymbol.id() == TokenID.INT)) {
            simpleExprRes = simpleExp();
        }

        // while (nextsymbol.id() == TokenID.WHILE) {
        // simpleExprRes = whileStatement();
        // }

        while (nextSymbol.id() == TokenID.IF) {
            simpleExprRes = ifStatement();
        }

        if (nextSymbol.id() == TokenID.END) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("END expected\n");
        }
        return simpleExprRes;
    }

    private AbstractNode assignment(MyToken ident)
            throws ParsingException {

        inSymbol();
        AbstractNode simpleExpr = simpleExp();
        return new AssignmentNode(ident, simpleExpr);
    }

    private static StatementNode statement() {


        return new StatementNode(null);
    }

    private AbstractNode statementSeq() throws ParsingException {

        return simpleExp();
    }

    private AbstractNode expression() throws ParsingException {

        AbstractNode simpleExp1 = simpleExp();
        AbstractNode simpleExp2 = null;
        MyToken relop = null;

        if (nextSymbol.id() == TokenID.EQ
                || nextSymbol.id() == TokenID.NEQ
                || nextSymbol.id() == TokenID.LO
                || nextSymbol.id() == TokenID.LOEQ
                || nextSymbol.id() == TokenID.HI
                || nextSymbol.id() == TokenID.HIEQ) {
            print(nextSymbol);
            relop = nextSymbol;
        } else {
            throw new ParsingException("Rel Op expected");
        }

        inSymbol();
        simpleExp2 = simpleExp();

        return new ExpressionNode(simpleExp1, relop, simpleExp2);
    }


    private AbstractNode simpleExp() throws ParsingException {

        MyToken lsy;
        term();
        if ((nextSymbol.id() == TokenID.PLUS)
                || (nextSymbol.id() == TokenID.MINUS)) {
            lsy = nextSymbol;
            inSymbol();
            simpleExp();
            if (lsy.id() == TokenID.PLUS)
                print(lsy);
            else
                print(lsy);
        }

        return term();
    }


    private AbstractNode term() throws ParsingException {

        MyToken lsy;
        AbstractNode t = factor();
        if ((nextSymbol.id() == TokenID.MUL)
                || (nextSymbol.id() == TokenID.DIV)) {
            lsy = nextSymbol;
            inSymbol();
            term();
            if (lsy.id() == TokenID.MUL)
                print(lsy);
            else
                print(lsy);
        }

        return new TermNode(t);
    }

    private AbstractNode factor() throws ParsingException {
        FactorNode f = null;
        MyToken next;
        // Klammern
        if (nextSymbol.id() == TokenID.LPAR) {
            print(nextSymbol);
            inSymbol();
            simpleExp();
            if (nextSymbol.id() == TokenID.RPAR) {
                print(nextSymbol);
                inSymbol();
            } else {
                throw new ParsingException(" ) expected");
            }
        }
        // integer
        else if (nextSymbol.id() == TokenID.INT) {
            System.out.println("ddd" + nextSymbol);
            print(nextSymbol);
            f = new FactorNode(Integer.parseInt(nextSymbol.text()));
            inSymbol();
        }
        // identifier
        else if ((next = nextSymbol).id() == TokenID.ID) {
            print(nextSymbol);
            f = new FactorNode(nextSymbol.text());
            inSymbol();
            if (nextSymbol.id() == TokenID.ASSIGN) {
                print(nextSymbol);
                assignment(next);
                if (nextSymbol.id() == TokenID.SEMICOLON) {
                    print(nextSymbol);
                    inSymbol();
                    simpleExp();
                } else {
                    throw new ParsingException("SEMICOLON expected\n");
                }
            }
        }

        System.out.println("+" + f);
        return f;
    }

    private IfNode ifStatement() throws ParsingException {
        AbstractNode e = null, st1 = null, st2 = null;
        if (nextSymbol.id() == TokenID.IF) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("IF expected");
        }
        e = expression();
        if (nextSymbol.id() == TokenID.THEN) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("THEN expected");
        }
        st1 = statementSeq();
        if (nextSymbol.id() == TokenID.ELSE) {
            print(nextSymbol);
            inSymbol();
            st2 = statementSeq();
        }
        if (nextSymbol.id() == TokenID.END) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("END expected");
        }
        return new IfNode(e, st1, st2);
    }

    private WhileNode whileStatement() throws ParsingException {
        AbstractNode e = null, st = null;
        if (nextSymbol.id() == TokenID.WHILE) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("WHILE expected " + nextSymbol);
        }
        e = expression();
        if (nextSymbol.id() == TokenID.DO) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("DO expected " + nextSymbol);
        }
        st = statementSeq();
        if (nextSymbol.id() == TokenID.ENDSY) {
            print(nextSymbol);
            inSymbol();
        } else {
            throw new ParsingException("END expected " + nextSymbol);
        }
        return new WhileNode(e, st);
    }

    /**
     * Check if the next token is of the same type as the provided token.
     * <p/>
     * A token value of null will test if the remaining scanner output is empty.
     *
     * @param token the token to test against
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
     * @param expectedToken  the expected token
     * @param expectedString the expected token in human readable form
     * @return the next token
     */
    MyToken read(TokenID expectedToken, String expectedString) {
        expect(expectedToken, expectedString);
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

    /**
     * Check if the next token is expectedToken or fail with an error message
     * otherwise.
     * <p/>
     * An expectedToken value of null will verify that the remaining scanner
     * output is empty.
     *
     * @param expectedToken  the expected token
     * @param expectedString the expected token in human readable form
     */
    void expect(TokenID expectedToken, String expectedString) {
        if (!test(expectedToken)) {
            failExpectation(expectedString);
        }
    }

    /**
     * Stop parsing and show the provided error message together with the
     * current line and column.
     */
    void failExpectation(String expectation) {
        String location;

        if (nextSymbol != null) {
            location = "line " + (nextSymbol.line() + 1) + ", column " + (nextSymbol.column() + 1);
        } else {
            location = "end of file";
        }

        error("expected " + expectation + " at " + location);
    }

    static void error(String str) {
        System.out.print("==> Error: " + str);
        //throw new ParserException("==> Error: " + str);
    }

    public static void main(String[] argv) {
        System.out.println("MyStandalone Version 0.1");

        if (argv.length == 0) {
            System.out.println("Usage : java MyStandalone <inputfile>");
        } else {

            for (int i = 0; i < argv.length; i++) {
                try {
                    Parser parser = new Parser(
                            new MyFlexScanner(
                                    new java.io.FileReader(argv[0])));

                    AbstractNode erg = parser.parse();
                    erg.toString();

                } catch (java.io.FileNotFoundException e) {
                    System.out.println("File not found : \"" + argv[0] + "\"");
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
