/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.ProcDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class ProcedureCallNode extends AbstractNode {
    IdentNode ident = null;
    AbstractNode actualParameters = null;

    /**
     * @param ident
     * @param actualParameters
     */
    public ProcedureCallNode(IdentNode ident, AbstractNode actualParameters) {
        super();
        this.ident = ident;
        this.actualParameters = actualParameters;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        ProcDescr procedure = (ProcDescr) symbolTable.descriptorFor(ident.getIdentName());

        // Paramter auf den Stack legen
        if (actualParameters != null) {
            actualParameters.compile(symbolTable);
        }

        write("CALL, " + procedure.getLabelInAssembler());
        return null;

    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "ProcedureCallNode [ident=" + ident + ", actualParameters="
                + actualParameters + "]";
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
        result = prime
                * result
                + ((actualParameters == null) ? 0 : actualParameters.hashCode());
        result = prime * result + ((ident == null) ? 0 : ident.hashCode());
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
        ProcedureCallNode other = (ProcedureCallNode) obj;
        if (actualParameters == null) {
            if (other.actualParameters != null)
                return false;
        } else if (!actualParameters.equals(other.actualParameters))
            return false;
        if (ident == null) {
            if (other.ident != null)
                return false;
        } else if (!ident.equals(other.ident))
            return false;
        return true;
    }

}
