package com.eugene.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import com.eugene.keyboard.math.ArithmeticException;
import com.eugene.keyboard.math.Expression;
import com.eugene.keyboard.util.ExpressionParser;

/**
 * Created by eugene on 18/08/2016.
 */
public class CalculatorInputMethod extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {
    private static final String TAG = CalculatorInputMethod.class.getSimpleName();
    private static final int ASSIGNMENT = 61;
    private static final int HISTORY = 72;

    private CalculatorKeyboard mCalculatorKeyboard;
    private KeyboardView mInputView;

    @Override public void onInitializeInterface() {
        super.onInitializeInterface();

        mCalculatorKeyboard = new CalculatorKeyboard(this, R.xml.calculator);
    }

    @Override public View onCreateInputView() {
        mInputView = (CalculatorKeyboardView) getLayoutInflater().inflate(R.layout.calculator_input,
                null);
        mInputView.setOnKeyboardActionListener(this);
        mInputView.setKeyboard(mCalculatorKeyboard);

        return mInputView;
    }

    @Override public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
    }

    @Override public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);
    }

    @Override public void onStartCandidatesView(EditorInfo info, boolean restarting) {
        super.onStartCandidatesView(info, restarting);
    }

    @Override public void onFinishCandidatesView(boolean finishingInput) {
        super.onFinishCandidatesView(finishingInput);
    }

    @Override public void onPress(int i) {
        Log.d(TAG, "onPress");
    }

    @Override public void onRelease(int i) {
        Log.d(TAG, "onRelease");
    }

    @Override public void onKey(int primaryCode, int[] keyCodes) {
        Log.d(TAG, "onKey(): primaryCode = " + primaryCode);
        InputConnection inputConnection = getCurrentInputConnection();
        switch (primaryCode) {
            case ASSIGNMENT:
                String text = inputConnection.getExtractedText(new ExtractedTextRequest(),
                        0).text.toString();
                Expression expression;

                try {
                    expression = ExpressionParser.parseExpression(text);

                    CharSequence beforeCursorText =
                            inputConnection.getTextBeforeCursor(text.length(), 0);
                    CharSequence afterCursorText =
                            inputConnection.getTextAfterCursor(text.length(), 0);
                    inputConnection.beginBatchEdit();
                    inputConnection.deleteSurroundingText(beforeCursorText.length(),
                            afterCursorText.length());
                    inputConnection.setComposingText(String.valueOf(expression.calculate()), 1);
                    inputConnection.endBatchEdit();
                } catch (ArithmeticException e) {
                }

                Log.d(TAG, "onKey: assignment text is '" + text + "'");

                break;
            default:
                String newText = String.valueOf((char) primaryCode);
                inputConnection.commitText(newText, 1);
                break;
        }
    }

    @Override public void onText(CharSequence charSequence) {
        Log.d(TAG, "onText");
    }

    @Override public void swipeLeft() {

    }

    @Override public void swipeRight() {

    }

    @Override public void swipeDown() {

    }

    @Override public void swipeUp() {

    }
}
