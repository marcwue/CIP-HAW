/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;

/**
 * @author Marc Wüseke
 */
public class WhileNode extends AbstractNode {

	AbstractNode expression;
	AbstractNode statementSequence;

	/**
	 * @param expression
	 * @param statementSequence
	 */
	public WhileNode(AbstractNode expression, AbstractNode statementSequence) {
		super();
		this.expression = expression;
		this.statementSequence = statementSequence;
	}

	public AbstractDescr compile(SymbolTable table) {
		int labelWhile = getNextLabelNumber();
		int labelEnd = getNextLabelNumber();
		write("LABEL, " + labelWhile);
		expression.compile(table);
		write("BF, " + labelEnd);
		statementSequence.compile(table);
		write("JMP, " + labelWhile);
		write("LABEL, " + labelEnd);
		return null;

	}

	@Override
	public String toString() {
		return "WhileNode{" + "expression=" + expression
				+ ", statementSequence=" + statementSequence + '}';
	}
}
