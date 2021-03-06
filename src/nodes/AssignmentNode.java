/**
 *
 */
package nodes;

import descriptoren.*;

/**
 * @author Marc Wüseke
 */
public class AssignmentNode extends AbstractNode {

    private final AbstractNode selector;
    private final AbstractNode expression;

    /**
     * @param expression
     * @param selector
     */
    public AssignmentNode(AbstractNode selector, AbstractNode expression) {
        this.selector = selector;
        this.expression = expression;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        if (selector instanceof IdentNode
                && symbolTable.descriptorFor(((IdentNode) selector).getIdentName()) instanceof IntConstDescr) {
            System.out.println("Constant "
                    + ((IdentNode) selector).getIdentName()
                    + " cannot be overriden");
        }
        AbstractDescr rightDescr = expression.compile(symbolTable);
        if (rightDescr instanceof RecordDescr || rightDescr instanceof ArrayDescr) {
            if (((ContentNode) expression).getSubject() instanceof IdentNode) {
                symbolTable.link(((IdentNode) selector).getIdentName(),
                        ((IdentNode) ((ContentNode) expression).getSubject()).getIdentName());
            } else if (((ContentNode) expression).getSubject() instanceof ArraySelectorNode) {
                AbstractDescr leftDescr = selector.compile(symbolTable);

                if (!leftDescr.equals(rightDescr)) {
                    System.out.println("left type (" + leftDescr
                            + ") is not compatible with right type ("
                            + rightDescr + ")");
                }

                write("ASSIGN, " + rightDescr.getSize());
            } else {
                System.out
                        .println("nur IdentNodes und ArraySelectorNodes");
            }
        } else {
            selector.compile(symbolTable);
            write("ASSIGN, 1");
        }
        return null;
    }

    @Override
    public String toString() {
        return indent() + "AssignmentNode\n" + selector + "\n"
                + expression + "\n" + unindent();
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((selector == null) ? 0 : selector.hashCode());
        result = prime * result
                + ((expression == null) ? 0 : expression.hashCode());
        return result;
    }

    /*
      * (non-Javadoc)
      *
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
