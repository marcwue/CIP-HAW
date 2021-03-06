package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.List;

public class ConstListNode extends AbstractNode {
    private List<ConstNode> list;
    int memSize = 0;

    public ConstListNode(List<ConstNode> list) {
        this.list = list;
    }

    public int getSize() {
        return memSize;
    }

    public AbstractDescr compileReturnAbstractNode(SymbolTable table) {
        System.out.println(list);
        for (AbstractNode constNode : list) {
            memSize += constNode.compile(table).getSize();
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (ConstNode iN : list) {
            s += "\n" + iN.toString();
        }
        return "ConstListNode{" + s + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ConstListNode that = (ConstListNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
