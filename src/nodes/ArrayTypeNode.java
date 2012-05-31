package nodes;

import cip.base.CodeGen;
import descriptoren.AbstractDescr;
import descriptoren.ArrayDescr;
import descriptoren.IntConstDescr;

import java.util.HashMap;

public class ArrayTypeNode extends AbstractNode {
    private final AbstractNode expression;
    private final AbstractNode basetype;

    public ArrayTypeNode(AbstractNode expression, AbstractNode type) {
        this.expression = expression;
        this.basetype = type;

    }

    public ArrayDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        AbstractDescr basedescr = null;
        int numelem;
        if (expression instanceof IntNode)
            numelem = ((IntNode) expression).getValue();
        else
            numelem = (
                    (IntConstDescr) CodeGen.search(
                            (
                                    (IdentNode) expression
                            ).getIdentName()
                    )
            ).getIntVal();
        if (basetype instanceof ArrayTypeNode) {
            basedescr = ((ArrayTypeNode) basetype).compile(symbolTable);
        } else if (basetype instanceof RecordTypeNode) {
            basedescr = ((RecordTypeNode) basetype).compile(symbolTable);
        } else {
            basedescr = CodeGen.search(((IdentNode) basetype).getIdentName());
        }
        return new ArrayDescr(numelem, basedescr.getSize() * numelem, basedescr);
    }

    @Override
    public String toString() {
        return "ArrayTypeNode{" + "expression=" + expression + ", basetype=" + basetype + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ArrayTypeNode that = (ArrayTypeNode) o;

        if (expression != null ? !expression.equals(that.expression) : that.expression != null)
            return false;
        if (basetype != null ? !basetype.equals(that.basetype) : that.basetype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expression != null ? expression.hashCode() : 0;
        result = 31 * result + (basetype != null ? basetype.hashCode() : 0);
        return result;
    }
}
