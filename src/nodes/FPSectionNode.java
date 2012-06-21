package nodes;

import app.TokenID;
import descriptoren.AbstractDescr;
import descriptoren.SimpleTypeDescr;
import descriptoren.SymbolTable;

public class FPSectionNode extends AbstractNode {
    private final AbstractNode node;
    private final AbstractNode type;
    private AbstractDescr sectionType;

    public FPSectionNode(AbstractNode node, AbstractNode type) {
        this.node = node;
        this.type = type;
    }

    public AbstractDescr compile(SymbolTable table) {
        AbstractDescr d = null;
        if (type instanceof IdentNode) {
            String s = ((IdentNode) type).getIdentName();
            if (s.equals("integer")) {
                d = new SimpleTypeDescr(TokenID.INT);
            } else if (s.equals("boolean")) {
                d = new SimpleTypeDescr(TokenID.BOOLEAN);
            } else if (s.equals("string")) {
                d = new SimpleTypeDescr(TokenID.STR);
            } else {
                d = table.descriptorFor(s);
            }
        } else {
            d = type.compile(table);
        }

        IdentListNode n = (IdentListNode) node;
        n.compileParams(table, d);
        sectionType = d;

        return null;
    }

    public int size() {
        if (sectionType == null)
            return -1;
        IdentListNode n = (IdentListNode) node;
        return n.getSize() * sectionType.getSize();
    }

    @Override
    public String toString() {
        return "FPSectionNode" + node + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FPSectionNode that = (FPSectionNode) o;

        if (node != null ? !node.equals(that.node) : that.node != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = node != null ? node.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
