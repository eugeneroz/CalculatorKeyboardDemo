package com.eugene.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;

/**
 * Created by eugene on 18/08/2016.
 */
public class CalculatorKeyboard extends Keyboard {
    public CalculatorKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    @Override protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
            XmlResourceParser parser) {
        Key key = new Key(res, parent, x, y, parser);

        return key;
    }
}
