/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
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
	
}
