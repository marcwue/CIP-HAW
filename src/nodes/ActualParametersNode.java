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
public class ActualParametersNode extends AbstractNode{

	List<AbstractNode> list = new LinkedList<AbstractNode>();

	/**
	 * @param list
	 */
	public ActualParametersNode(List<AbstractNode> list) {
		super();
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActualParametersCall [list=" + list + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		ActualParametersNode other = (ActualParametersNode) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	
}
