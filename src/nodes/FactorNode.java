/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class FactorNode extends AbstractNode{

	Integer valueInt = null;
	String valueString = null;
	/**
	 * @param type
	 * @param value
	 */
	public FactorNode(int value) {
		valueInt = new Integer(value);
	}

	public FactorNode(String value) {
		this.valueString = value;
	}

	/* (non-Javadoc)
	 * @see nodes.AbstractNode#toString()
	 */
	@Override
	public String toString() {
		if (valueInt == null)
			return valueInt+"";
		else
			return valueString;
	}
	
}
