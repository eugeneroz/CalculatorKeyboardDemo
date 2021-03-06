package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public class Operand implements Expression {
    int mValue;

    public Operand(int value) {
        this.mValue = value;
    }

    @Override public int calculate() {
        return mValue;
    }

    @Override public boolean isValid() {
        return true;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operand operand = (Operand) o;

        return mValue == operand.mValue;
    }

    @Override public int hashCode() {
        return mValue;
    }

    @Override public String toString() {
        return String.valueOf(mValue);
    }
}
