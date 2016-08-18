package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public abstract class ArithmeticExpression implements Expression {
    Operand leftOperand;
    Operand rightOperand;

    public ArithmeticExpression(Operand leftOperand, Operand rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public static final ArithmeticExpression getArithmeticExpression(String operator,
            Operand leftOperand, Operand rightOperand) {
        ArithmeticExpression result;

        if (operator.equals("+")) {
            result = new AdditionExpression(leftOperand, rightOperand);
        } else if (operator.equals("-")) {
            result = new SubstractionExpression(leftOperand, rightOperand);
        } else if (operator.equals("*")) {
            result = new MultiplicationExpression(leftOperand, rightOperand);
        } else if (operator.equals("/")) {
            result = new DivisionExpression(leftOperand, rightOperand);
        } else {
            throw new IllegalStateException("Illegal arithmetic operator");
        }

        return result;
    }

    public abstract int calculate();

    @Override public boolean isValid() {
        return leftOperand != null && rightOperand != null;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticExpression that = (ArithmeticExpression) o;

        if (!leftOperand.equals(that.leftOperand)) return false;
        return rightOperand.equals(that.rightOperand);
    }

    @Override public int hashCode() {
        int result = leftOperand.hashCode();
        result = 31 * result + rightOperand.hashCode();
        return result;
    }
}