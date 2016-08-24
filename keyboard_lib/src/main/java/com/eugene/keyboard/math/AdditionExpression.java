package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public class AdditionExpression extends ArithmeticExpression {

    public AdditionExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    native int addition(int leftOperand, int rightOperand);

    @Override public int calculate() {
        return addition(mLeftOperand.calculate(), mRightOperand.calculate());
    }

    @Override char getOperator() {
        return '+';
    }
}
