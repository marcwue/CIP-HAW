package nodes;


import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.List;

public class VarListNode extends AbstractNode {
    private final List<VarNode> list;
    private int memSize = 0;

    public VarListNode(List<VarNode> list) {
        this.list = list;
    }

    public AbstractDescr compile(SymbolTable symboltable) {
        for (VarNode varNode : list) {
            memSize += varNode.compile(symboltable).getSize() * varNode.getSize();
        }
        return null;
    }

    public int getSize() {
        return memSize;
    }

    @Override
    public String toString() {
        String s = "";
        for (VarNode iN : list) {
            s += "\n" + iN.toString();
        }
        return indent() + "VarListNode" + s + "\n" + unindent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VarListNode that = (VarListNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
