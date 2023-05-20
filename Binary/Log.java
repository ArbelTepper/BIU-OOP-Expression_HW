package Binary;

import Miscellaneous.Expression;
import Miscellaneous.Num;
import Miscellaneous.Var;

import java.util.Map;
import java.util.Objects;

/**
 * The type represents a Logarithm object.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Log.
     *
     * @param expression1 the expression 1
     * @param expression2 the expression 2
     */
    // expression 1 is the base
    // expression 2 is the exponent
    public Log(Expression expression1, Expression expression2) {
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
        return new Log(new Num(this.getExpression1().evaluate(assignment)),
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

        double exp1Result = this.getExpression1().evaluate();
        double exp2Result = this.getExpression2().evaluate();
        if (exp1Result <= 0 || exp1Result == 1 || exp2Result <= 0) {
            throw new RuntimeException();
        } else {
            return Math.log(exp2Result) / Math.log(exp1Result);
        }
    }

    /**
     * Returns a String representation of the Log object.
     * The returned String will be in the format of "log (expression1,
     * expression2)".
     *
     * @return a String representing the Log object.
     */
    @Override
    public String toString() {
        return ("log(" + this.getExpression1().toString() + ", "
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
        return new Log(this.getExpression1().assign(var, expression),
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

        // all 10's used to be Math.E
        if (this.getExpression1().getVariables().contains(var)) {
            return new Div(new Log(new Var("e"), this.getExpression2()),
                    new Log(new Var("e"), this.getExpression1()))
                    .differentiate(var);
        } else {
            return new Div(this.getExpression2().differentiate(var),
                    new Mult(this.getExpression2(), new Log(new Var("e"),
                            this.getExpression1())));
        }
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     */
    public Expression simplify() {
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
            // log (X,X)
            if (Objects.equals(simplified1str, simplified2str)) {
                return new Num(1);
                // All other cases
            } else {
                return new Log(simplified1, simplified2);
            }
        }
    }
}
