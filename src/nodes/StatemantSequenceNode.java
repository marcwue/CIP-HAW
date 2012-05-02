package nodes;

import java.util.List;

public class StatemantSequenceNode {
    private final List<AbstractNode> list;

    public StatemantSequenceNode(List<AbstractNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "StatemantSequenceNode{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatemantSequenceNode that = (StatemantSequenceNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
