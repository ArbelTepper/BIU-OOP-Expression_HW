package Miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Var implements Expression {
    private String variable;

    public Var (String variable) {
        this.variable = variable;
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
            if (Objects.equals(key, this.variable)) {
                // create a new expression using the assign method with the
                // matching key and its value.
                Expression assigned =  this.assign(key,
                        new Num (assignment.get(key)));
                // returns the value of the new num made from the map, as a
                // double rather than an Expression.
                return assigned.evaluate();
            }
        }
        throw new RuntimeException("The variable" + this.variable +
                "has not been assigned so the expression cannot be " +
                "evaluated.");
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
        variables.add(this.variable);
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
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return null;
        }
    }
}
