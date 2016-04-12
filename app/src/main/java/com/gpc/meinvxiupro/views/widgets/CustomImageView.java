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
public class CustomImageView extends ImageView implements View.OnClickListener {
    private static final long EXIT_INTERVAL = 1000;
    private long mSysClickLastTime = 0;
    private int mClickCount = 1;
    private DoubleClickListener mDoubleClickListener;
    private int mLastMoveY;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        twiceClickResponse();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int currentY;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = (int) event.getY();
                int distanceY = currentY - mLastMoveY;
                scrollTo(0, distanceY);
                mLastMoveY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                currentY = (int) event.getY();
                mLastMoveY = currentY;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public void setOnDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.mDoubleClickListener = doubleClickListener;
    }

    public interface DoubleClickListener {
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
            mDoubleClickListener.OnTwiceClickListener();
            mClickCount = 0;
            mSysClickLastTime = now;
        } else {
            mClickCount = 0;
            mSysClickLastTime = now;
        }
    }
}
