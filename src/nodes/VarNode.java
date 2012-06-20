package nodes;

import java.util.HashMap;

import app.TokenID;

import cip.base.CodeGen;
import descriptoren.AbstractDescr;
import descriptoren.SimpleTypeDescr;
import descriptoren.SymbolTable;
import descriptoren.VarDescr;

public class VarNode extends AbstractNode {
	private final IdentListNode identList;
	private final AbstractNode type;

	public VarNode(IdentListNode ident, AbstractNode type) {
		this.identList = ident;
		this.type = type;
	}

	public AbstractDescr compile(SymbolTable table) {
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
		return d;
	}

	public int getSize() {
		return identList.getSize();
	}

	@Override
	public String toString() {
		return indent() + "VarNode\n" + identList + "\n" + type + unindent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		VarNode varNode = (VarNode) o;

		if (identList != null ? !identList.equals(varNode.identList)
				: varNode.identList != null)
			return false;
		if (type != null ? !type.equals(varNode.type) : varNode.type != null)
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
