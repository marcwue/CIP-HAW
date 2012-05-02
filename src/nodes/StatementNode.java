/**
 *
 */
package nodes;

/**
 * @author Marc W�seke
 */
public class StatementNode extends AbstractNode {

    AbstractNode statement;

    /**
     * @param statement
     */
    public StatementNode(AbstractNode statement) {
        super();
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "StatementNode{" +
                "statement=" + statement +
                '}';
    }

}
