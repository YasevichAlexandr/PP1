import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator
{
    public static void main (String a[]) throws IOException {
        File fileIn = new File("input.txt");
        FileWriter cout = new FileWriter("output.txt");
        Scanner cin = new Scanner(fileIn);
        String expression = cin.next();
        //String expression = "-(-2-2)";
        cout.write(String.valueOf(new Calculator().decide(expression)));
        cout.close();
        //System.out.println(new Calculator().decide(expression));
    }
    public double decide (String expression)
    {
        String prepared = preparingExpression(expression);
        String rpn = expressionToRPN(prepared);
        return rpnToAnswer(rpn);
    }

    private String preparingExpression (String expression)
    {
        String preparedExpression = new String();
        for (int token = 0; token < expression.length(); token++)
        {
           char symbol = expression.charAt(token);
           if (symbol == '-') {
           if (token == 0)
               preparedExpression += '0';
           else if (expression.charAt(token-1) == '(')
               preparedExpression += '0';
           }
           preparedExpression+=symbol;
           }
        return preparedExpression;
        }

     private String expressionToRPN (String expression) //Polska Notation
    {
        String current = "";
        Stack <Character> stack = new Stack<>();
        int priority;
        for (int i=0; i<expression.length();i++)
        {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) current+=expression.charAt(i);
            if (priority == 1) stack.push(expression.charAt(i));

            if (priority > 1)
            {
                current += ' ';
                while(!stack.empty())
                {
                    if(getPriority(stack.peek()) >= priority)
                    {
                        current+= stack.pop();
                    }
                     else break;
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1)
            {
                current+= " ";
                while(getPriority(stack.peek()) != 1)
                {
                    current+=stack.pop();
                }
                stack.pop();
            }
        }
        while(!stack.empty())
        {
            current+=stack.pop();
        }

        return current;
    }

    private double rpnToAnswer (String rpn) {  
        String operand;
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                operand = new String();
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop(), b = stack.pop();
                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }
        return stack.pop();
    }

    private int getPriority(char token) //Priority
    {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(' ) return 1;
        else if (token == ')') return -1;
        else
            return 0;
    }
}
