package app;

import cip.standaloneCodeGen.StandaloneCG;
import descriptoren.SymbolTable;
import nodes.AbstractNode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Interpreter {

    private final static String nodeFile = "nodeFile.txt";
    static MyFlexScanner scanner = null;
    static SymbolTable symbolTable = new SymbolTable();

    public static void main(String[] argv) {
        System.out.println("MyStandalone Version 0.1");

        if (argv.length != 1) {
            System.out.println("Usage : java MyStandalone <inputfile>");
            System.exit(-1);
        }

        try {
            System.out.println("\n### Scan:");
            scanner = new MyFlexScanner(new java.io.FileReader(argv[0]));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \"" + argv[0] + "\"");
        } catch (Exception e) {
            System.out.println("Unexpected exception:");
            e.printStackTrace();
        }

        System.out.println("\n### Parse:");
        Parser parser = new Parser(scanner);
        AbstractNode ergTree = parser.parse();

        System.out.println("\n### Tree:");
        System.out.println(ergTree);

        System.out.println("\n### Compile:");
        ergTree.compile(symbolTable);

        System.out.println("\n### SymbolTable:");
        System.out.println(symbolTable);

        System.out.println("\n### Code:");
        System.out.println(ergTree.getAssemblerCode());

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(nodeFile));
            os.writeUTF(ergTree.getAssemblerCode());
            os.flush();
        } catch (IOException ignored) {
        }

        System.out.println("### Standalone fu");
        String[] s = {nodeFile, "1"};
        try {
            StandaloneCG.main(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
