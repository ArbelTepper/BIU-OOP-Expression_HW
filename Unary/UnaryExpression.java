package Unary;

import Miscellaneous.BaseExpression;
import Miscellaneous.Expression;

import java.util.List;

public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    public UnaryExpression (Expression expression) {
        super(expression);
    }
    public List<String> getVariables() {
        return this.expression.getVariables();
    }
}
