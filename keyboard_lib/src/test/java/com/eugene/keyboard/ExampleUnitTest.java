package com.eugene.keyboard;

import com.eugene.keyboard.math.AdditionExpression;
import com.eugene.keyboard.math.Expression;
import com.eugene.keyboard.math.Operand;
import com.eugene.keyboard.util.ExpressionParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test public void parseExpression() throws Exception {
        Expression expectedExpression = new AdditionExpression(new Operand(3), new Operand(5));
        Expression actualExpression = ExpressionParser.parseExpression("3+5");

        assertEquals(expectedExpression, actualExpression);
    }
}