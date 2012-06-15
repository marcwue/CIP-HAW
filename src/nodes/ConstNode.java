package nodes;

import descriptoren.IntConstDescr;
import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class ConstNode extends AbstractNode {
	private final AbstractNode ident;
	private final AbstractNode exp;

	public ConstNode(IdentNode ident, ExpressionNode exp) {
		this.ident = ident;
		this.exp = exp;
	}

	@Override
	public int getSize() {
		return 1;
	}

	public AbstractDescr compile(SymbolTable table) {
		IdentNode id = (IdentNode) ident;
		IntConstDescr value = null;
		if (exp instanceof BinOpNode) {
			value = new IntConstDescr(
					(Integer) ((BinOpNode) exp).getVal());
		} else if (exp instanceof IntNode) {
			value = new IntConstDescr(((IntNode) exp).getValue());
		} else {
			System.out.println("unsupported value: " + exp);
		}
		table.declare(id.getIdentName(), value);
		return value; // da nur in Tabelle geschrieben wird --> kein geeigneter
						// Rueckgabewert vorhanden
	}

	@Override
	public String toString() {
		return "ConstNode{" + "ident=" + ident + ", exp=" + exp + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ConstNode constNode = (ConstNode) o;

		if (exp != null ? !exp.equals(constNode.exp) : constNode.exp != null)
			return false;
		if (ident != null ? !ident.equals(constNode.ident)
				: constNode.ident != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = ident != null ? ident.hashCode() : 0;
		result = 31 * result + (exp != null ? exp.hashCode() : 0);
		return result;
	}
}
