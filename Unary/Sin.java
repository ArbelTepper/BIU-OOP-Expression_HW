package Unary;

import Binary.Mult;

import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.Map;

/**
 * The type Sin represents a new Sine object.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Instantiates a new Sin.
     *
     * @param expression the expression
     */
    public Sin(Expression expression) {
        super(expression);
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
        // evaluate returns a double, which is made into a Num object, which
        // is made into a Sin object, whose value can be evaluated.
        return new Sin(new Num(this.getExpression().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians((this.getExpression().evaluate())));
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return A string representation of the expression.
     */
    @Override
    public String toString() {
        return ("sin(" + this.getExpression().toString() + ")");
    }

    /**
     * Assigns a new expression to a specific variable.
     *
     * @param var        the variable which the value is assigned to
     * @param expression the expression to assign
     * @return a new expression with the assigned variable
     */
    public Expression assign(String var, Expression expression) {
        return new Sin(this.getExpression().assign(var, expression));
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
        return new Mult(new Cos(this.getExpression()),
                this.getExpression().differentiate(var));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     * @throws Exception if an error occurs during simplification
     */
    public Expression simplify() {
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return new Sin(this.getExpression().simplify());
        }
    }
}
