package Tests;
import Unary.*;
import Binary.*;
import Miscellaneous.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainTest {

    static int gradePart1 = 0;
    static int totalTests = 0;

    public static void main(String[] args) throws Exception {

        Expression e1 = null;
        double value;
        String s;
        Map<String, Double> assignment = new TreeMap<String, Double>();
        List<String> vars;

        // Test 1
        try
        {
            totalTests++;
            // ans is : (((x + y) + z)^2.0)

            // expression: (((x + y) + z)^2.0)
            e1 = new Pow(new Plus (new Plus(new Var("x"), new Var("y")) ,new Var("z")), new Num(2));
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(((x+y)+z)^2.0)") || s.equals("(((x+y)+z)^2)"))
            {
                gradePart1++;
            } else {
                System.out.println("Test 1 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 1 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in Test 1");
        }

        // Test 2
        try
        {
            totalTests++;
            // ans is : (((x + y) + (z + w))^4.0)

            // expression: ((x + y) + (z + w))^4
            e1 = new Pow(new Plus(new Plus(new Var("x"), new Var("y")) , new Plus(new Var("z"), new Var("w"))), new Num(4));
            s = e1.toString().toLowerCase().replaceAll("\\s+","");

            // (((x+y)+(z+w))^4.0)
            if (s.equals("(((x+y)+(z+w))^4.0)") || s.equals("(((x+y)+(z+w))^4)"))
            {
                gradePart1++;
            } else {
                System.out.println("Test 2 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 2 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in Test 2");
        }

        // Test 3
        try
        {
            totalTests++;
            // The result is: 234256.0

            // expression: ((x + y) + (z + w))^4
            assignment.clear();
            assignment.put("x",  2.0);
            assignment.put("y",  4.0);
            assignment.put("z",  6.0);
            assignment.put("w",  10.0);
            value = e1.evaluate(assignment);

            if (value == 234256.0)
            {
                gradePart1++;
            } else {
                System.out.println("Test 3 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 3 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 3");
        }

        // Test 4
        try
        {
            totalTests++;
            // get vars of e1
            // ans is (the order is not important) :
            // x, y, z, w
            vars = e1.getVariables();
            Collections.sort(vars);

            List<String> correct = new ArrayList<String>();
            correct.add("x");
            correct.add("y");
            correct.add("z");
            correct.add("w");

            boolean ans = correct.retainAll(vars);
            if (!ans)
            {
                gradePart1++;
            } else {
                System.out.println("Test 4 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 4 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 4");
        }

        // Test 5
        try
        {
            totalTests++;
            // ans is : (((2.0*x)+Sin((4.0*y)))+(e^x))

            // expression: ((2x + sin(4y)) + e^x)
            e1 = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                    new Sin(new Mult(new Num(4), new Var("y")))),
                    new Pow(new Var("e"), new Var("x")));
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(((2.0*x)+sin((4.0*y)))+(e^x))") || s.equals("(((2*x)+sin((4*y)))+(e^x))"))
            {
                gradePart1++;
            } else {
                System.out.println("Test 5 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 5 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 5");
        }

        // Test 6
        try
        {
            totalTests++;
            // ans is : 11.406508505367933
            assignment.clear();
            assignment.put("x", (double) 2);
            assignment.put("y", 0.25);
            assignment.put("e", Math.E);
            value = e1.evaluate(assignment);

            if (value == 11.406508505367933)
            {
                gradePart1++;
            } else {
                System.out.println("Test 6 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 6 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 6");
        }

        // Test 7
        try
        {
            totalTests++;
            // ans is : (2.0+(e^x))
            e1 = e1.differentiate("x").simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(2.0+(e^x))") || s.equals("(2+(e^x))"))
            {
                gradePart1++;
            } else {
                System.out.println("Test 7 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 7 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 7");
        }

        // Test 8
        try
        {
            totalTests++;
            // ans is : 0.10173001015936747

            // expression: ((sin(1) + cos(1)) / 10)
            e1 = new Div(new Plus(new Sin(new Num(1)), new Cos(new Num(1))), new Num(10));
            value = e1.evaluate();

            if (value == 0.10173001015936747)
            {
                gradePart1++;
            } else {
                System.out.println("Test 8 failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test 8 exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test 8");
        }

        // Test 9
        try
        {
            totalTests++;
            // ans is : 2y

            // expression: (log(9x,9x) * 2y)
            e1 = new Mult(new Log(new Mult(new Num(9), new Var("x")), new Mult(new Num(9), new Var("x"))),
                    new Mult(new Num(2), new Var("y")));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");
            //System.out.println(s);
            if (s.equals("2.0*y") || s.equals("2*y") || s.equals("(2.0*y)") || s.equals("(2*y)"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 10
        try
        {
            totalTests++;
            // ans is : (-0.0-(2.0+x))

            // expression: (-((y / -(y / x)) * 0.0) + -(--((-2.0 - 0.0) - 4.0) + x))
            e1 = new Plus(new Neg(new Mult(new Div(new Var("y"), new Neg(new Div(new Var("y"), new Var("x")))),
                    new Num(0))), new Neg(new Neg(new Neg(new Plus(new Minus(new Minus(new Neg(new Num(2)),
                    new Num(0)), new Neg(new Num(4))), new Var("x"))))));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(-0.0-(2.0+x))") || s.equals("(-0-(2+x))") || s.equals("(-(2.0+x))") || s.equals("(-(2+x))"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 11
        try
        {
            totalTests++;
            // ans is : 73.578125

            // expression: ((x^2 + y^2) - z^3)
            e1 = new Minus(new Plus(new Pow(new Var("x"), new Num(2)), new Pow(new Var("y"), new Num(2))),
                    new Pow(new Var("z"), new Num(3)));
            assignment.put("x", 7.0);
            assignment.put("y", 5.0);
            assignment.put("z", 0.75);
            value = e1.evaluate(assignment);

            if (value == 73.578125)
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 12
        try
        {
            totalTests++;
            // ans is : 1.0

            // expression: (log(x,x) / log(x,x))
            e1 = new Div(new Log(new Var("x"), new Var("x")), new Log(new Var("x"), new Var("x")));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("1.0") || s.equals("1"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 13
        try
        {
            totalTests++;
            // ans is : -x

            // expression: (0 - (x * 1 / 1))
            e1 = new Minus(new Num(0), new Div(new Mult(new Var("x"), new Num(1)), new Num(1)));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(-x)") || s.equals("-x"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 14
        try
        {
            totalTests++;
            // ans is : (((1.0*(-sin(x)))+(1.0/x))+((((0.0*x)-(1.0*1.0))/(x^2.0))+(((2.0*1.0)*(x^(2.0-1.0)))*cos((x^2.0)))))

            // expression: ((cos(x) + log(e,x)) + (1/x + sin(x^2)))
            e1 = new Plus(new Plus(new Cos(new Var("x")), new Log(new Var("e"), new Var("x"))), new Plus(new Div(new Num(1),
                    new Var("x")), new Sin(new Pow(new Var("x"), new Num(2)))));
            e1 = e1.differentiate("x");
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");
            //System.out.println(s);
            if (s.equals("(((1.0*(-sin(x)))+(1.0/x))+((((0.0*x)-(1.0*1.0))/(x^2.0))+(((2.0*1.0)*(x^(2.0-1.0)))*cos((x^2.0)))))") ||
                    s.equals("(((1*(-sin(x)))+(1/x))+((((0*x)-(1*1))/(x^2))+(((2*1)*(x^(2-1)))*cos((x^2)))))"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 15
        try
        {
            totalTests++;
            // ans is : 9x

            // expression: (((3 + 6) * x) + (4x * sin(0)))
            e1 = new Plus(new Mult(new Plus(new Num(3), new Num(6)), new Var("x")), new Mult(new Mult(new Num(4),
                    new Var("x")), new Sin(new Num(0))));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(9.0*x)") || s.equals("(9*x)") || s.equals("(9x)") || s.equals("9x"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 16
        try
        {
            totalTests++;
            // ans is : 2.4660592671959822E27

            // expression: ((cos(5x^7) * e) + y^(x / 2 + 5))
            e1 = new Plus(new Mult(new Cos(new Pow(new Mult(new Num(5), new Var("x")), new Num(7))), new Var("e")),
                    new Pow(new Var("y"), new Plus(new Div(new Var("x"), new Num(2)), new Num(5))));
            assignment.clear();
            assignment.put("x", 25.0);
            assignment.put("y", 36.75);
            assignment.put("e", Math.E);
            value = e1.evaluate(assignment);

            if (value == 2.4660592671959822E27)
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        // Test 17
        try
        {
            totalTests++;
            // ans is : 2x

            // expression: (((x * 1) * 1) + (0 - -(x + 0)))
            e1 = new Plus(new Mult(new Mult(new Var("x"), new Num(1)), new Num(1)), new Minus(new Num(0), new Neg(new Plus(new Var("x"), new Num(0)))));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");
            if (s.equals("(2x)") || s.equals("(2.0x)") || s.equals("(2*x)") || s.equals("(2.0*x)") ||
                    s.equals("2x") || s.equals("2.0x") || s.equals("2*x") || s.equals("2.0*x"))
            {
                gradePart1++;
            } else {
                System.out.println("Test " + totalTests + " failed");
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError in test " + totalTests);
        }

        //Test 18
        try
        {
            totalTests++;
            // ans is : 0

            // expression: ((((20.0 * (x^5.0)) * (3.0 * (e^x))) * (Cos((x^2.0)))) * (x - x))
            // (20*x^5 * 3e^x ) * Cos(x^2) * (x - x)
            e1 = new Mult(new Mult( new Mult( new Mult(new Num(20) ,new Pow(new Var("x") , new Num(5))) , new Mult(new Num(3) , new Pow(new Var("e"), new Var("x"))) )
                    , new Cos(new Pow(new Var("x") , new Num(2)))) , new Minus(new Var("x") , new Var("x")));
            e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("0.0") || s.equals("0"))
            {
                gradePart1++;
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Your grade is: " + gradePart1 + "/" + totalTests);
        }

        //Test 19
        try
        {
            totalTests++;
            // ans is : 1

            // expression: ((((e^x) * ((e^x)^2.0)) / ((e^x)^3.0)) * ((((e^x)^4.0) * ((e^x)^3.0)) / ((e^x)^7.0)))
            e1 = new Mult(new Div(new Mult(new Pow(new Var("e"), new Var("x")) ,new Pow(new Pow(new Var("e"),
                    new Var("x")) , new Num(2))) , new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)))
                    , new Div( new Mult(new Pow(new Pow(new Var("e"), new Var("x")) , new Num(4)) ,
                    new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)) ) ,e1 = new Pow(new Pow(new Var("e"),
                    new Var("x")) , new Num(7)) ));
            e1 = e1.assign("e", new Num(2.71));
            e1 = e1.assign("x", new Num(0));
            value = e1.evaluate();

            if (value == 1)
            {
                gradePart1++;
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        catch(StackOverflowError t)
        {
            System.out.println("Your grade is: " + gradePart1 + "/" + totalTests);
        }

        //Test 20
        try
        {
            // expression: sin(e^(sin(x)/cos(x)) = sin(e^tan(x))
            // ans is :
            e1 = new Sin(new Pow(new Var("e"), new Div(new Sin(new Var("x")), new Cos(new Var("x")))));
            e1 = e1.differentiate("x");
            //e1 = e1.simplify();
            s = e1.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(((e^(sin(x)/cos(x)))*((((((1.0*cos(x))*cos(x))-((1.0*(-sin(x)))*sin(x)))/(cos(x)^2.0))*log(e,e))+((sin(x)/cos(x))*0.0)))*cos((e^(sin(x)/cos(x)))))") ||
                    s.equals("(((e^(sin(x)/cos(x)))*((((((1*cos(x))*cos(x))-((1*(-sin(x)))*sin(x)))/(cos(x)^2))*log(e,e))+((sin(x)/cos(x))*0)))*cos((e^(sin(x)/cos(x)))))"))
            {
                gradePart1++;
            }
        }
        catch(Exception exp)
        {
            System.out.println("Test " + totalTests + " exception");
        }
        //catch(StackOverflowError t)
        {
            System.out.println("Your grade is: " + gradePart1 + "/" + totalTests);
        }
    }
}
