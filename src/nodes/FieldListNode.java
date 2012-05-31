package nodes;

import descriptoren.AbstractDescr;

import java.util.HashMap;

public class FieldListNode extends AbstractNode {
    private final AbstractNode node;
    private final AbstractNode type;

    public FieldListNode(AbstractNode identList, AbstractNode type) {
        this.node = identList;
        this.type = type;
    }

    @Override
    public void compile(AbstractDescr d, HashMap<String, AbstractDescr> symbolTable) {
        node.compile(d, symbolTable);
    }


    @Override
    public String toString() {
        return "FieldListNode{" +
                "node=" + node +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldListNode that = (FieldListNode) o;

        if (node != null ? !node.equals(that.node) : that.node != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = node != null ? node.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
