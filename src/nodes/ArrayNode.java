package nodes;

import descriptoren.AbstractDescr;
import descriptoren.ArrayDescr;
import descriptoren.IntConstDescr;
import descriptoren.SymbolTable;

public class ArrayNode extends AbstractNode {
    private final AbstractNode expression;
    private final AbstractNode basetype;

    public ArrayNode(AbstractNode expression, AbstractNode type) {
        this.expression = expression;
        this.basetype = type;

    }

    public AbstractDescr compile(SymbolTable systemTable) {
        int size;
        if (expression instanceof IdentNode) {
            size = ((IntConstDescr) systemTable
                    .descriptorFor(((IdentNode) expression).getIdentName()))
                    .value();
        } else if (expression instanceof ContentNode) {
            size = ((IntConstDescr) systemTable
                    .descriptorFor((((IdentNode) ((ContentNode) expression)
                            .getSubject()).getIdentName()))).value();
        } else {
            size = ((IntNode) expression).getValue();
        }

        AbstractDescr descr = null;
        if (basetype instanceof ArrayNode || basetype instanceof RecordTypeNode) {
            descr = basetype.compile(systemTable);
        } else if (basetype instanceof IdentNode) {
            String identName = ((IdentNode) basetype).getIdentName();
            descr = systemTable.descriptorFor(identName);

            if (descr == null) {
                System.out.println("unknown type: " + identName);
            }
        } else {
            System.out.println("unsupported type: " + basetype);
        }

        return new ArrayDescr(size, descr);
    }

    @Override
    public String toString() {
        return indent() + "ArrayTypeNode\n" + expression + "\n" + basetype + unindent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ArrayNode that = (ArrayNode) o;

        if (expression != null ? !expression.equals(that.expression)
                : that.expression != null)
            return false;
        if (basetype != null ? !basetype.equals(that.basetype)
                : that.basetype != null)
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
