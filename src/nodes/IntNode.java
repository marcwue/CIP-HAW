package nodes;

import java.util.HashMap;

import cip.base.CodeGen;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

public class IntNode extends AbstractNode {
    private final int value;

    public IntNode(int value) {
        this.value = value;
    }

    public AbstractDescr compile(SymbolTable symbolTable){
    	write("PUSHI, "+value);
    	return super.compile(symbolTable); 
    }
    
    /**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	@Override
    public String toString() {
        return "IntNode{" +
                "value=" + value +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntNode intNode = (IntNode) o;

        return value == intNode.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}
