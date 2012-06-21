package aufgabe1;

public class Parser {
    static MyFlexScanner scanner = null;
    static MyToken nextsymbol;
    static String[] argv;

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
        for (int i = 0; i < argv.length; i++) {
            try {
                scanner = new MyFlexScanner(new java.io.FileReader(argv[i]));

                while ((nextsymbol = scanner.yylex()) != null) {
                    System.out.println(nextsymbol.toString());
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("File not found : \"" + argv[i] + "\"");
            } catch (Exception e) {
                System.out.println("Unexpected exception:");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] argv) {
        System.out.println("MyStandalone Version 0.1");

        if (argv.length == 0) {
            System.out.println("Usage : java MyStandalone <inputfile>");
        } else {
            Parser.argv = argv;
            inSymbol();
        }
    }

}
