package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class TypeNode extends AbstractNode {
	private final IdentNode ident;
	private final AbstractNode type;

	public TypeNode(IdentNode ident, AbstractNode type) {
		this.ident = ident;
		this.type = type;
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public AbstractDescr compile(SymbolTable syms) {

		AbstractDescr descr = null;
		String identName = ((IdentNode) ident).getIdentName();

		if (type instanceof IdentNode) {
			descr = syms.descriptorFor(identName);
		} else if (type instanceof ArrayNode
				|| type instanceof RecordTypeNode) {
			descr = type.compile(syms);
		} else {
			System.out.println("unsupported type: " + type);
		}

		syms.declareType(identName, descr);

		return descr;
	}

	@Override
	public String toString() {
		return "TypeNode{" + "ident=" + ident + ", type=" + type + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TypeNode typeNode = (TypeNode) o;

		if (ident != null ? !ident.equals(typeNode.ident)
				: typeNode.ident != null)
			return false;
		if (type != null ? !type.equals(typeNode.type) : typeNode.type != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = ident != null ? ident.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}
}
