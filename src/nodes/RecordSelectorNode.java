/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.RecordDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class RecordSelectorNode extends SelectorNode {

    private static final long serialVersionUID = 1L;

    private final AbstractNode subject;
    private final AbstractNode selector;

    public RecordSelectorNode(IdentNode subject, AbstractNode selector) {
        this((AbstractNode) subject, selector);
    }

    public RecordSelectorNode(SelectorNode subject, AbstractNode selector) {
        this((AbstractNode) subject, selector);
    }

    private RecordSelectorNode(AbstractNode subject, AbstractNode selector) {
        this.subject = subject;
        this.selector = selector;
    }


    public String toString() {
        return indent() + "RecordSelectorNode\n"
                + subject + "\n"
                + selector + unindent();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((selector == null) ? 0 : selector.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecordSelectorNode other = (RecordSelectorNode) obj;
        if (selector == null) {
            if (other.selector != null)
                return false;
        } else if (!selector.equals(other.selector))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        return true;
    }

    public AbstractDescr compile(SymbolTable table) {
        //bei idents gleich pushi i
        if (subject instanceof IdentNode) {
            RecordDescr d = (RecordDescr) table.descriptorFor(((IdentNode) subject).getIdentName());
            subject.compile(table);
            int i = d.addressOf(((IdentNode) selector).getIdentName());
            write("PUSHI, " + i);
            write("ADD");
            return d.descriptorFor(((IdentNode) selector).getIdentName());
        } else {
            RecordDescr d = (RecordDescr) subject.compile(table);
            int address = d.addressOf(((IdentNode) selector).getIdentName());
            write("PUSHI, " + address);
            write("ADD");
            return d.descriptorFor(((IdentNode) selector).getIdentName());
        }
    }

}
