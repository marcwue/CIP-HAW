/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class NegativNode extends AbstractNode{

	AbstractNode negativPart;

	/**
	 * @param negativPart
	 */
	public NegativNode(AbstractNode negativPart) {
		super();
		this.negativPart = negativPart;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NegativNode [negativPart=" + negativPart + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((negativPart == null) ? 0 : negativPart.hashCode());
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
		NegativNode other = (NegativNode) obj;
		if (negativPart == null) {
			if (other.negativPart != null)
				return false;
		} else if (!negativPart.equals(other.negativPart))
			return false;
		return true;
	}
	
	
}
