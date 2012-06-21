/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc W�seke
 */
public class StatementNode extends AbstractNode {

    AbstractNode statement;

    /**
     * @param statement
     */
    public StatementNode(AbstractNode statement) {
        super();
        this.statement = statement;
    }

    public AbstractDescr compile(SymbolTable symbolTable) {
        return statement.compile(symbolTable);
    }

    @Override
    public String toString() {
        return "StatementNode{\n" +
                "statement=" + statement +
                '}';
    }

}
