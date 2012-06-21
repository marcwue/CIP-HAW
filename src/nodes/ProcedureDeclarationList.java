package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.List;

public class ProcedureDeclarationList extends AbstractNode {
    private final List<AbstractNode> list;

    public ProcedureDeclarationList(List<AbstractNode> procListe) {
        this.list = procListe;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        for (AbstractNode procNode : list) {
            procNode.compile(symbolTable);
        }
        return null;
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
