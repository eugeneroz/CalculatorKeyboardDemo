package com.eugene.keyboard.util;

import android.content.Context;

/**
 * Created by eugene on 21/08/2016.
 */
public class SizeUtils {

    private SizeUtils() {
    }

    /**
     * Convert DP to PX
     *
     * @param context the context
     * @param dpValue size in dp
     * @return size in pixels
     */
    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}