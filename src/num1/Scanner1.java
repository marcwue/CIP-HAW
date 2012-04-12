/* The following code was generated by JFlex 1.4.3 on 03.04.12 00:14 */

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
    MUL, PLUS, MINUS, DIV, ASSIGN,
    EQ, NEQ, LO, LOEQ, HI, HIEQ,
    DOT, COMMA, COLON, LPAR, RPAR, LBRAC, RBRAC, SEMICOLON,
    OF, THEN, DO, PRINT, READ,
    END, ELSE, ELSIF, IF, WHILE, REPEAT, UNTIL,
    ARRAY, RECORD, CONST, TYPE,
    VAR, PROCEDURE, BEGIN, MODULE;
    
    private static final int startValue = 256;
    private static final Map<TokenID, Integer> ids;
    
    static {
        ids = new HashMap<TokenID, Integer>();
        for (int i = 0; i < values().length; ++i) {
            ids.put(values()[i], startValue+i);
        }
    }
    
    // must not be used in constructor!
    public int id() { return ids.get(this); }
}

//Option und Deklarationen

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 03.04.12 00:14 from the specification file
 * <tt>C:/Users/Eagles/workspace2/CIP-HAW/src/num1/Scanner9.flex</tt>
 */
public class Scanner1 {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\12\0\1\53\30\0\1\13\4\0\1\20\1\21\1\6\1\7\1\17"+
    "\1\10\1\16\1\4\12\1\1\11\1\24\1\14\1\12\1\15\2\0"+
    "\1\43\1\50\1\46\1\33\1\31\1\26\1\51\1\30\1\35\2\2"+
    "\1\37\1\52\1\32\1\25\1\34\1\2\1\5\1\40\1\27\1\44"+
    "\1\47\1\41\1\2\1\45\1\2\1\22\1\0\1\23\3\0\1\36"+
    "\1\50\1\46\1\33\1\42\1\26\1\51\1\30\1\35\2\2\1\37"+
    "\1\52\1\32\1\25\1\34\1\2\1\5\1\40\1\27\1\44\1\47"+
    "\1\41\1\2\1\45\1\2\2\0\1\3\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\3\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\15\3\1\24\1\0"+
    "\1\3\1\25\1\26\1\27\1\30\4\3\1\31\1\3"+
    "\1\32\14\3\1\33\7\3\1\34\3\3\1\35\1\3"+
    "\1\36\1\37\1\40\13\3\1\41\1\3\1\42\1\43"+
    "\1\44\1\45\1\46\1\47\1\3\1\50\1\51\1\3"+
    "\1\52\2\3\1\53";

  private static int [] zzUnpackAction() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\54\0\130\0\204\0\54\0\260\0\54\0\54"+
    "\0\54\0\334\0\54\0\54\0\u0108\0\u0134\0\54\0\54"+
    "\0\54\0\54\0\54\0\54\0\54\0\u0160\0\u018c\0\u01b8"+
    "\0\u01e4\0\u0210\0\u023c\0\u0268\0\u0294\0\u02c0\0\u02ec\0\u0318"+
    "\0\u0344\0\u0370\0\54\0\u039c\0\u03c8\0\54\0\54\0\54"+
    "\0\204\0\u03f4\0\u0420\0\u044c\0\u0478\0\204\0\u04a4\0\204"+
    "\0\u04d0\0\u04fc\0\u0528\0\u0554\0\u0580\0\u05ac\0\u05d8\0\u0604"+
    "\0\u0630\0\u065c\0\u0688\0\u06b4\0\204\0\u06e0\0\u070c\0\u0738"+
    "\0\u0764\0\u0790\0\u07bc\0\u07e8\0\204\0\u0814\0\u0840\0\u086c"+
    "\0\204\0\u0898\0\204\0\204\0\204\0\u08c4\0\u08f0\0\u091c"+
    "\0\u0948\0\u0974\0\u09a0\0\u09cc\0\u09f8\0\u0a24\0\u0a50\0\u0a7c"+
    "\0\204\0\u0aa8\0\204\0\204\0\204\0\204\0\204\0\204"+
    "\0\u0ad4\0\204\0\204\0\u0b00\0\204\0\u0b2c\0\u0b58\0\204";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\4\1\27"+
    "\1\4\1\30\1\4\1\31\1\32\1\33\1\34\2\4"+
    "\1\35\1\30\1\34\1\36\1\4\1\37\1\40\1\41"+
    "\1\4\1\42\1\43\55\0\1\3\53\0\1\44\1\4"+
    "\2\0\1\4\17\0\26\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\4\4\1\45\10\4\1\45\10\4\13\0"+
    "\1\46\53\0\1\47\53\0\1\50\42\0\1\44\1\4"+
    "\2\0\1\4\17\0\1\4\1\51\24\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\3\4\1\52\14\4\1\53"+
    "\5\4\2\0\1\44\1\4\2\0\1\4\17\0\5\4"+
    "\1\54\4\4\1\55\13\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\1\56\25\4\2\0\1\44\1\4\2\0"+
    "\1\57\17\0\26\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\1\4\1\60\24\4\2\0\1\44\1\4\2\0"+
    "\1\61\17\0\26\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\3\4\1\62\22\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\5\4\1\63\20\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\1\64\25\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\11\4\1\65\4\4\1\65\7\4"+
    "\2\0\1\44\1\4\2\0\1\4\17\0\4\4\1\66"+
    "\10\4\1\66\10\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\1\67\25\4\4\0\1\4\51\0\1\44\1\4"+
    "\2\0\1\4\17\0\7\4\1\70\1\4\1\71\4\4"+
    "\1\71\2\4\1\72\4\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\4\4\1\73\10\4\1\73\10\4\2\0"+
    "\1\44\1\4\2\0\1\4\17\0\7\4\1\74\16\4"+
    "\2\0\1\44\1\4\2\0\1\4\17\0\6\4\1\75"+
    "\17\4\2\0\1\44\1\4\2\0\1\4\17\0\13\4"+
    "\1\76\12\4\2\0\1\44\1\4\2\0\1\4\17\0"+
    "\1\77\7\4\1\100\15\4\2\0\1\44\1\4\2\0"+
    "\1\101\17\0\26\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\10\4\1\102\15\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\2\4\1\103\23\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\5\4\1\104\20\4\2\0\1\44"+
    "\1\4\2\0\1\105\17\0\26\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\24\4\1\106\1\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\6\4\1\107\17\4\2\0"+
    "\1\44\1\4\2\0\1\4\17\0\4\4\1\110\10\4"+
    "\1\110\10\4\2\0\1\44\1\4\2\0\1\4\17\0"+
    "\6\4\1\111\17\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\1\112\25\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\5\4\1\113\20\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\4\4\1\114\10\4\1\114\10\4\2\0"+
    "\1\44\1\4\2\0\1\4\17\0\4\4\1\115\3\4"+
    "\1\116\4\4\1\115\10\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\21\4\1\117\4\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\5\4\1\120\20\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\11\4\1\121\4\4\1\121"+
    "\7\4\2\0\1\44\1\4\2\0\1\4\17\0\12\4"+
    "\1\122\13\4\2\0\1\44\1\4\2\0\1\4\17\0"+
    "\10\4\1\123\15\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\13\4\1\124\12\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\10\4\1\125\15\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\17\4\1\126\6\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\15\4\2\127\7\4\2\0"+
    "\1\44\1\4\2\0\1\130\17\0\26\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\1\4\1\131\24\4\2\0"+
    "\1\44\1\4\2\0\1\4\17\0\4\4\1\132\10\4"+
    "\1\132\10\4\2\0\1\44\1\4\2\0\1\4\17\0"+
    "\2\4\1\133\23\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\20\4\1\134\5\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\4\4\1\135\10\4\1\135\10\4\2\0"+
    "\1\44\1\4\2\0\1\4\17\0\12\4\1\136\13\4"+
    "\2\0\1\44\1\4\2\0\1\4\17\0\2\4\1\137"+
    "\23\4\2\0\1\44\1\4\2\0\1\4\17\0\5\4"+
    "\1\140\20\4\2\0\1\44\1\4\2\0\1\4\17\0"+
    "\12\4\1\141\13\4\2\0\1\44\1\4\2\0\1\4"+
    "\17\0\2\4\1\142\23\4\2\0\1\44\1\4\2\0"+
    "\1\4\17\0\6\4\1\143\17\4\2\0\1\44\1\4"+
    "\2\0\1\4\17\0\6\4\1\144\17\4\2\0\1\44"+
    "\1\4\2\0\1\4\17\0\4\4\1\145\10\4\1\145"+
    "\10\4\2\0\1\44\1\4\2\0\1\4\17\0\17\4"+
    "\1\146\6\4\2\0\1\44\1\4\2\0\1\147\17\0"+
    "\26\4\2\0\1\44\1\4\2\0\1\4\17\0\4\4"+
    "\1\150\10\4\1\150\10\4\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2948];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\1\11\1\1\3\11\1\1\2\11"+
    "\2\1\7\11\15\1\1\11\1\0\1\1\3\11\100\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner1(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner1(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 154) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 37: 
          { new MyToken(UNTIL, yytext(), yyline, yycolumn);
          }
        case 44: break;
        case 28: 
          { new MyToken(VAR, yytext(), yyline, yycolumn);
          }
        case 45: break;
        case 19: 
          { new MyToken(SEMICOLON, yytext(), yyline, yycolumn);
          }
        case 46: break;
        case 36: 
          { new MyToken(WHILE, yytext(), yyline, yycolumn);
          }
        case 47: break;
        case 39: 
          { new MyToken(BEGIN, yytext(), yyline, yycolumn);
          }
        case 48: break;
        case 13: 
          { new MyToken(DOT, yytext(), yyline, yycolumn);
          }
        case 49: break;
        case 16: 
          { new MyToken(RPAR, yytext(), yyline, yycolumn);
          }
        case 50: break;
        case 32: 
          { new MyToken(ELSE, yytext(), yyline, yycolumn);
          }
        case 51: break;
        case 29: 
          { new MyToken(READ, yytext(), yyline, yycolumn);
          }
        case 52: break;
        case 31: 
          { new MyToken(TYPE, yytext(), yyline, yycolumn);
          }
        case 53: break;
        case 6: 
          { new MyToken(PLUS, yytext(), yyline, yycolumn);
          }
        case 54: break;
        case 8: 
          { new MyToken(COLON, yytext(), yyline, yycolumn);
          }
        case 55: break;
        case 1: 
          { System.out.println("error (" + yytext() + "," + yyline + "," + yycolumn +")"); System.exit(0);
          }
        case 56: break;
        case 12: 
          { new MyToken(HI, yytext(), yyline, yycolumn);
          }
        case 57: break;
        case 40: 
          { new MyToken(REPEAT, yytext(), yyline, yycolumn);
          }
        case 58: break;
        case 34: 
          { new MyToken(PRINT, yytext(), yyline, yycolumn);
          }
        case 59: break;
        case 33: 
          { new MyToken(ELSIF, yytext(), yyline, yycolumn);
          }
        case 60: break;
        case 20: 
          { System.out.print(yytext());
          }
        case 61: break;
        case 24: 
          { new MyToken(OF, yytext(), yyline, yycolumn);
          }
        case 62: break;
        case 14: 
          { new MyToken(COMMA, yytext(), yyline, yycolumn);
          }
        case 63: break;
        case 2: 
          { System.out.println("DIGITS " + yytext());
          }
        case 64: break;
        case 30: 
          { new MyToken(THEN, yytext(), yyline, yycolumn);
          }
        case 65: break;
        case 35: 
          { new MyToken(ARRAY, yytext(), yyline, yycolumn);
          }
        case 66: break;
        case 17: 
          { new MyToken(LBRAC, yytext(), yyline, yycolumn);
          }
        case 67: break;
        case 4: 
          { new MyToken(DIV, yytext(), yyline, yycolumn);
          }
        case 68: break;
        case 38: 
          { new MyToken(CONST, yytext(), yyline, yycolumn);
          }
        case 69: break;
        case 41: 
          { new MyToken(RECORD, yytext(), yyline, yycolumn);
          }
        case 70: break;
        case 9: 
          { new MyToken(EQ, yytext(), yyline, yycolumn);
          }
        case 71: break;
        case 42: 
          { new MyToken(MODULE, yytext(), yyline, yycolumn);
          }
        case 72: break;
        case 15: 
          { new MyToken(LPAR, yytext(), yyline, yycolumn);
          }
        case 73: break;
        case 3: 
          { System.out.println("ID " + yytext());
          }
        case 74: break;
        case 25: 
          { new MyToken(DO, yytext(), yyline, yycolumn);
          }
        case 75: break;
        case 27: 
          { new MyToken(END, yytext(), yyline, yycolumn);
          }
        case 76: break;
        case 26: 
          { new MyToken(IF, yytext(), yyline, yycolumn);
          }
        case 77: break;
        case 22: 
          { new MyToken(LOEQ, yytext(), yyline, yycolumn);
          }
        case 78: break;
        case 21: 
          { new MyToken(ASSIGN, yytext(), yyline, yycolumn);
          }
        case 79: break;
        case 11: 
          { new MyToken(LO, yytext(), yyline, yycolumn);
          }
        case 80: break;
        case 43: 
          { new MyToken(PROCEDURE, yytext(), yyline, yycolumn);
          }
        case 81: break;
        case 18: 
          { new MyToken(RBRAC, yytext(), yyline, yycolumn);
          }
        case 82: break;
        case 7: 
          { new MyToken(MINUS, yytext(), yyline, yycolumn);
          }
        case 83: break;
        case 10: 
          { new MyToken(NEQ, yytext(), yyline, yycolumn);
          }
        case 84: break;
        case 5: 
          { new MyToken(MUL, yytext(), yyline, yycolumn);
          }
        case 85: break;
        case 23: 
          { new MyToken(HIEQ, yytext(), yyline, yycolumn);
          }
        case 86: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return YYEOF;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Scanner1 <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        Scanner1 scanner = null;
        try {
          scanner = new Scanner1( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}