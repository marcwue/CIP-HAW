/**
 *
 */
package nodes;

import aufgabe2.MyToken;

/**
 * @author Marc W�seke
 */
public class BinOpNode extends AbstractNode {

	MyToken token;
    AbstractNode left;
    AbstractNode right;
	/**
	 * @param token
	 * @param left
	 * @param right
	 */
	public BinOpNode(MyToken token, AbstractNode left, AbstractNode right) {
		super();
		this.token = token;
		this.left = left;
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TermNode [token=" + token + ", left=" + left + ", right="
				+ right + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		BinOpNode other = (BinOpNode) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

    
}
