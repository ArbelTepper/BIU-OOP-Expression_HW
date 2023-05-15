package Binary;

import Miscellaneous.Expression;

import java.util.Map;

public class Log extends BinaryExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public Log (Expression expression1, Expression expression2) {
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
        return 0;
    }

    @Override
    public double evaluate() throws Exception {
        return Math.log(this.expression1.evaluate())
                / Math.log(this.expression2.evaluate());
    }

    @Override
    public String toString() {
        return ("(log" + this.expression1.toString() + ", "
                + this.expression2.toString() + ")");
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(this.expression1.assign(var, expression),
                this.expression2.assign(var, expression));
    }
}
