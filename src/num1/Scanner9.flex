package num1;

//Usercode

import java.util.HashMap;
import java.util.Map;

import static num1.TokenID.*;

class MyToken {
	private int line, column;
	private TokenID id;
	private String text;
	
	public MyToken (TokenID id, String text, int line, int column) {
		this.id = id;
		this.text = text;
		this.line = line;
		this.column = column;
	
		String out = "Token(" + id + "(" + id.id() + ")" + "," + text + "," + line + "," + column + ")";
		System.out.println(out);
	}
	
	public TokenID id() { return id; }
	public String text() { return text; }
	public int line() { return line; }
	public int column() { return column; }
}

//package private TokenID
enum TokenID {
	//enums
    MUL, PLUS, MINUS, DIV, ASSIGN,
    EQ, NEQ, LO, LOEQ, HI, HIEQ,
    DOT, COMMA, COLON, LPAR, RPAR, LBRAC, RBRAC, SEMICOLON,
    OF, THEN, DO, PRINT, READ,
    END, ELSE, ELSIF, IF, WHILE, REPEAT, UNTIL,
    ARRAY, RECORD, CONST, TYPE,
    VAR, PROCEDURE, BEGIN, MODULE;
    
    private static final int startValue = 256;
    private static final Map<TokenID, Integer> ids;
    
    //generate the enums in the Map<TokenID, Integer> ids
    static {
        ids = new HashMap<TokenID, Integer>();
        for (int i = 0; i < values().length; ++i) {
            ids.put(values()[i], startValue+i);
        }
    }
    
    // must not be used in constructor!
    public int id() { return ids.get(this); }
}

//Option und Declaration
%%

%public
%class Scanner1
%standalone
//need this to use yyline, yycolumn later
%line
%column

digit 	=	[0-9]
letter	=	[a-zA-Z]
integer	=	{digit}({digit})*
ident 	=	{letter}({letter}|{digit}})*
blank	=	[/t/n/r]

//lexical rules
%%
"*" { new MyToken(MUL, yytext(), yyline, yycolumn); }
"+" { new MyToken(PLUS, yytext(), yyline, yycolumn); }
"-" { new MyToken(MINUS, yytext(), yyline, yycolumn); }
"/" { new MyToken(DIV, yytext(), yyline, yycolumn); }
":=" { new MyToken(ASSIGN, yytext(), yyline, yycolumn); }

"=" { new MyToken(EQ, yytext(), yyline, yycolumn); }
"#" { new MyToken(NEQ, yytext(), yyline, yycolumn); }
"<" { new MyToken(LO, yytext(), yyline, yycolumn); }
"<=" { new MyToken(LOEQ, yytext(), yyline, yycolumn); }
">" { new MyToken(HI, yytext(), yyline, yycolumn); }
">=" { new MyToken(HIEQ, yytext(), yyline, yycolumn); }

"." { new MyToken(DOT, yytext(), yyline, yycolumn); }
"," { new MyToken(COMMA, yytext(), yyline, yycolumn); }
":" { new MyToken(COLON, yytext(), yyline, yycolumn); }
"(" { new MyToken(LPAR, yytext(), yyline, yycolumn); }
")" { new MyToken(RPAR, yytext(), yyline, yycolumn); }
"[" { new MyToken(LBRAC, yytext(), yyline, yycolumn); }
"]" { new MyToken(RBRAC, yytext(), yyline, yycolumn); }
";" { new MyToken(SEMICOLON, yytext(), yyline, yycolumn); }

[oO][fF] 				{ new MyToken(OF, yytext(), yyline, yycolumn); }
[tT][hH][eE][nN] 		{ new MyToken(THEN, yytext(), yyline, yycolumn); }
[dD][oO] 				{ new MyToken(DO, yytext(), yyline, yycolumn); }
[pP][rR][iI][nN][tT] 	{ new MyToken(PRINT, yytext(), yyline, yycolumn); }
[rR][eE][aA][dD] 		{ new MyToken(READ, yytext(), yyline, yycolumn); }

[eE][nN][dD] 			{ new MyToken(END, yytext(), yyline, yycolumn); }
[eE][lL][sS][eE] 		{ new MyToken(ELSE, yytext(), yyline, yycolumn); }
[eE][lL][sS][iI][fF] 	{ new MyToken(ELSIF, yytext(), yyline, yycolumn); }
[iI][fF] 				{ new MyToken(IF, yytext(), yyline, yycolumn); }
[wW][hH][iI][lL][eE] 	{ new MyToken(WHILE, yytext(), yyline, yycolumn); }
[rR][eE][pP][eE][eA][tT] { new MyToken(REPEAT, yytext(), yyline, yycolumn); }
[uU][nN][tT][iI][lL] 	{ new MyToken(UNTIL, yytext(), yyline, yycolumn); }

[aA][rR][rR][aA][yY]	{ new MyToken(ARRAY, yytext(), yyline, yycolumn); }
[rR][eE][cC][oO][rR][dD] { new MyToken(RECORD, yytext(), yyline, yycolumn); }
[cC][oO][nN][sS][tT] 	{ new MyToken(CONST, yytext(), yyline, yycolumn); }
[tT][yY][pP][eE] 		{ new MyToken(TYPE, yytext(), yyline, yycolumn); }

[vV][aA][rR] 							{ new MyToken(VAR, yytext(), yyline, yycolumn); }
[pP][rR][oO][cC][eE][dD][uU][rR][eE] 	{ new MyToken(PROCEDURE, yytext(), yyline, yycolumn); }
[bB][eE][gG][iI][nN] 					{ new MyToken(BEGIN, yytext(), yyline, yycolumn); }
[mM][oO][dD][uU][lL][eE] 				{ new MyToken(MODULE, yytext(), yyline, yycolumn); }

{digit}+ { System.out.println("DIGITS " + yytext()); }
{ident} { System.out.println("ID " + yytext()); }

{blank} {}
//if there is nothing found overhead then it is nothing "nichts"
. { System.out.println("error (" + yytext() + "," + yyline + "," + yycolumn +")"); System.exit(0);}


