package nodes;


import descriptoren.AbstractDescr;

import java.util.HashMap;
import java.util.List;

public class VarListNode extends AbstractNode{
    private final List<VarNode> list;

    public VarListNode(List<VarNode> list) {
        this.list = list;
    }

    public void compile(HashMap<String, AbstractDescr> symboltable) {
        for(VarNode node : list) {
            node.compile(symboltable);
        }
    }

    @Override
    public String toString() {
        return "VarListNode{" +
                "list=" + list +
                '}';
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
