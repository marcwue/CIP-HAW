package nodes;


import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.HashMap;
import java.util.List;

public class VarListNode extends AbstractNode{
    private final List<VarNode> list;

    public VarListNode(List<VarNode> list) {
        this.list = list;
    }

    public AbstractDescr compile(SymbolTable symboltable) {
        for(VarNode node : list) {
            node.compile(symboltable);
        }
        return null;
    }

    @Override
    public String toString() {
    	String s = "";
		for (VarNode iN : list) {
			s += "\n" + iN.toString();
		}
        return "VarListNode{" + s + "\n" +
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
