/**
 *
 */
package nodes;

/**
 * @author Marc W�seke
 */
public class SimpleExprNode extends AbstractNode {

    AbstractNode term;

    public SimpleExprNode(AbstractNode term) {
        super();
        this.term = term;
    }

    @Override
    public String toString() {
        return "SimpleExprNode{" +
                "term=" + term +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleExprNode that = (SimpleExprNode) o;

        if (term != null ? !term.equals(that.term) : that.term != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return term != null ? term.hashCode() : 0;
    }
}
