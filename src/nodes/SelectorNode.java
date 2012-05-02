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
	IdentNode subject;
	List<AbstractNode> selectors = new LinkedList<AbstractNode>();
	/**
	 * @param subject
	 * @param selectors
	 */
	public SelectorNode(IdentNode subject, List<AbstractNode> selectors) {
		super();
		this.subject = subject;
		this.selectors = selectors;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelectorNode [subject=" + subject + ", selectors=" + selectors
				+ "]";
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
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		SelectorNode other = (SelectorNode) obj;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
