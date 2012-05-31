/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;

import java.util.HashMap;

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
    
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        return statement.compile(symbolTable);
    }

    @Override
    public String toString() {
        return "StatementNode{" +
                "statement=" + statement +
                '}';
    }

}
