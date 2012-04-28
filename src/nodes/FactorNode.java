/**
 * 
 */
package nodes;

/**
 * @author Marc W�seke
 *
 */
public class FactorNode extends AbstractNode{

	int valueInt;
	String valueString;
	/**
	 * @param type
	 * @param value
	 */
	public FactorNode(int value) {
		this.valueInt = value;
	}

	public FactorNode(String value) {
		this.valueString = value;
	}
	
}
