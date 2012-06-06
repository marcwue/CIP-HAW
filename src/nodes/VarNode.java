package nodes;

import java.util.HashMap;

import cip.base.CodeGen;
import descriptoren.AbstractDescr;
import descriptoren.SimpleTypeDescr;
import descriptoren.SymbolTable;
import descriptoren.VarDescr;

public class VarNode extends AbstractNode {
	private final AbstractNode identList;
	private final AbstractNode type;

	public VarNode(IdentListNode ident, AbstractNode type) {
		this.identList = ident;
		this.type = type;
	}

	public AbstractDescr compile(SymbolTable symbolTable) {

		AbstractDescr d = null;
		if (type instanceof IdentNode) {
			String s = ((IdentNode) type).getIdentName();
			SimpleTypeDescr sd = null;
			if (s.equalsIgnoreCase("integer")) {
				sd = new SimpleTypeDescr("INTEGER");
			} else if (s.equalsIgnoreCase("boolean")) {
				sd = new SimpleTypeDescr("BOOLEAN");
			} else if (s.equalsIgnoreCase("string")) {
				sd = new SimpleTypeDescr("STRING");
			} else {
				System.out.println(
						"ToDo: Typedef in VarDeclarationNode");
			}
			d = sd;
		} else {
			d = type.compile(symbolTable);
		}
		identList.compile(symbolTable, d);
		return null;
	}

	@Override
	public String toString() {
		return "VarNode{" + "ident=" + identList + ", type=" + type + '}';
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
