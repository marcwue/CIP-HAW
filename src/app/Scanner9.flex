package app;

//Usercode
import static app.TokenID.*;

//Option und Declaration
%%

%public
%class MyFlexScanner
%type MyToken

//need this to use yyline, yycolumn later
%line
%column

digit 	=	[0-9]
letter	=	[a-zA-Z]
integer	=	{digit}({digit})*
ident 	=	{letter}({letter}|{digit}|"_"})*
blank	=	[ \t\n\r]
string = \"([^\\\"]|\\.)*\"

//lexical rules
%%
{blank}* {}

"*" { return new MyToken(MUL, yytext(), yyline, yycolumn); }
"+" { return new MyToken(PLUS, yytext(), yyline, yycolumn); }
"-" { return new MyToken(MINUS, yytext(), yyline, yycolumn); }
"/" { return new MyToken(DIV, yytext(), yyline, yycolumn); }
":=" { return new MyToken(ASSIGN, yytext(), yyline, yycolumn); }

"=" { return new MyToken(EQ, yytext(), yyline, yycolumn); }
"#" { return new MyToken(NEQ, yytext(), yyline, yycolumn); }
"<" { return new MyToken(LO, yytext(), yyline, yycolumn); }
"<=" { return new MyToken(LOEQ, yytext(), yyline, yycolumn); }
">" { return new MyToken(HI, yytext(), yyline, yycolumn); }
">=" { return new MyToken(HIEQ, yytext(), yyline, yycolumn); }

"." { return new MyToken(DOT, yytext(), yyline, yycolumn); }
"," { return new MyToken(COMMA, yytext(), yyline, yycolumn); }
":" { return new MyToken(COLON, yytext(), yyline, yycolumn); }
"(" { return new MyToken(LPAR, yytext(), yyline, yycolumn); }
")" { return new MyToken(RPAR, yytext(), yyline, yycolumn); }
"[" { return new MyToken(LBRAC, yytext(), yyline, yycolumn); }
"]" { return new MyToken(RBRAC, yytext(), yyline, yycolumn); }
";" { return new MyToken(SEMICOLON, yytext(), yyline, yycolumn); }

[oO][fF] 				{ return new MyToken(OF, yytext(), yyline, yycolumn); }
[tT][hH][eE][nN] 		{ return new MyToken(THEN, yytext(), yyline, yycolumn); }
[dD][oO] 				{ return new MyToken(DO, yytext(), yyline, yycolumn); }
[pP][rR][iI][nN][tT] 	{ return new MyToken(PRINT, yytext(), yyline, yycolumn); }
[rR][eE][aA][dD] 		{ return new MyToken(READ, yytext(), yyline, yycolumn); }

[eE][nN][dD] 			{ return new MyToken(END, yytext(), yyline, yycolumn); }
[eE][lL][sS][eE] 		{ return new MyToken(ELSE, yytext(), yyline, yycolumn); }
[eE][lL][sS][iI][fF] 	{ return new MyToken(ELSIF, yytext(), yyline, yycolumn); }
[iI][fF] 				{ return new MyToken(IF, yytext(), yyline, yycolumn); }
[wW][hH][iI][lL][eE] 	{ return new MyToken(WHILE, yytext(), yyline, yycolumn); }
[rR][eE][pP][eE][eA][tT] { return new MyToken(REPEAT, yytext(), yyline, yycolumn); }
[uU][nN][tT][iI][lL] 	{ return new MyToken(UNTIL, yytext(), yyline, yycolumn); }

[aA][rR][rR][aA][yY]	{ return new MyToken(ARRAY, yytext(), yyline, yycolumn); }
[rR][eE][cC][oO][rR][dD] { return new MyToken(RECORD, yytext(), yyline, yycolumn); }
[cC][oO][nN][sS][tT] 	{ return new MyToken(CONST, yytext(), yyline, yycolumn); }
[tT][yY][pP][eE] 		{ return new MyToken(TYPE, yytext(), yyline, yycolumn); }

[vV][aA][rR] 							{ return new MyToken(VAR, yytext(), yyline, yycolumn); }
[pP][rR][oO][cC][eE][dD][uU][rR][eE] 	{ return new MyToken(PROCEDURE, yytext(), yyline, yycolumn); }
[bB][eE][gG][iI][nN] 					{ return new MyToken(BEGIN, yytext(), yyline, yycolumn); }
[mM][oO][dD][uU][lL][eE] 				{ return new MyToken(MODULE, yytext(), yyline, yycolumn); }

-?{integer}+ { return new MyToken(INT, yytext(), yyline, yycolumn); }
{ident} { return new MyToken(ID, yytext(), yyline, yycolumn); }
{string}    { return new MyToken(STR, yytext(), yyline, yycolumn); }

. { return new MyToken(ERROR, yytext(), yyline, yycolumn); } 