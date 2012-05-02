package nodes;

public class ConstNode extends AbstractNode {
    private final IdentNode ident;
    private final ExpressionNode exp;

    public ConstNode(IdentNode ident, ExpressionNode exp) {
        this.ident = ident;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "ConstNode{" +
                "ident=" + ident +
                ", exp=" + exp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstNode constNode = (ConstNode) o;

        if (exp != null ? !exp.equals(constNode.exp) : constNode.exp != null) return false;
        if (ident != null ? !ident.equals(constNode.ident) : constNode.ident != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ident != null ? ident.hashCode() : 0;
        result = 31 * result + (exp != null ? exp.hashCode() : 0);
        return result;
    }
}
