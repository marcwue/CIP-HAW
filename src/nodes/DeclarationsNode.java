/**
 * 
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 * 
 */
public class DeclarationsNode extends AbstractNode {
	private final ConstListNode con;
	private final TypeListNode type;
	private final VarListNode var;
	private final ProcedureDeclarationList proc;
	int memSize = 0;

	public DeclarationsNode(ConstListNode con, TypeListNode type,
			VarListNode var, ProcedureDeclarationList proc) {
		this.con = con;
		this.type = type;
		this.var = var;
		this.proc = proc;
	}

	@Override
	public String toString() {
		return "DeclarationsNode{" + "\n" + "con=" + con + "\n" + "type="
				+ type + "\n" + "var=" + var + "\n" + "proc=" + proc + "\n"
				+ '}';
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
	public int size() {
		return memSize;
	}

	public AbstractDescr compile(SymbolTable table) {
		
		memSize += con.compile(table).getSize();
		
		memSize += type.compile(table).getSize();
		
		memSize += var.compile(table).getSize();
		
		memSize += proc.compile(table).getSize();

		return null;

	}
}
