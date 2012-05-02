package nodes;

public class ProcedureBodyNode extends AbstractNode {
    private final DeclarationsNode declarations;
    private final StatementSequenceNode statseq;

    public ProcedureBodyNode(DeclarationsNode declarations, StatementSequenceNode statseq) {
        this.declarations = declarations;
        this.statseq = statseq;
    }

    @Override
    public String toString() {
        return "ProcedureBodyNode{" +
                "declarations=" + declarations +
                ", statseq=" + statseq +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureBodyNode that = (ProcedureBodyNode) o;

        if (declarations != null ? !declarations.equals(that.declarations) : that.declarations != null) return false;
        if (statseq != null ? !statseq.equals(that.statseq) : that.statseq != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = declarations != null ? declarations.hashCode() : 0;
        result = 31 * result + (statseq != null ? statseq.hashCode() : 0);
        return result;
    }
}
