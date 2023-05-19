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

        Expression e1 = new Mult(new Pow(new Mult(new Num(83), new Num(2)), new Num(150)), new Num(0));
        e1 = e1.simplify();
        System.out.println(e1.toString());
    }
}