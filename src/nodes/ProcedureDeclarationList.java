package nodes;

import java.util.List;

public class ProcedureDeclarationList extends AbstractNode {
    private final List<ProcedureDeclarationNode> list;

    public ProcedureDeclarationList(List<ProcedureDeclarationNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ProcedureDeclarationList{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureDeclarationList that = (ProcedureDeclarationList) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
