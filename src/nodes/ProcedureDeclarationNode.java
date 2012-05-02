package nodes;

public class ProcedureDeclarationNode {
    private final ProcedureHeadingNode head;
    private final ProcedureBodyNode body;

    public ProcedureDeclarationNode(ProcedureHeadingNode head, ProcedureBodyNode body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ProcedureDeclarationNode{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureDeclarationNode that = (ProcedureDeclarationNode) o;

        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (head != null ? !head.equals(that.head) : that.head != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
