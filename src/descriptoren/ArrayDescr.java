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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((basetype == null) ? 0 : basetype.hashCode());
		result = prime * result + numberOfElements;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayDescr other = (ArrayDescr) obj;
		if (basetype == null) {
			if (other.basetype != null)
				return false;
		} else if (!basetype.equals(other.basetype))
			return false;
		if (numberOfElements != other.numberOfElements)
			return false;
		return true;
	}
	
	
}