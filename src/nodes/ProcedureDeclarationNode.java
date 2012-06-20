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

	@Override
	public AbstractDescr compile(SymbolTable symbolTable) {

		SymbolTable lokal = new SymbolTable(symbolTable);
		int label = getNextLabelNumber();

		if (fparams != null) {
			fparams.compile(lokal);
		}

		if (declarations != null) {
			declarations.compile(lokal);
		}
		int allocatedMemory = linkage + lokal.size();
		write("LABEL, " + label);

		// entry Code starts here
		write("INIT, " + allocatedMemory);
		write("PUSHREG, RK");
		write("PUSHREG, FP");
		// to do: SL Register
		// SP := SP + lokale Variablen laenge
		write("GETSP");
		write("SETFP");
		write("GETSP");
		write("PUSHI, " + lokal.getSize());
		write("ADD");
		write("SETSP");
		// end of entryCode

		if (statseq != null) {
			statseq.compile(lokal);
		} else {
			error("Kein StatementSequenzNode");
		}

		// exitCode starts here
		// FP := SP
		write("GETFP");
		write("SETSP");
		// to do: restore SL
		write("POPREG, FP");
		write("POPREG, RK");
		write("GETSP");
		write("PUSHI, " + fparams.size());
		write("SUB");
		write("SETSP");

		int reduceVal = allocatedMemory + fparams.size();
		write("REDUCE, " + reduceVal);
		write("RET");

		ProcDescriptor descr = new ProcDescriptor(label, lokal);
		symbolTable.declare(ident.getIdentName(), descr);
		return descr;

	}

	@Override
	public String toString() {
		return "ProcedureDeclarationNode{" + "head=" + head + ", body=" + body
				+ '}';
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
