package Binary;

import Miscellaneous.Expression;
import Miscellaneous.Num;
import Unary.Neg;

import java.util.Map;
import java.util.Objects;

/**
 * The type represents a subtraction object.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Instantiates a new Minus.
     *
     * @param expression1 the expression 1
     * @param expression2 the expression 2
     */
    public Minus(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a map of variable names to their corresponding values
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return new Minus(new Num(this.getExpression1().evaluate(assignment)),
                new Num(this.getExpression2().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    public double evaluate() throws Exception {
        return this.getExpression1().evaluate() - this.getExpression2().evaluate();
    }

    /**
     * Returns a String representation of the Minus object.
     * The returned String will be in the format of "(expression1
     * - expression2)".
     *
     * @return a String representing the Minus object.
     */
    @Override
    public String toString() {
        return ("(" + this.getExpression1().toString() + " - "
                + this.getExpression2().toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the name of the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(this.getExpression1().assign(var, expression),
                this.getExpression2().assign(var, expression));
    }

    /**
     * Returns the derivative of the expression differentiated according to the
     * specified variable inserted.
     *
     * @param var the variable by which the expression is differentiated
     * @return the derivative of the expression
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(this.getExpression1().differentiate(var),
                this.getExpression2().differentiate(var));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     * @throws Exception if an error occurs during simplification
     */
    public Expression simplify() { // shouldn't throw exception
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            Expression simplified1 = this.getExpression1().simplify();
            Expression simplified2 = this.getExpression2().simplify();
            // simplified str is the string representations of the simplified
            // expressions for comparison purpose.
            String simplified1str = simplified1.toString();
            String simplified2str = simplified2.toString();
                // X-0
            if (Objects.equals(simplified2str, new Num(0).toString())) {
                return simplified1;
                // 0-X
            } else if (Objects.equals(simplified1str, new Num(0).toString())) {
                return new Neg(simplified2);
                // X-X
            } else if (simplified1str.equals(simplified2str)) {
                return new Num(0);
                // All other cases
            } else {
                return new Minus(simplified1, simplified2);
            }
        }
    }
}
