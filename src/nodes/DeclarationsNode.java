/**
 * 
 */
package nodes;
/**
 * @author Marc Wüseke
 *
 */
public class DeclarationsNode extends AbstractNode{
    private final ConstListNode con;
    private final TypeListNode type;
    private final VarListNode var;
	private final ProcedureDeclarationNode proc;

    public DeclarationsNode(ConstListNode con, TypeListNode type, VarListNode var, ProcedureDeclarationNode proc) {
        this.con = con;
        this.type = type;
        this.var = var;
        this.proc = proc;
    }

    @Override
    public String toString() {
        return "DeclarationsNode{" +
                "con=" + con +
                ", type=" + type +
                ", var=" + var +
                ", proc=" + proc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeclarationsNode that = (DeclarationsNode) o;

        if (con != null ? !con.equals(that.con) : that.con != null) return false;
        if (proc != null ? !proc.equals(that.proc) : that.proc != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (var != null ? !var.equals(that.var) : that.var != null) return false;

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
}
