package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public class SubtractionExpression extends ArithmeticExpression {

    public SubtractionExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    native int subtraction(int leftOperand, int rightOperand);

    @Override public int calculate() {
        return subtraction(leftOperand.calculate(), rightOperand.calculate());
    }

    @Override char getOperator() {
        return '-';
    }
}
