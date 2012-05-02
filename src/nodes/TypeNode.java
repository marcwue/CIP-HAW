package nodes;

public class TypeNode extends AbstractNode {
    private final IdentNode ident;
    private final AbstractNode type;

    public TypeNode(IdentNode ident, AbstractNode type) {
        this.ident = ident;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeNode{" +
                "ident=" + ident +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeNode typeNode = (TypeNode) o;

        if (ident != null ? !ident.equals(typeNode.ident) : typeNode.ident != null) return false;
        if (type != null ? !type.equals(typeNode.type) : typeNode.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ident != null ? ident.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
