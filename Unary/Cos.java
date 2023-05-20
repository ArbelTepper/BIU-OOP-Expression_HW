package Unary;

import Binary.Mult;
import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.List;
import java.util.Map;

/**
 * The type Cos represents a Cosine object.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Instantiates a new Cos.
     *
     * @param expression the expression
     */
    public Cos(Expression expression) {
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
        // is made into a Cos object, whose value can be evaluated.
        return new Cos(new Num(this.getExpression().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians((this.getExpression().evaluate())));
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return this.getExpression().getVariables();
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
        return this.getExpression().assign(var, expression);
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return A string representation of the expression.
     */
    @Override
    public String toString() {
        return ("cos(" + this.getExpression().toString() + ")");
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
        return new Neg(new Mult(new Sin(this.getExpression()),
                this.getExpression().differentiate(var)));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     * @throws Exception if an error occurs during simplification
     */
    @Override
    public Expression simplify() { // shouldn't throw exception
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return new Cos(this.getExpression().simplify());
        }
    }
}
