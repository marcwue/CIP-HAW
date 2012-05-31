package nodes;

public class IntNode extends AbstractNode {
    private final int value;

    public IntNode(int value) {
        this.value = value;
    }

    /**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	@Override
    public String toString() {
        return "IntNode{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntNode intNode = (IntNode) o;

        return value == intNode.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}
