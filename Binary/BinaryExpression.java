package Binary;

import Miscellaneous.Expression;


import java.util.ArrayList;
import java.util.List;

/**
 * A binary expression is an expression that consists of two operands and an operator.
 */
public abstract class BinaryExpression {
    /**
     * The first operand of the expression.
     */
    private Expression expression1;
    /**
     * The second operand of the expression.
     */
    private Expression expression2;

    /**
     * Constructs a new binary expression with the given operands.
     *
     * @param expression1 The left operand of the expression.
     * @param expression2 The right operand of the expression.
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    /**
     * Gets the first operand of the expression.
     *
     * @return The first operand of the expression.
     */
    public Expression getExpression1() {
        return this.expression1;
    }

    /**
     * Gets the second operand of the expression.
     *
     * @return The second operand of the expression.
     */
    public Expression getExpression2() {
        return this.expression2;
    }

    /**
     * Gets a list of all the variables used in the expression.
     *
     * @return A list of all the variables used in the expression.
     */
    public List<String> getVariables() {
        List<String> merge = new ArrayList<>();
        merge.addAll(expression1.getVariables());
        for (String variable:expression2.getVariables()) {
            // if the list does not contain the variable already, add it.
            if (!(merge.contains(variable))) {
                merge.add(variable);
            }
        }
        return merge;
    }
}
