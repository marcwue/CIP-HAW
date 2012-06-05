package nodes;

import java.util.List;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class IdentListNode extends AbstractNode {
    
	private List<IdentNode> list;

    public IdentListNode(List<IdentNode> list) {
        this.list = list;
    }

    public AbstractDescr compile(SymbolTable symbolTable, AbstractDescr descr){
    	for(IdentNode node : list){
    		symbolTable.declare(node.getIdentName(), descr);
    	}
    	return null;
    }
    
    @Override
    public String toString() {
        return "IdentListNode{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentListNode that = (IdentListNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
