package app;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import nodes.AbstractNode;

public class Interpreter {
	
    private final static String nodeFile = "nodeFile.txt";
    static MyFlexScanner scanner = null;
	
	public static void main(String[] argv) {
		System.out.println("MyStandalone Version 0.1");

		if (argv.length == 0) {
			System.out.println("Usage : java MyStandalone <inputfile>");
		} else {

			for (int i = 0; i < argv.length; i++) {
				
				try {
					System.out.println("Scan:");
					scanner = new MyFlexScanner(new java.io.FileReader(argv[i])));

				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}

		}
		
		System.out.println("Parse:");
		Parser parser = new Parser(scanner);
		AbstractNode erg = parser.parse();
		System.out.println("Tree:");
		System.out.println(erg);
		
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nodeFile));
        os.writeObject(erg);
        os.flush();
        
        
	}
}
