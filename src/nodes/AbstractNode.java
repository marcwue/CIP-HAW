package nodes;

import java.io.Serializable;
import java.util.HashMap;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public abstract class AbstractNode implements Serializable {

	private static final long serialVersionUID = -5560813573289386664L;
	public static int labelCount = 1;
	static String spaces = "";

	private static String assemblerCode = "";

	public AbstractNode() {
	}

	public static String indent(){
		spaces += "  ";
		return spaces;
	}
	
	public static String unindent(){
		spaces = spaces.substring(2);
		return "";
	}
	
	public static String getSpaces(){
		return spaces;
	}
	
	protected String toString(int indent, String str) {
		String indentStr = "";
		for (int i = 0; i < indent; ++i) {
			indentStr += " ";
		}

		return str.replaceAll("^", indentStr);
	}

	public AbstractDescr compile(SymbolTable symbolTable) {
		return null;
	}

	public AbstractDescr compileReturnAbstractNode(SymbolTable symbolTable) {
		return null;
	}

	public AbstractDescr compile(SymbolTable symbolTable, AbstractDescr desct) {
		return null;
	}

	public Object getValue() {
		return null;
	}

	public static int getNextLabelNumber() {
		return labelCount++;
	}

	public int getSize() {
		return 0;
	}

	public void write(String s) {
		assemblerCode += s + "\n";
	}

	public String getAssemblerCode() {
		return assemblerCode;
	}
}
