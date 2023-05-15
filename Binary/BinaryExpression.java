package Binary;

import Miscellaneous.Expression;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryExpression {
    private Expression expression1;
    private Expression expression2;

    public BinaryExpression (Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    public List<String> getVariables() {
        List<String> merge = new ArrayList<>();
        merge.addAll(expression1.getVariables());
        merge.addAll(expression2.getVariables());
        return merge;
    }
}
