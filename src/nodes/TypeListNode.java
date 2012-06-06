package nodes;

import java.util.List;

public class TypeListNode extends AbstractNode {
    private List<TypeNode> list;

    public TypeListNode(List<TypeNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
    	String s = "";
		for (TypeNode iN : list) {
			s += "\n" + iN.toString();
		}
        return "TypeListNode{" + s +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeListNode that = (TypeListNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
