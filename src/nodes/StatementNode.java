/**
 * 
 */
package nodes;

/**
 * @author Marc W�seke
 *
 */
public class StatementNode extends AbstractNode{

	AbstractNode statement;

	/**
	 * @param statement
	 */
	public StatementNode(AbstractNode statement) {
		super();
		this.statement = statement;
	}

	/* (non-Javadoc)
	 * @see nodes.AbstractNode#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return statement.toString();
	}
	
}
