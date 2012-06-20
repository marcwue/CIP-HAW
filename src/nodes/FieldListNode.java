package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.HashMap;

import app.TokenID;

import descriptoren.SimpleTypeDescr;

public class FieldListNode extends AbstractNode {
	private final AbstractNode identList;
	private final AbstractNode type;

	public FieldListNode(AbstractNode identList, AbstractNode type) {
		this.identList = identList;
		this.type = type;
	}

	@Override
	public AbstractDescr compile(SymbolTable table) {
		//tabelle schreiben
		AbstractDescr d = null;
		if (type instanceof IdentNode) {
			String s = ((IdentNode) type).getIdentName();
			if (s.equals("integer")) {
				d = new SimpleTypeDescr(TokenID.INT);
			} else if (s.equals("boolean")) {
				d = new SimpleTypeDescr(TokenID.BOOLEAN);
			} else if (s.equals("string")) {
				d = new SimpleTypeDescr(TokenID.STR);
			} else {
				d = table.descriptorFor(s);
			}
		} else {
			d = type.compile(table);
		}
		identList.compile(table, d);
		return null;
	}

	@Override
	public String toString() {
		return "FieldListNode{" + "node=" + identList + ", type=" + type + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FieldListNode that = (FieldListNode) o;

		if (identList != null ? !identList.equals(that.identList)
				: that.identList != null)
			return false;
		if (type != null ? !type.equals(that.type) : that.type != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = identList != null ? identList.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}
}
