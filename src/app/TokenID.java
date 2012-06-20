/**
 * 
 */
package app;

import java.util.HashMap;
import java.util.Map;

import nodes.AbstractNode;

/**
 * @author Marc Wüseke
 *
 */
public enum TokenID {
    //enums

	WHITESPACE, BEGINSY, ENDSY, 
    MUL, PLUS, MINUS, DIV, ASSIGN,
    EQ, NEQ, LO, LOEQ, HI, HIEQ,
    DOT, COMMA, COLON, LPAR, RPAR, LBRAC, RBRAC, SEMICOLON,
    OF, THEN, DO, PRINT, READ,
    END, ELSE, ELSIF, IF, WHILE, REPEAT, UNTIL,
    ARRAY, RECORD, CONST, TYPE, STR,
    VAR, PROCEDURE, BEGIN, MODULE, ERROR, INT, ID, BLANK,
    BOOLEAN;
    
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
