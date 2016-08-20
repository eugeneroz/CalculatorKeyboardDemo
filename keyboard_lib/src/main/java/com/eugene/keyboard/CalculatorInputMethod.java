package com.eugene.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.eugene.keyboard.math.ArithmeticException;
import com.eugene.keyboard.math.Expression;
import com.eugene.keyboard.util.ExpressionParser;
import com.eugene.keyboard.view.ListViewMaxHeight;
import java.util.LinkedList;

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

    private ArrayAdapter<Expression> historyAdapter;
    private boolean showHistory = false;

    public CalculatorInputMethod() {
        super();
    }

    @Override public void onInitializeInterface() {
        super.onInitializeInterface();

        mCalculatorKeyboard = new CalculatorKeyboard(this, R.xml.calculator);
    }

    @Override public View onCreateCandidatesView() {
        ListViewMaxHeight listView =
                (ListViewMaxHeight) getLayoutInflater().inflate(R.layout.history, null);

        historyAdapter = new ArrayAdapter<>(this, R.layout.history_item, new LinkedList());

        listView.setAdapter(historyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "onItemClick(): position = " + position);
                setText(String.valueOf(historyAdapter.getItem(position).calculate()));

                displayHistory(false);
            }
        });

        return listView;
    }

    private void displayHistory(boolean show) {
        showHistory = show;
        setCandidatesViewShown(showHistory);
    }

    @Override public int getCandidatesHiddenVisibility() {
        return View.GONE;
    }

    private void setText(String newText) {
        InputConnection inputConnection = getCurrentInputConnection();
        String text =
                inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString();

        CharSequence beforeCursorText = inputConnection.getTextBeforeCursor(text.length(), 0);
        CharSequence afterCursorText = inputConnection.getTextAfterCursor(text.length(), 0);
        inputConnection.beginBatchEdit();
        inputConnection.deleteSurroundingText(beforeCursorText.length(), afterCursorText.length());
        inputConnection.setComposingText(newText, 1);
        inputConnection.endBatchEdit();
        inputConnection.finishComposingText();
    }

    @Override public View onCreateInputView() {
        mInputView = (CalculatorKeyboardView) getLayoutInflater().inflate(R.layout.calculator_input,
                null);
        mInputView.setOnKeyboardActionListener(this);
        mInputView.setKeyboard(mCalculatorKeyboard);

        return mInputView;
    }

    @Override public void onComputeInsets(Insets outInsets) {
        super.onComputeInsets(outInsets);

        if (!isFullscreenMode()) {
            outInsets.contentTopInsets = outInsets.visibleTopInsets;
        }
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
                if (!showHistory) {
                    String text = inputConnection.getExtractedText(new ExtractedTextRequest(),
                            0).text.toString();

                    try {
                        Expression expression = ExpressionParser.parseExpression(text);

                        setText(String.valueOf(expression.calculate()));
                        historyAdapter.add(expression);
                    } catch (ArithmeticException e) {
                    }

                    Log.d(TAG, "onKey: assignment text is '" + text + "'");
                }
                break;
            case HISTORY:
                displayHistory(!showHistory);
                break;
            default:
                if (!showHistory) {
                    String newText = String.valueOf((char) primaryCode);
                    inputConnection.commitText(newText, 1);
                }
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
