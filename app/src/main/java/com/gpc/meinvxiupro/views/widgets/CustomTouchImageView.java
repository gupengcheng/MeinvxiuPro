package com.gpc.meinvxiupro.views.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.ToastUtils;

/**
 * Created by pcgu on 16-4-12.
 */
public class CustomTouchImageView extends ImageView implements View.OnClickListener {
    private static final long EXIT_INTERVAL = 1000;
    private long mSysClickLastTime = 0;
    private int mClickCount = 1;
    private CustomImageOnClickListener mCustomImageOnClickListener;
    private float mDy;

    public CustomTouchImageView(Context context) {
        super(context);
        init();
    }

    public CustomTouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CustomTouchImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        twiceClickResponse();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                onMoveEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void onMoveEvent(MotionEvent event) {
        scrollTo(0, (int) (event.getY() - mDy));
        mDy = event.getY();
    }

    public void setOnClickListener(CustomImageOnClickListener customImageOnClickListener) {
        this.mCustomImageOnClickListener = customImageOnClickListener;
    }

    public interface CustomImageOnClickListener {
        public abstract void OnTwiceClickListener();
    }

    private void twiceClickResponse() {
        long now = System.currentTimeMillis();
        if (now - mSysClickLastTime > EXIT_INTERVAL) {
            mClickCount = 0;
        }
        if (mClickCount == 0) {
            mSysClickLastTime = now;
        }
        mClickCount++;
        if (mClickCount == 1) {
            ToastUtils.showShortToast(getContext(), R.string.click_again_exit);
        } else if (mClickCount == 2) {
            mCustomImageOnClickListener.OnTwiceClickListener();
            mClickCount = 0;
            mSysClickLastTime = now;
        } else {
            mClickCount = 0;
            mSysClickLastTime = now;
        }
    }
}
