import Binary.Log;
import Binary.Mult;
import Binary.Plus;
import Binary.Pow;
import Miscellaneous.Expression;
import Miscellaneous.Num;
import Miscellaneous.Var;
import Unary.Sin;

import java.util.Map;
import java.util.TreeMap;

public class ExpressionsTest {
    public static void main(String[] args) throws Exception {

        Expression e = new Plus(new Mult(new Num(2), new Var("x")),
                new Plus(new Sin(new Mult(new Num(4), new Var("y"))),
                        new Pow(new Var("e"),
                                new Var("x"))));
        System.out.println(e.toString());

        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(e.evaluate(assignment));

        Expression de = e.differentiate("x");
        System.out.println(de);

        System.out.println(de.evaluate(assignment));



        Expression right = new Pow(new Var("e"),
                new Var("x"));

        Expression rightde = right.differentiate("x");

        System.out.println(rightde);
        System.out.println(rightde.evaluate(assignment));

        Expression myproblem = new Log(new Var("e"),
                new Var("e"));

        double eval = myproblem.evaluate(assignment);
        System.out.println(eval);




    }
}
