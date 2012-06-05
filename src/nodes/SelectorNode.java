/**
 * 
 */
package nodes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Marc Wüseke
 *
 */
public class SelectorNode extends AbstractNode{
	IdentNode identNode;
	List<AbstractNode> selectors = new LinkedList<AbstractNode>();
	/**
	 * @param subject
	 * @param selectors
	 */
	public SelectorNode(IdentNode subject, List<AbstractNode> selectors) {
		super();
		this.identNode = subject;
		this.selectors = selectors;
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
		SelectorNode other = (SelectorNode) obj;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		if (identNode == null) {
			if (other.identNode != null)
				return false;
		} else if (!identNode.equals(other.identNode))
			return false;
		return true;
	}
	/**
	 * @return the identNode
	 */
	public IdentNode getIdentNode() {
		return identNode;
	}
	/**
	 * @return the selectors
	 */
	public List<AbstractNode> getSelectors() {
		return selectors;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((selectors == null) ? 0 : selectors.hashCode());
		result = prime * result + ((identNode == null) ? 0 : identNode.hashCode());
		return result;
	}
	/**
	 * @param identNode the identNode to set
	 */
	public void setIdentNode(IdentNode identNode) {
		this.identNode = identNode;
	}
	/**
	 * @param selectors the selectors to set
	 */
	public void setSelectors(List<AbstractNode> selectors) {
		this.selectors = selectors;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectorNode [subject=" + identNode + ", selectors=" + selectors
				+ "]";
	}
	
	
}
