/**
 * 
 */
package nodes;

/**
 * @author Marc W�seke
 *
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

	/* (non-Javadoc)
	 * @see nodes.AbstractNode#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return term.toString();
	}
	
}
