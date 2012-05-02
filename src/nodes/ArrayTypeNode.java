package nodes;

public class ArrayTypeNode extends AbstractNode {
    private final AbstractNode node;
    private final AbstractNode type;

    public ArrayTypeNode(AbstractNode expression, AbstractNode type) {
        this.node = expression;
        this.type = type;

    }

    @Override
    public String toString() {
        return "ArrayTypeNode{" +
                "node=" + node +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTypeNode that = (ArrayTypeNode) o;

        if (node != null ? !node.equals(that.node) : that.node != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = node != null ? node.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
