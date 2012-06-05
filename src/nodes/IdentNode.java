package nodes;

import java.util.HashMap;

import cip.base.CodeGen;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;
import descriptoren.VarDescr;

public class IdentNode extends AbstractNode {
    private final String identName;

    public IdentNode(String identName) {
        this.identName = identName;
    }
    
    /* (non-Javadoc)
	 * @see nodes.AbstractNode#compile(java.util.HashMap)
	 */
	@Override
	public AbstractDescr compile(SymbolTable symbolTable) {
		write("PUSHI, "+symbolTable.addressOf(identName));
		return null;
	}

	/**
	 * @return the identName
	 */
	public String getIdentName() {
		return identName;
	}

	@Override
    public String toString() {
        return "IdentNode{" +
                "identName='" + identName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentNode identNode = (IdentNode) o;

        if (identName != null ? !identName.equals(identNode.identName) : identNode.identName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return identName != null ? identName.hashCode() : 0;
    }
}
