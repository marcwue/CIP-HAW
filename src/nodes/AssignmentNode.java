/**
 *
 */
package nodes;

import aufgabe2.MyToken;

/**
 * @author Marc Wüseke
 */
public class AssignmentNode extends AbstractNode {

    MyToken ident;
    AbstractNode value;

    /**
     * @param value1
     * @param ident
     */
    public AssignmentNode(MyToken ident, AbstractNode value1) {
        this.ident = ident;
        this.value = value1;
    }

    @Override
    public String toString() {
        return "AssignmentNode{" +
                "ident=" + ident +
                ", value=" + value +
                '}';
    }

}
