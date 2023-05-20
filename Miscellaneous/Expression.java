package Miscellaneous;

import java.util.List;
import java.util.Map;

/**
 * Represents an arithmetic expression.
 */
public interface Expression {

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
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a string representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the name of the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the derivative of the expression differentiated according to the
     * specified variable inserted.
     *
     * @param var the variable by which the expression is differentiated
     * @return the derivative of the expression
     */
    Expression differentiate(String var);

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     * @throws Exception if an error occurs during simplification
     */
    Expression simplify();
}