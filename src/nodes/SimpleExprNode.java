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

	/* (non-Javadoc)
	 * @see nodes.AbstractNode#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return term.toString();
	}
	
	
	
}
