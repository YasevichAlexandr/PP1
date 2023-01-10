package Parsing;

import Exceptions.CalculatorFunc;

public class CalculatorFunc {

    final int SYNTAX = 0;
    final int NOEXP = 1;
    final int ZERO = 2;
    final String EOF = "\0";
    private String token;
    private int typeToken;


    public String toString(){
        return String.format("Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}", exp.toString(), explds,
                token.toString(), typeToken);
    }


    private boolean isDel(char charAt) {
        if((" +-/*%^=()".indexOf(charAt)) == -1)
            return false;
        return true;
    }

    public String evaluate(String expstr) throws CalculatorFuncException {
        double result;

        exp = expstr;
        explds = 0;
        getToken();

        if(token.equals(EOF))
            handleErr(NOEXP);

        result = evalExp2();

        if(!token.equals(EOF))
            handleErr(SYNTAX);

        return Double.toString(result);
    }

    private double Exp2() throws CalculatorFuncException {

        char op;
        double result;
        double partialResult;
        result = Exp3();
        while((op = token.charAt(0)) == '+' ||
                op == '-'){
            getToken();
            partialResult = Exp3();
            switch(op){
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }

    private double Exp3() throws CalculatorFuncException {

        char op;
        double result;
        double partialResult;

        result = Exp4();
        while((op = token.charAt(0)) == '*' ||
                op == '/' | op == '%'){
            getToken();
            partialResult = evalExp4();
            switch(op){
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if(partialResult == 0.0)
                        handleErr(ZERO);
                    result /= partialResult;
                    break;
                case '%':
                    if(partialResult == 0.0)
                        handleErr(ZERO);
                    result %= partialResult;
                    break;
            }
        }
        return result;
    }


    private double atom()   throws CalculatorFuncException {

        double result = 0.0;
        switch(typeToken){
            case NUMBER:
                try{
                    result = Double.parseDouble(token);
                }
                catch(NumberFormatException exc){
                    handleErr(SYNTAX);
                }
                getToken();

                break;
            default:
                handleErr(SYNTAX);
                break;
        }
        return result;
    }
