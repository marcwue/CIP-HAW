/**
 *
 */
package nodes;

import aufgabe2.MyToken;

/**
 * @author Marc Wüseke
 */
public class AssignmentNode extends AbstractNode {

    AbstractNode ident;
    AbstractNode value;

    /**
     * @param value1
     * @param ident
     */
    public AssignmentNode(AbstractNode ident, AbstractNode value1) {
        this.ident = ident;
        this.value = value1;
    }

    @Override
    public String toString() {
        return "AssignmentNode{" +
                "ident=" + ident +
                ", value=" + value +
                '}';
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		AssignmentNode other = (AssignmentNode) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
    
    

}
