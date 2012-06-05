package nodes;

import descriptoren.AbstractDescr;

import java.util.HashMap;

public class FieldListNode extends AbstractNode {
    private final AbstractNode identList;
    private final AbstractNode type;

    public FieldListNode(AbstractNode identList, AbstractNode type) {
        this.identList = identList;
        this.type = type;
    }

    @Override
    public AbstractDescr compile(AbstractDescr d, HashMap<String, AbstractDescr> symbolTable) {
//        identList.compile(d, symbolTable);
    	return null;
    }


    @Override
    public String toString() {
        return "FieldListNode{" +
                "node=" + identList +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldListNode that = (FieldListNode) o;

        if (identList != null ? !identList.equals(that.identList) : that.identList != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identList != null ? identList.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
