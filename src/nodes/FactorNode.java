/**
 *
 */
package nodes;

/**
 * @author Marc Wüseke
 */
public class FactorNode extends AbstractNode {

    Integer valueInt = null;
    String valueString = null;

    /**
     * @param value
     */
    public FactorNode(int value) {
        valueInt = new Integer(value);
    }

    public FactorNode(String value) {
        this.valueString = value;
    }

    @Override
    public String toString() {
        return "FactorNode{" +
                "valueInt=" + valueInt +
                ", valueString='" + valueString + '\'' +
                '}';
    }

}
