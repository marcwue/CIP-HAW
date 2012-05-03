package nodes;

public class IdentNode extends AbstractNode {
    private final String identName;

    public IdentNode(String identName) {
        this.identName = identName;
    }

    @Override
    public String toString() {
        return "IdentNode{" +
                "identName='" + identName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentNode identNode = (IdentNode) o;

        if (identName != null ? !identName.equals(identNode.identName) : identNode.identName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return identName != null ? identName.hashCode() : 0;
    }
}
