/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 * 
 */
public class IfNode extends AbstractNode {

	AbstractNode condition;
	AbstractNode thenPart, elsePart;

	public IfNode() {
		condition = null;
		thenPart = null;
		elsePart = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1, AbstractNode fst2) {
		condition = fe;
		thenPart = fst1;
		elsePart = fst2;
	};
}
