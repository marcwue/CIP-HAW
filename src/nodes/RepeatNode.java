/**
 * 
 */
package nodes;

/**
 * @author Marc Wüseke
 *
 */
public class RepeatNode extends AbstractNode{
	AbstractNode exp1 = null;
	AbstractNode stateSeq1 = null;
	/**
	 * @param exp1
	 * @param stateSeq1
	 */
	public RepeatNode(AbstractNode exp1, AbstractNode stateSeq1) {
		super();
		this.exp1 = exp1;
		this.stateSeq1 = stateSeq1;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RepeatStatementNode [exp1=" + exp1 + ", stateSeq1=" + stateSeq1
				+ "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp1 == null) ? 0 : exp1.hashCode());
		result = prime * result
				+ ((stateSeq1 == null) ? 0 : stateSeq1.hashCode());
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
		RepeatNode other = (RepeatNode) obj;
		if (exp1 == null) {
			if (other.exp1 != null)
				return false;
		} else if (!exp1.equals(other.exp1))
			return false;
		if (stateSeq1 == null) {
			if (other.stateSeq1 != null)
				return false;
		} else if (!stateSeq1.equals(other.stateSeq1))
			return false;
		return true;
	}
	
	
}
