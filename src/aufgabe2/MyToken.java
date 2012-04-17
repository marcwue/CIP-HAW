package aufgabe2;

import java.util.HashMap;
import java.util.Map;

public class MyToken {

    private int line, column;
    private TokenID id;
    private String text;

    public MyToken(TokenID id, String text, int line, int column) {
        this.id = id;
        this.text = text;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token(" + id + "(" + id.id() + ")" + "," + text + "," + line + "," + column + ")";
    }

    public TokenID id() {
        return id;
    }

    public String text() {
        return text;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }
}

enum TokenID {
    //enums

	WHITESPACE, BEGINSY, ENDSY, 
    MUL, PLUS, MINUS, DIV, ASSIGN,
    EQ, NEQ, LO, LOEQ, HI, HIEQ,
    DOT, COMMA, COLON, LPAR, RPAR, LBRAC, RBRAC, SEMICOLON,
    OF, THEN, DO, PRINT, READ,
    END, ELSE, ELSIF, IF, WHILE, REPEAT, UNTIL,
    ARRAY, RECORD, CONST, TYPE,
    VAR, PROCEDURE, BEGIN, MODULE, ERROR, INT, ID, BLANK;
    
    private static final int startValue = 256;
    private static final Map<TokenID, Integer> ids;

    //generate the enums in the Map<TokenID, Integer> ids
    static {
        ids = new HashMap<TokenID, Integer>();
        for (int i = 0; i < values().length; ++i) {
            ids.put(values()[i], startValue + i);
        }
    }

    // must not be used in constructor!
    public int id() {
        return ids.get(this);
    }
}