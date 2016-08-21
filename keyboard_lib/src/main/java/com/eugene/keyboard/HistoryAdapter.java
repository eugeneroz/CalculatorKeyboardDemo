package com.eugene.keyboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.eugene.keyboard.math.Expression;
import java.util.List;

/**
 * Created by eugene on 21/08/2016.
 */
public class HistoryAdapter extends ArrayAdapter<Expression> {
    private static final String TAG = HistoryAdapter.class.getSimpleName();

    private View.OnClickListener mOnClickListener;

    public HistoryAdapter(Context context, int resource, List<Expression> objects) {
        super(context, resource, objects);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (mOnClickListener != null) {
            view.setOnClickListener(mOnClickListener);
        }

        view.setTag(position);

        return view;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
