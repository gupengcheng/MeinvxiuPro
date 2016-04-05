package com.gpc.meinvxiupro.views.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pcgu on 16-4-5.
 */
public class NumberIncreaseView extends View implements View.OnClickListener{

    public NumberIncreaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberIncreaseView(Context context) {
        super(context);
    }

    public NumberIncreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public NumberIncreaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View v) {

    }
}
