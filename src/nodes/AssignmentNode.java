/**
 * 
 */
package nodes;

import aufgabe2.MyToken;

/**
 * @author Marc Wüseke
 *
 */
public class AssignmentNode extends AbstractNode{

	MyToken ident;
	AbstractNode value;
	
	/**
	 * @param value1
	 * @param ident
	 * @param value2
	 */
	public AssignmentNode(MyToken ident, AbstractNode value1) {
		this.ident = ident;
		this.value = value1;
	}
	
	
	
}
