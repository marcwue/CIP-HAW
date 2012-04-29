/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class SimpleExprNode extends AbstractNode{

	AbstractNode term;

	/**
	 * @param expr
	 */
	public SimpleExprNode(AbstractNode term) {
		super();
		this.term = term;
	}
	
	
}
