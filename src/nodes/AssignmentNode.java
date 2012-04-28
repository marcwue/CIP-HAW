/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class AssignmentNode extends AbstractNode{

	AbstractNode ident;
	AbstractNode value;
	
	/**
	 * @param value1
	 * @param ident
	 * @param value2
	 */
	public AssignmentNode(AbstractNode ident, AbstractNode value1) {
		this.ident = ident;
		this.value = value1;
	}
	
	
	
}
