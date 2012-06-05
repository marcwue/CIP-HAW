package nodes;

import java.io.Serializable;
import java.util.HashMap;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public abstract class AbstractNode implements Serializable {

	private static final long serialVersionUID = -5560813573289386664L;

	public AbstractNode() {
	}

	public AbstractDescr compile(SymbolTable symbolTable){
		return null;
	}

	public void write(String s){
		//TODO ausgabe in Datei einbauen
		System.out.println(s);
	}
}
