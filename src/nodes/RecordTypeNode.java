package nodes;

import descriptoren.AbstractDescr;
import descriptoren.RecordDescr;
import descriptoren.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordTypeNode extends AbstractNode {
	private final List<FieldListNode> fieldLists;

	public RecordTypeNode(List<FieldListNode> fieldLists) {
		this.fieldLists = new ArrayList<FieldListNode>(fieldLists);
	}

	public AbstractDescr compile(SymbolTable table) {
		SymbolTable symbolTable = new SymbolTable(table); // neue Symboltabelle
		for (AbstractNode node : fieldLists) {
			node.compile(symbolTable); // schreibe Variablen in neue
										// Symboltabelle
		}
		return new RecordDescr(symbolTable);
	}

	@Override
	public String toString() {
		return "RecordTypeNode{" + "fieldLists=" + fieldLists + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RecordTypeNode that = (RecordTypeNode) o;

		if (fieldLists != null ? !fieldLists.equals(that.fieldLists)
				: that.fieldLists != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return fieldLists != null ? fieldLists.hashCode() : 0;
	}
}
