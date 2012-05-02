/**
 *
 */
package nodes;

/**
 * @author Marc W�seke
 */
public class TermNode extends AbstractNode {

    AbstractNode term;

    /**
     * @param term
     */
    public TermNode(AbstractNode term) {
        super();
        this.term = term;
    }

    @Override
    public String toString() {
        return "TermNode{" +
                "term=" + term +
                '}';
    }

}
