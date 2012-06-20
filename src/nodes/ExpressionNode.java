package nodes;

import app.MyToken;

/**
 * Created by IntelliJ IDEA.
 * User: terence
 * Date: 02.05.12
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionNode extends AbstractNode{
    MyToken token;
    AbstractNode left;
    AbstractNode right;

    public ExpressionNode(MyToken token, AbstractNode left, AbstractNode right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return indent() + "ExpressionNode" +
                "\n" + token +
                "\n" + left +
                "\n" + right + 
                unindent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressionNode that = (ExpressionNode) o;

        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
