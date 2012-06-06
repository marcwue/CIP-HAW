package nodes;

import java.util.List;

public class StatementSequenceNode extends AbstractNode{
    private final List<AbstractNode> list;

    public StatementSequenceNode(List<AbstractNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "StatemantSequenceNode{\n" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatementSequenceNode that = (StatementSequenceNode) o;

        if (list != null ? !list.equals(that.list) : that.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
