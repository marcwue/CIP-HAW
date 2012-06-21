package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class ProcedureDeclarationNode extends AbstractNode{
	private final ProcedureHeadingNode head;
	private final ProcedureBodyNode body;

	public ProcedureDeclarationNode(ProcedureHeadingNode head,
			ProcedureBodyNode body) {
		this.head = head;
		this.body = body;
	}

	public AbstractDescr compile(SymbolTable symbolTable){
		this.head.compile(symbolTable);
		this.body.compile(symbolTable, head.getProcSymbolTable(), getParams().getSize());
		return null;
	}
	
	public FormalParametersNode getParams(){
		return head.getFparams();
	}

	@Override
	public String toString() {
		return indent() + "ProcedureDeclarationNode" + head + "\n" + body + unindent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProcedureDeclarationNode that = (ProcedureDeclarationNode) o;

		if (body != null ? !body.equals(that.body) : that.body != null)
			return false;
		if (head != null ? !head.equals(that.head) : that.head != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = head != null ? head.hashCode() : 0;
		result = 31 * result + (body != null ? body.hashCode() : 0);
		return result;
	}
}
