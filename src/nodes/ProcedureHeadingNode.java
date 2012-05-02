package nodes;

public class ProcedureHeadingNode extends AbstractNode {
    private final IdentNode subject;
    private final FormalParametersNode fparams;

    public ProcedureHeadingNode(IdentNode subject, FormalParametersNode fparams) {
        this.subject = subject;
        this.fparams = fparams;
    }

    @Override
    public String toString() {
        return "ProcedureHeadingNode{" +
                "subject=" + subject +
                ", fparams=" + fparams +
                '}';
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
}
