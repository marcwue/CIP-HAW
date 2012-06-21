/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class PrintNode extends AbstractNode {
    private static final long serialVersionUID = 1L;

    private AbstractNode expr;

    public PrintNode(AbstractNode expr) {
        this.expr = expr;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        expr.compile(symbolTable);
        write("PRINT");
        return null;
    }

    protected String toString(int indent) {
        return indent() + "PrintNode\n" + indent + unindent();
    }

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expr == null) ? 0 : expr.hashCode());
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
        PrintNode other = (PrintNode) obj;
        if (expr == null) {
            if (other.expr != null)
                return false;
        } else if (!expr.equals(other.expr))
            return false;
        return true;
    }


}