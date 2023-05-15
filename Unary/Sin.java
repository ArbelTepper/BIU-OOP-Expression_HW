package Unary;

import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.List;
import java.util.Map;

public class Sin extends UnaryExpression implements Expression {
    private Expression expression;

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
        return new Sin (new Num(this.expression.evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return Math.sin(this.expression.evaluate());
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */


    @Override
    public String toString() {
        return ("(sin(" + this.expression.toString() + "))");
    }

    public Expression assign(String var, Expression expression) {
        return new Sin (this.assign(var, expression));
    }
}
