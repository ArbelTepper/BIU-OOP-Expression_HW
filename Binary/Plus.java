package Binary;

import Miscellaneous.Expression;

import java.util.List;
import java.util.Map;

public class Plus extends BinaryExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public Plus (Expression expression1, Expression expression2) {
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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    public double evaluate() throws Exception {
        return expression1.evaluate() + expression2.evaluate();
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return ("(" + this.expression1.toString() + " + "
                + this.expression2.toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the name of the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.expression1.assign(var, expression),
                this.expression2.assign(var, expression));
    }

}
