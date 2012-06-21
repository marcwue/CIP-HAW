/**
 * 
 */
package nodes;

import java.util.List;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 * 
 */
public class DeclarationsNode extends AbstractNode {
	// geht nicht mit listen da dann die getSize
	// nicht funktionert aufgrund nullPointerException
	// private final ConstListNode con;
	// private final TypeListNode type;
	// private final VarListNode var;
	// private final ProcedureDeclarationList proc;
	private final List<? extends AbstractNode> con;
	private final List<? extends AbstractNode> type;
	private final List<? extends AbstractNode> var;
	private final List<? extends AbstractNode> proc;

	int memSize = 0;

	/**
	 * @param con
	 * @param type
	 * @param var
	 * @param proc
	 */
	public DeclarationsNode(List<? extends AbstractNode> con,
			List<? extends AbstractNode> type,
			List<? extends AbstractNode> var, List<? extends AbstractNode> proc) {
		super();
		this.con = con;
		this.type = type;
		this.var = var;
		this.proc = proc;
	}

	@Override
	public String toString() {
		String s = "";
		for (AbstractNode c : con) {
			s += c.toString() + "\n";
		}
		for (AbstractNode c : type) {
			s += c.toString() + "\n";
		}
		for (AbstractNode c : var) {
			s += c.toString() + "\n";
		}
		for (AbstractNode c : proc) {
			s += c.toString() + "\n";
		}
		return indent() + "DeclarationsNode\n" + s +  unindent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		DeclarationsNode that = (DeclarationsNode) o;

		if (con != null ? !con.equals(that.con) : that.con != null)
			return false;
		if (proc != null ? !proc.equals(that.proc) : that.proc != null)
			return false;
		if (type != null ? !type.equals(that.type) : that.type != null)
			return false;
		if (var != null ? !var.equals(that.var) : that.var != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = con != null ? con.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (var != null ? var.hashCode() : 0);
		result = 31 * result + (proc != null ? proc.hashCode() : 0);
		return result;
	}

	/**
	 * @return memSize
	 */
	public int getSize() {
		System.out.println(memSize);
		return memSize;
	}

	public AbstractDescr compile(SymbolTable symbolTable) {
		// wird nicht gehen wegen der getSize() auf
		// nullPointer
		// memSize += con.compile(table).getSize();
		//
		// memSize += type.compile(table).getSize();
		//
		// memSize += var.compile(table).getSize();
		//
		// memSize += proc.compile(table).getSize();

		for (AbstractNode constNode : con) {
			memSize += constNode.compile(symbolTable).getSize();
		}
		for (AbstractNode typeNode : type) {
			typeNode.compile(symbolTable);
		}
		for (AbstractNode varNode : var) {
			int varS = varNode.compile(symbolTable).getSize();

			memSize += varS * varNode.getSize();
			
		}
		for (AbstractNode procNode : proc) {
			procNode.compile(symbolTable);
		}

		return null;
	}
}
