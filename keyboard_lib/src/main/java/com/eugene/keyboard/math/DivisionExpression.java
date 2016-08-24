package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public class DivisionExpression extends ArithmeticExpression {
    public DivisionExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    native int division(int leftOperand, int rightOperand);

    @Override public int calculate() {
        return division(mLeftOperand.calculate(), mRightOperand.calculate());
    }

    @Override char getOperator() {
        return '/';
    }
}
