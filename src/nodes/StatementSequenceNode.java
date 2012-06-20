package nodes;

import java.util.List;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class StatementSequenceNode extends AbstractNode {
	private final List<AbstractNode> list;

	public StatementSequenceNode(List<AbstractNode> list) {
		this.list = list;
	}

	@Override
	public AbstractDescr compile(SymbolTable syms) {
		for (AbstractNode n : list) {
			n.compile(syms); 
		}

		return null;
	}

	@Override
	public String toString() {
		String s = "";
		for(AbstractNode a : list){
			s += a.toString() + "\n";
		}
		return indent() + "StatemantSequenceNode\n" + s + unindent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StatementSequenceNode that = (StatementSequenceNode) o;

		if (list != null ? !list.equals(that.list) : that.list != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return list != null ? list.hashCode() : 0;
	}
}
