package nodes;

import java.util.HashMap;

import cip.base.CodeGen;
import descriptoren.AbstractDescr;
import descriptoren.VarDescr;

public class VarNode extends AbstractNode {
	private final AbstractNode identList;
	private final AbstractNode type;

	public VarNode(IdentListNode ident, AbstractNode type) {
		this.identList = ident;
		this.type = type;
	}

	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {

		AbstractDescr typeD = null;
		if (type instanceof IdentNode) {
			typeD = searchSymbolTable(CodeGen.level,
					((IdentNode) type).getIdentName());

		} else {
			// Case of Array and Record
			typeD = type.compile(symbolTable);

		}

		if (identList instanceof ListNode) {
			for (AbstractNode elem : ((ListNode) identList).getList()) {
				AbstractDescr varD = new VarDescr(CodeGen.level, address, typeD);
				symbolTable.get(CodeGen.level).put(
						((IdentNode) elem).getIdentName(), varD);
				address += typeD.getSize();

			}
		} else {
			AbstractDescr varD = new VarDescr(CodeGen.level, address, typeD);
			symbolTable.get(CodeGen.level).put(
					((IdentNode) identList).getIdentName(), varD);
			address += typeD.getSize();
			// varD.print();
		}
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
