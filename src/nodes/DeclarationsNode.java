/**
 * 
 */
package nodes;
/**
 * @author Marc Wüseke
 *
 */
public class DeclarationsNode extends AbstractNode{
	
	AbstractNode dec;

	/**
	 * @param dec
	 */
	public DeclarationsNode(AbstractNode dec) {
		super();
		this.dec = dec;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeclarationNode [dec=" + dec + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dec == null) ? 0 : dec.hashCode());
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
		DeclarationsNode other = (DeclarationsNode) obj;
		if (dec == null) {
			if (other.dec != null)
				return false;
		} else if (!dec.equals(other.dec))
			return false;
		return true;
	}
	
	
	
}
