import Binary.*;
import Miscellaneous.Expression;
import Miscellaneous.Num;
import Miscellaneous.Var;
import Unary.Neg;
import Unary.Sin;
import Unary.Cos;

import java.util.HashMap;
import java.util.Map;



public class Main {
    public static void main(String[] args) throws Exception {

        Expression e1 = new Sin(new Plus(new Mult(new Num(2), new Var("x")),
                new Var("y")));
        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        Expression e3 = new Mult(new Num(4), new Var("x"));
        Expression e4 = new Div(new Neg(new Var("y")), new Num(2));



        Expression e5 = e1.assign("x", new Var("z"));
        //System.out.println(e5.toString()); // sin((2.0*z+y))

        Expression e6 = e2.assign("y", new Var("z"));
        //System.out.println(e6.toString()); // (x+z)^2.0

        Expression e7 = e3.assign("x", new Var("y"));
        //System.out.println(e7.toString()); // 4.0*y

        Expression e8 = e4.assign("y", new Num(2));
        //System.out.println(e8.toString()); // (-2.0)/2.0

        Expression e9 = new Log(new Var("x"), (new Var("x")));
        //System.out.println(e9.differentiate("x").assign("x", new Num(100))
        // .evaluate());

        Expression e = new Pow(new Var("e"), new Var("x"));
        System.out.println(e.differentiate("x"));
    }
}