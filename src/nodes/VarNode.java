package nodes;

public class VarNode extends AbstractNode {
    private final IdentListNode ident;
    private final AbstractNode type;

    public VarNode(IdentListNode ident, AbstractNode type) {
        this.ident = ident;
        this.type = type;
    }

    @Override
    public String toString() {
        return "VarNode{" +
                "ident=" + ident +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VarNode varNode = (VarNode) o;

        if (ident != null ? !ident.equals(varNode.ident) : varNode.ident != null) return false;
        if (type != null ? !type.equals(varNode.type) : varNode.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ident != null ? ident.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}