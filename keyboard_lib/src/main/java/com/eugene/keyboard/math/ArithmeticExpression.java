package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public abstract class ArithmeticExpression implements Expression {
    static {
        System.loadLibrary("math-jni");
    }

    Operand mLeftOperand;
    Operand mRightOperand;

    public ArithmeticExpression(Operand leftOperand, Operand rightOperand) {
        this.mLeftOperand = leftOperand;
        this.mRightOperand = rightOperand;
    }

    public static final ArithmeticExpression getArithmeticExpression(char operator,
            Operand leftOperand, Operand rightOperand) {
        ArithmeticExpression result;

        if (operator == '+') {
            result = new AdditionExpression(leftOperand, rightOperand);
        } else if (operator == '-') {
            result = new SubtractionExpression(leftOperand, rightOperand);
        } else if (operator == '*') {
            result = new MultiplicationExpression(leftOperand, rightOperand);
        } else if (operator == '/') {
            result = new DivisionExpression(leftOperand, rightOperand);
        } else {
            throw new IllegalStateException("Illegal arithmetic operator");
        }

        return result;
    }

    public abstract int calculate();

    abstract char getOperator();

    @Override public boolean isValid() {
        return mLeftOperand != null && mRightOperand != null;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticExpression that = (ArithmeticExpression) o;

        if (!mLeftOperand.equals(that.mLeftOperand)) return false;
        return mRightOperand.equals(that.mRightOperand);
    }

    @Override public int hashCode() {
        int result = mLeftOperand.hashCode();
        result = 31 * result + mRightOperand.hashCode();
        return result;
    }

    @Override public String toString() {
        return String.valueOf(mLeftOperand.calculate()) + getOperator() + String.valueOf(
                mRightOperand.calculate()) + '=' + String.valueOf(calculate());
    }
}
