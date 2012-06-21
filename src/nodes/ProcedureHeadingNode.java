package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class ProcedureHeadingNode extends AbstractNode {
    private final IdentNode subject;
    private final FormalParametersNode fparams;
    SymbolTable symbolTableL;

    public ProcedureHeadingNode(IdentNode subject, FormalParametersNode fparams) {
        this.subject = subject;
        this.fparams = fparams;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {

        symbolTableL = new SymbolTable(symbolTable);

        if (fparams != null) {
            fparams.compile(symbolTableL);
        }
        return null;
    }

    public SymbolTable getProcSymbolTable() {
        return symbolTableL;
    }

    @Override
    public String toString() {
        return "ProcedureHeadingNode{" + subject + "\n" + fparams
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureHeadingNode that = (ProcedureHeadingNode) o;

        if (fparams != null ? !fparams.equals(that.fparams) : that.fparams != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (fparams != null ? fparams.hashCode() : 0);
        return result;
    }


    public IdentNode getSubject() {
        return subject;
    }

    public FormalParametersNode getFparams() {
        return fparams;
    }
}
