package Binary;

import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.Map;
import java.util.Objects;

/**
 * The type Div represents a division object.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Div.
     *
     * @param expression1 the expression 1
     * @param expression2 the expression 2
     */
    public Div(Expression expression1, Expression expression2) {
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
        return new Div(new Num(this.getExpression1().evaluate(assignment)),
                new Num(this.getExpression2().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */

    public double evaluate() throws Exception {
        double exp2Result = this.getExpression2().evaluate();
        if (exp2Result == 0) {
            throw new RuntimeException();
        }
        double exp1Result = this.getExpression1().evaluate();
        return  exp1Result / exp2Result;
    }

    /**
     * Returns a String representation of the Div object.
     * The returned String will be in the format of "(expression1
     * / expression2)".
     *
     * @return a String representing the Div object.
     */
    @Override
    public String toString() {
        return ("(" + this.getExpression1().toString() + " / "
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
        return new Div(this.getExpression1().assign(var, expression),
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
        return new Div(new Minus(new Mult(this.getExpression1().differentiate(var),
                this.getExpression2()), new Mult(this.getExpression1(),
                this.getExpression2().differentiate(var))),
                new Pow(this.getExpression2(), new Num(2)));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     */
    public Expression simplify() {

        Expression simplified1 = this.getExpression1().simplify();
        Expression simplified2 = this.getExpression2().simplify();
        // simplified str is the string representations of the simplified
        // expressions for comparison purpose.
        String simplified1str = simplified1.toString();
        String simplified2str = simplified2.toString();

        if (simplified2str.equals(new Num(0).toString())) {
            return this;
        }
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
                // X/X
            if (Objects.equals(simplified1str, simplified2str)) {
                return new Num(1);
                // X/1
            } else if (Objects.equals(simplified2str, new Num(1).toString())) {
                return simplified1;
                // All other cases
            } else {
                return new Div(simplified1, simplified2);
            }
        }
    }
}
