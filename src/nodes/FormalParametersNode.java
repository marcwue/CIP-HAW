package nodes;

import java.util.ArrayList;
import java.util.List;

public class FormalParametersNode extends AbstractNode {
    private final List<FPSectionNode> fpsections;

    public FormalParametersNode(List<FPSectionNode> fpsections) {
        this.fpsections = new ArrayList<FPSectionNode>(fpsections);
    }

    @Override
    public String toString() {
        return "FormalParametersNode{" +
                "fpsections=" + fpsections +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormalParametersNode that = (FormalParametersNode) o;

        if (fpsections != null ? !fpsections.equals(that.fpsections) : that.fpsections != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fpsections != null ? fpsections.hashCode() : 0;
    }
}
