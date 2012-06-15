package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class ModuleNode extends AbstractNode {
	private final IdentNode ident;
	private final DeclarationsNode declaration;
	private final AbstractNode statementSequence;

	public ModuleNode(IdentNode ident, DeclarationsNode declaration,
			AbstractNode statementSequence) {
		this.ident = ident;
		this.declaration = declaration;
		this.statementSequence = statementSequence;
	}

	@Override
	public AbstractDescr compile(SymbolTable syms) {
		int label = getNextLabelNumber();
		write("JMP, " + label);
		declaration.compile(syms);
		write("LABEL, " + label);
		write("PUSHI, " + declaration.size());
		write("SETSP");
		statementSequence.compile(syms);

		// module node is the outermost node in a program
		// it has to tell the interpreter it reached the end of the code
		write("STOP");

		return null;
	}

	@Override
	public String toString() {
		return "ModuleNode{\n" + "ident=" + ident + "\n" + "declaration="
				+ declaration + "\n" + "statementSequence=" + statementSequence
				+ "\n" + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ModuleNode that = (ModuleNode) o;

		if (declaration != null ? !declaration.equals(that.declaration)
				: that.declaration != null)
			return false;
		if (ident != null ? !ident.equals(that.ident) : that.ident != null)
			return false;
		if (statementSequence != null ? !statementSequence
				.equals(that.statementSequence)
				: that.statementSequence != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = ident != null ? ident.hashCode() : 0;
		result = 31 * result
				+ (declaration != null ? declaration.hashCode() : 0);
		result = 31
				* result
				+ (statementSequence != null ? statementSequence.hashCode() : 0);
		return result;
	}
}
