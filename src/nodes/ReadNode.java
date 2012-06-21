/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class ReadNode extends AbstractNode {

    private static final long serialVersionUID = 1L;

    private final StringNode in;

    public ReadNode() {
        this(null);
    }

    public ReadNode(StringNode in) {
        this.in = in;
    }

    protected String toString(int indent) {
        return indent + "Read(" + in + ")";
    }

    @Override
    public AbstractDescr compile(SymbolTable syms) {
        write("READ, " + in.getValue());
        return null;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((in == null) ? 0 : in.hashCode());
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
        ReadNode other = (ReadNode) obj;
        if (in == null) {
            if (other.in != null)
                return false;
        } else if (!in.equals(other.in))
            return false;
        return true;
    }


}