package aufgabe1;

public class Parser {
    static MyFlexScanner scanner = null;

    public static void main(String[] argv) {
        System.out.println("MyStandalone Version 0.1");

        if (argv.length == 0) {
            System.out.println("Usage : java MyStandalone <inputfile>");
        } else {
            for (int i = 0; i < argv.length; i++) {
                try {
                    scanner = new MyFlexScanner(new java.io.FileReader(argv[i]));
                    
                    MyToken nextsymbol;
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
    }
}
