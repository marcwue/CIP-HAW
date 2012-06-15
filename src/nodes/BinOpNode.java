/**
 *
 */
package nodes;

import descriptoren.AbstractDescr;
import descriptoren.SymbolTable;
import app.TokenID;

/**
 * @author Marc Wüseke
 */
public class BinOpNode extends AbstractNode {

	TokenID token;
	AbstractNode left;
	AbstractNode right;

	/**
	 * @param token
	 * @param left
	 * @param right
	 */
	public BinOpNode(TokenID token, AbstractNode left, AbstractNode right) {
		super();
		this.token = token;
		this.left = left;
		this.right = right;
	}

	public Object getVal() {
		if (token.equals(TokenID.MUL)) {
			return (Integer) left.getValue() * (Integer) right.getValue();
		} else if (token.equals(TokenID.DIV)) {
			return (Integer) left.getValue() / (Integer) right.getValue();
		} else if (token.equals(TokenID.MINUS)) {
			return (Integer) left.getValue() - (Integer) right.getValue();
		} else if (token.equals(TokenID.PLUS)) {
			if (left.getValue() instanceof Integer) {
				return (Integer) left.getValue() + (Integer) right.getValue();
			}
			// evtl nicht nötig bzw es ist nicht in oberon spezifiziert
			else {
				return (String) left.getValue() + (String) right.getValue();
			}
		} else {
			return null;
		}
	}

	@Override
	public AbstractDescr compile(SymbolTable symbolTable) {
		left.compile(symbolTable);
		if (left instanceof IdentNode)
			write("CONT, 1");
		right.compile(symbolTable);
		if (right instanceof IdentNode)
			write("CONT, 1");

		if (token.equals(TokenID.MUL)) {
			write("MUL");
		} else if (token.equals(TokenID.DIV)) {
			write("DIV");
		} else if (token.equals(TokenID.MINUS)) {
			write("SUB");
		} else if (token.equals(TokenID.PLUS)) {
			write("ADD");
		} else if (token.equals(TokenID.EQ)) {
			write("EQ");
		} else if (token.equals(TokenID.HI)) {
			write("GT");
		} else if (token.equals(TokenID.HIEQ)) {
			write("GE");
		} else if (token.equals(TokenID.LO)) {
			write("LT");
		} else if (token.equals(TokenID.LOEQ)) {
			write("LE");
		} else if (token.equals(TokenID.NEQ)) {
			write("NEQ");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TermNode [token=" + token + ", left=" + left + ", right="
				+ right + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinOpNode other = (BinOpNode) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
