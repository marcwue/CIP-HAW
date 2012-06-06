/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marc Wüseke
 */
public class AssignmentNode extends AbstractNode {

    AbstractNode selector;
    AbstractNode expression;

    /**
     * @param expression
     * @param selector
     */
    public AssignmentNode(AbstractNode selector, AbstractNode expression) {
        this.selector = selector;
        this.expression = expression;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
    	expression.compile(symbolTable);
    	selector.compile(symbolTable);
    	write("ASSIGN, 1");
    	return null;
    }

    @Override
    public String toString() {
        return "AssignmentNode{\n" +
                "ident=" + selector + "\n" +
                "value=" + expression + "\n" +
                "}\n";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((selector == null) ? 0 : selector.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentNode other = (AssignmentNode) obj;
		if (selector == null) {
			if (other.selector != null)
				return false;
		} else if (!selector.equals(other.selector))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		return true;
	}

}
