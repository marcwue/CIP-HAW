package descriptoren;

import nodes.*;

public class ArrayDescr extends AbstractDescr {
	private static final long serialVersionUID = 1L;
	private int numberOfElements;
	private AbstractDescr basetype;

	public ArrayDescr(int numberOfElements, AbstractDescr basetype) {
		this.numberOfElements = numberOfElements;
		this.basetype = basetype;
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