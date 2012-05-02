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
}
