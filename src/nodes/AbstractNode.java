package nodes;

import java.io.Serializable;
import java.util.HashMap;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public abstract class AbstractNode implements Serializable {

	private static final long serialVersionUID = -5560813573289386664L;

	String assemblerCode = "";
	
	public AbstractNode() {
	}

	public AbstractDescr compile(SymbolTable symbolTable) {
		return null;
	}

	public AbstractDescr compile(SymbolTable symbolTable, AbstractDescr desct) {
		return null;
	}

	public void write(String s) {
		assemblerCode += s + "\n";
		System.out.println(s);
	}
	
	public String getAssemblerCode(){
		return assemblerCode;
	}
}
