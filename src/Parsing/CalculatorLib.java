package Parsing;

import net.objecthunter.exp4j.*;

public class CalculatorLib
{
    public String Calculate(String line)
    {
        Expression expression = new ExpressionBuilder(line).build();
        double result = expression.evaluate();
        return Double.toString(result);
    }
}