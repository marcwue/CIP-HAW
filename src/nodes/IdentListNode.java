package nodes;

import java.util.List;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class IdentListNode extends AbstractNode {

	private List<IdentNode> list;

	public IdentListNode(List<IdentNode> list) {
		this.list = list;
	}

	public AbstractDescr compile(SymbolTable symbolTable, AbstractDescr descr) {
		for (IdentNode node : list) {
			symbolTable.declare(node.getIdentName(), descr);
		}
		return null;
	}

	public AbstractDescr compileParams(SymbolTable table, AbstractDescr descriptor) {
		for (IdentNode node : list) {
			table.declareParams(node.getIdentName(), descriptor);
		}
		return null;
	}

	@Override
	public int getSize() {
		
		int retSize = 0;
		for (IdentNode node : list) {
			retSize += node.getSize();
		}
		return retSize;
	}

	@Override
	public String toString() {
		String s = "";
		for (IdentNode iN : list) {
			s += "\n" + iN.toString();
		}
		
		return indent() + "IdentListNode " + s + unindent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		IdentListNode that = (IdentListNode) o;

		if (list != null ? !list.equals(that.list) : that.list != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return list != null ? list.hashCode() : 0;
	}
}
