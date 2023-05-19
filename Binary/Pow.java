package Binary;

import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.NaN;

public class Pow extends BinaryExpression implements Expression {

    public Pow (Expression expression1, Expression expression2) {
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
        return new Pow(new Num(this.getExpression1().evaluate(assignment)) ,
                new Num(this.getExpression2().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        if (this.getExpression1().equals(new Num(0))
                && this.getExpression2().equals(new Num(0)))
        {
            throw new RuntimeException();
        } else {
            double powerResult = Math.pow(this.getExpression1().evaluate(),
                    this.getExpression2().evaluate());
            // if the power is not NaN
            if (!Double.isNaN(powerResult))
            {
                return powerResult;
            } else {
                throw new RuntimeException();
            }
        }

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
        return new Pow(this.getExpression1().assign(var, expression),
                this.getExpression2().assign(var, expression));
    }

    /**
     * Returns a String representation of the Pow object.
     * The returned String will be in the format of "(expression1^expression2)".
     * @return a String representing the Pow object.
     */
    @Override
    public String toString() {
        return ("(" + this.getExpression1().toString() + "^"
                + this.getExpression2().toString() + ")");
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Pow(this.getExpression1(), this.getExpression2()),
                new Plus(new Mult(this.getExpression1().differentiate(var),
                        new Div(this.getExpression2(), this.getExpression1())),
                        new Mult(this.getExpression2().differentiate(var),
                                new Log(new Num(Math.E), this.getExpression1()))));
    }

    public Expression simplify() throws Exception { // shouldn't throw exception
        if (this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else {
            return new Pow(this.getExpression1().simplify(),
                    this.getExpression2().simplify());
        }
    }
}
