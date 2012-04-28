/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class WhileNode extends AbstractNode {

	AbstractNode expression;
	AbstractNode statementSequence;
	/**
	 * @param expression
	 * @param statementSequence
	 */
	public WhileNode(AbstractNode expression, AbstractNode statementSequence) {
		super();
		this.expression = expression;
		this.statementSequence = statementSequence;
	}
	
	
}
