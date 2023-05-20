package Miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type Var represents a Variable object.
 */
public class Var implements Expression {
    private String variable;

    /**
     * Instantiates a new Var.
     *
     * @param variable the variable
     */
    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * Gets variable.
     *
     * @return the variable
     */
    public String getVariable() {
        return this.variable;
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
        for (String key : assignment.keySet()) {
            // if this variable is a key in the map.
            if (Objects.equals(key, this.getVariable())) {
                // create a new expression using the assign method with the
                // matching key and its value.
                Expression assigned =  this.assign(key,
                        new Num(assignment.get(key)));
                // returns the value of the new num made from the map, as a
                // double rather than an Expression.
                return assigned.evaluate();
            }
        }
        // if this line has been reached it means there is a string in the
        // map for which there is no matching Var.
        throw new RuntimeException();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        throw new RuntimeException("Cannot evaluate a non-numeric variable.");
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(this.getVariable());
        return variables;
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
        if (this.getVariable().equals(var)) {
            return expression;
        } else {
            return this;
        }
    }
    /**
     * Returns a string representation of the expression.
     *
     * @return A string representation of the expression.
     */
    @Override
    public String toString() {
        return (this.getVariable());
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
        if (this.getVariable().equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return the simplified expression
     */
    public Expression simplify() {
        return this;
    }
}
