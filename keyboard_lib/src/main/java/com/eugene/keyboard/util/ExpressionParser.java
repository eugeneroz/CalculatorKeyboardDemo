package com.eugene.keyboard.util;

import com.eugene.keyboard.math.ArithmeticException;
import com.eugene.keyboard.math.ArithmeticExpression;
import com.eugene.keyboard.math.Expression;
import com.eugene.keyboard.math.Operand;

/**
 * Created by eugene on 18/08/2016.
 */
public class ExpressionParser {
    public static final Expression parseExpression(String text) throws ArithmeticException {
        Expression result;
        String[] expressionArr = text.trim().split("(?<=[-+*/])|(?=[-+*/])");

        if (expressionArr.length != 3) {
            throw new ArithmeticException("Arithmetic expression is invalid");
        }
        try {
            Operand leftOperand = new Operand(Integer.valueOf(expressionArr[0]));
            Operand rightOperand = new Operand(Integer.valueOf(expressionArr[2]));
            result = ArithmeticExpression.getArithmeticExpression(expressionArr[1], leftOperand,
                    rightOperand);
        } catch (NumberFormatException exception) {
            throw new ArithmeticException("Arithmetic expression is invalid");
        } catch (IllegalStateException exception) {
            throw new ArithmeticException(exception.getMessage());
        }

        return result;
    }
}
