package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class FormalParametersNode extends AbstractNode {
    private final List<FPSectionNode> fpsections;

    public FormalParametersNode(List<FPSectionNode> fpsections) {
        this.fpsections = new ArrayList<FPSectionNode>(fpsections);
    }

    public AbstractDescr compile(SymbolTable table) {
        for (FPSectionNode node : fpsections) {
            node.compile(table);
        }
        return null;
    }

    public int getSize() {
        int size = 0;
        for (FPSectionNode node : fpsections) {
            size += node.getSize();
        }
        return size;
    }

    @Override
    public String toString() {
        return indent() + "FormalParametersNode" + fpsections + unindent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FormalParametersNode that = (FormalParametersNode) o;

        if (fpsections != null ? !fpsections.equals(that.fpsections)
                : that.fpsections != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fpsections != null ? fpsections.hashCode() : 0;
    }
}
