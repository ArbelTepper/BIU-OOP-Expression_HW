package Unary;

import Binary.Mult;
import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.List;
import java.util.Map;

public class Cos extends UnaryExpression implements Expression {
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
        return new Cos (new Num(this.getExpression().evaluate(assignment))).evaluate();
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

    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(this.getExpression()),
                this.getExpression().differentiate(var)));
    }

    @Override
    public Expression simplify() throws Exception { // shouldn't throw exception
        if (this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else {
            return new Cos(this.getExpression().simplify());
        }
    }
}
