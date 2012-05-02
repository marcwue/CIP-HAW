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

