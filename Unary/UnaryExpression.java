package Unary;

import Miscellaneous.BaseExpression;
import Miscellaneous.Expression;

import java.util.List;

/**
 * The unary expression abstract class represents a unary expression that has
 * one operand. It extends the BaseExpression class and contains an
 * Expression object.
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * The expression that is operated on by the unary operator.
     */
    private Expression expression;
    /**
     * Constructs a new `UnaryExpression` with the specified expression.
     *
     * @param expression The expression that is operated on by the unary operator.
     */

    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }
    /**
     * Returns the expression that is operated on by the unary operator.
     *
     * @return The expression that is operated on by the unary operator.
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Returns the variables that are used in the expression.
     *
     * @return The variables that are used in the expression.
     */
    public List<String> getVariables() {
        return this.expression.getVariables();
    }
}
