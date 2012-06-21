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
	public AbstractDescr compile(SymbolTable systemTable) {
		int label = getNextLabelNumber();
		write("PUSHS, m");
		write("JMP, " + label);
		declaration.compile(systemTable);
		System.out.println(declaration);
		write("LABEL, " + label);
		write("PUSHI, " + declaration.getSize());
		write("SETSP");
		statementSequence.compile(systemTable);

		write("STOP");

		return null;
	}
	
	@Override
	public int getSize() {
		return this.declaration.getSize();
	}

	@Override
	public String toString() {
		return "ModuleNode\n" + 
				ident + "\n" +
				declaration + "\n" +
				statementSequence
				+ "\n";
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
