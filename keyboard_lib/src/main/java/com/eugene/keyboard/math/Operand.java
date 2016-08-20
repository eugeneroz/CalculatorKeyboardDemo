package com.eugene.keyboard.math;

/**
 * Created by eugene on 18/08/2016.
 */
public class Operand implements Expression {
    int value;

    public Operand(int value) {
        this.value = value;
    }

    @Override public int calculate() {
        return value;
    }

    @Override public boolean isValid() {
        return true;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operand operand = (Operand) o;

        return value == operand.value;
    }

    @Override public int hashCode() {
        return value;
    }

    @Override public String toString() {
        return String.valueOf(value);
    }
}
