package Miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type represents a Number object.
 */
public class Num implements Expression {
    private double num;

    /**
     * Instantiates a new Num.
     *
     * @param num the num
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public double getNum() {
        return this.num;
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
        return this.num;
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
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
        return this;
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return A string representation of the expression.
     */
    @Override
    public String toString() {

        //if (this.getNum() == Math.E) {
        //    return ("e");
        //}
            Double toPrint = getNum();
            return (toPrint.toString());
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
        return new Num(0);
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     * @throws Exception if an error occurs during simplification
     */
    public Expression simplify() {
        return this;
    }
}

