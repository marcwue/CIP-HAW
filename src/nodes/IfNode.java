/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class IfNode extends AbstractNode {

    AbstractNode exp1 = null;
    AbstractNode stateSeq1 = null;
    AbstractNode elseifs = null;
    AbstractNode stateSeq2 = null;

    /**
     * @param exp1
     * @param stateSeq1
     * @param elseifs
     * @param stateSeq2
     */
    public IfNode(AbstractNode exp1, AbstractNode stateSeq1,
                  AbstractNode elseifs, AbstractNode stateSeq2) {
        super();
        this.exp1 = exp1;
        this.stateSeq1 = stateSeq1;
        this.elseifs = elseifs;
        this.stateSeq2 = stateSeq2;
    }

    public int getCountOfElse() {
        return (elseifs != null ? (((IfNode) elseifs).getCountOfElse() + 1)
                : 0)
                + (stateSeq2 == null ? 0 : 1);
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        exp1.compile(symbolTable);
        write("BF, " + labelCount);
        stateSeq1.compile(symbolTable);
        int end = (AbstractNode.labelCount + getCountOfElse());
        write("JMP, " + end);
        if (elseifs != null) {
            write("LABEL, " + AbstractNode.getNextLabelNumber());
            ((IfNode) elseifs).compileElseIf(symbolTable, end);
        }
        if (stateSeq2 != null) {
            write("LABEL, " + AbstractNode.getNextLabelNumber());
            stateSeq2.compile(symbolTable);
        }
        write("LABEL, " + AbstractNode.getNextLabelNumber());
        return null;
    }

    public void compileElseIf(SymbolTable symbolTable, int end) {
        exp1.compile(symbolTable);
        write("BF, " + labelCount);
        stateSeq1.compile(symbolTable);
        write("JMP, " + end);
        if (elseifs != null) {
            write("LABEL, " + AbstractNode.getNextLabelNumber());
            ((IfNode) elseifs).compileElseIf(symbolTable, end);
        }
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "IfNode [exp1=" + exp1 + ", stateSeq1=" + stateSeq1
                + ", elseifs=" + elseifs + ", stateSeq2=" + stateSeq2 + "]";
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
        result = prime * result + ((elseifs == null) ? 0 : elseifs.hashCode());
        result = prime * result + ((exp1 == null) ? 0 : exp1.hashCode());
        result = prime * result
                + ((stateSeq1 == null) ? 0 : stateSeq1.hashCode());
        result = prime * result
                + ((stateSeq2 == null) ? 0 : stateSeq2.hashCode());
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
        IfNode other = (IfNode) obj;
        if (elseifs == null) {
            if (other.elseifs != null)
                return false;
        } else if (!elseifs.equals(other.elseifs))
            return false;
        if (exp1 == null) {
            if (other.exp1 != null)
                return false;
        } else if (!exp1.equals(other.exp1))
            return false;
        if (stateSeq1 == null) {
            if (other.stateSeq1 != null)
                return false;
        } else if (!stateSeq1.equals(other.stateSeq1))
            return false;
        if (stateSeq2 == null) {
            if (other.stateSeq2 != null)
                return false;
        } else if (!stateSeq2.equals(other.stateSeq2))
            return false;
        return true;
    }

}
