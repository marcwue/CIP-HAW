package nodes;

import java.util.ArrayList;
import java.util.List;

public class RecordTypeNode extends AbstractNode {
    private final List<FieldListNode> fieldLists;

    public RecordTypeNode(List<FieldListNode> fieldLists) {
        this.fieldLists = new ArrayList<FieldListNode>(fieldLists);
    }

    @Override
    public String toString() {
        return "RecordTypeNode{" +
                "fieldLists=" + fieldLists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordTypeNode that = (RecordTypeNode) o;

        if (fieldLists != null ? !fieldLists.equals(that.fieldLists) : that.fieldLists != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fieldLists != null ? fieldLists.hashCode() : 0;
    }
}
