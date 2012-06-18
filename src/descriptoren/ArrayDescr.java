package descriptoren;

public class ArrayDescr extends AbstractDescr {
	private static final long serialVersionUID = 1L;
	private int numberOfElements;
	private AbstractDescr basetype;

	public ArrayDescr(int numberOfElements, AbstractDescr basetype) {
		this.numberOfElements = numberOfElements;
		this.basetype = basetype;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("ArrayDescriptor[ numberOfElements: " + numberOfElements
				+ "\n\t" + "baseType :" + basetype + "]\n");
		return result.toString();
	}

	public int numberOfElements() {
		return this.numberOfElements;
	}

	public AbstractDescr basetype() {
		return this.basetype;
	}

	public int getSize() {
		return basetype.getSize() * numberOfElements;
	}
}