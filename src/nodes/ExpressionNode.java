/**
 * 
 */
package nodes;

import aufgabe2.MyToken;

/**
 * @author Marc Wüseke
 *
 */
public class ExpressionNode extends AbstractNode{

	AbstractNode simpleExp1;
	MyToken relop;
	AbstractNode simpleExp2;
	
	/**
	 * @param simpleExp1
	 * @param relop
	 * @param simpleExp2
	 */
	public ExpressionNode(AbstractNode simpleExp1, MyToken relop,
			AbstractNode simpleExp2) {
		super();
		this.simpleExp1 = simpleExp1;
		this.relop = relop;
		this.simpleExp2 = simpleExp2;
	}
	
	
}
