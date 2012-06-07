/**
 * 
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 *
 */
public class ContentNode extends AbstractNode {
    
    private static final long serialVersionUID = 1L;
    
    private AbstractNode subject;
    
    public ContentNode(IdentNode ident) {
        this((AbstractNode)ident);
    }
    
    public ContentNode(SelectorNode selector) {
        this((AbstractNode)selector);
    }
    
    private ContentNode(AbstractNode subject) {
        this.subject = subject;
    }

    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ContentNode other = (ContentNode) obj;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
    public AbstractDescr compile(SymbolTable symbolTable) {
        subject.compile(symbolTable);
        write("CONT, 1");
        return null;
    }

}