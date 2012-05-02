package nodes;

public class ModuleNode extends AbstractNode{
    private final IdentNode ident;
    private final AbstractNode declaration;
    private final StatementSequenceNode statementSequence;

    public ModuleNode(IdentNode ident, AbstractNode declaration, StatementSequenceNode statementSequence) {
        this.ident = ident;
        this.declaration = declaration;
        this.statementSequence = statementSequence;
    }

    @Override
    public String toString() {
        return "ModuleNode{" +
                "ident=" + ident +
                ", declaration=" + declaration +
                ", statementSequence=" + statementSequence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleNode that = (ModuleNode) o;

        if (declaration != null ? !declaration.equals(that.declaration) : that.declaration != null) return false;
        if (ident != null ? !ident.equals(that.ident) : that.ident != null) return false;
        if (statementSequence != null ? !statementSequence.equals(that.statementSequence) : that.statementSequence != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ident != null ? ident.hashCode() : 0;
        result = 31 * result + (declaration != null ? declaration.hashCode() : 0);
        result = 31 * result + (statementSequence != null ? statementSequence.hashCode() : 0);
        return result;
    }
}
