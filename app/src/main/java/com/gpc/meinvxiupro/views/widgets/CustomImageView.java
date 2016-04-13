package com.gpc.meinvxiupro.views.widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.views.interfaces.DoubleClickListener;

/**
 * Created by pcgu on 16-4-12.
 */
public class CustomImageView extends ImageView implements View.OnClickListener {
    private static final String TAG = "CustomImageView";
    private static final long EXIT_INTERVAL = 1000;
    private long mSysClickLastTime = 0;
    private DoubleClickListener mDoubleClickListener;
    private OnTouchDistanceListener mOnTouchDistanceListener;
    private int mClickCount = 1;

    private static final int DISTANCE_Y_SET_WALLPAPER = 80;
    private static final int DISTANCE_Y_COLLECT_WALLPAPER = -56;
    private int mLastY;
    private int mTotalY;
    private ObjectAnimator mResetAnimator;

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
        int rawY = (int) event.getRawY();
        int tempY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetY = rawY - mLastY;
                tempY = mTotalY + offsetY;
                if (tempY >= PixelUtil.dp2px(getContext(), DISTANCE_Y_SET_WALLPAPER)) {
                    offsetTopAndBottom(PixelUtil.dp2px(getContext(), DISTANCE_Y_SET_WALLPAPER) - mTotalY);
                    mTotalY = PixelUtil.dp2px(getContext(), DISTANCE_Y_SET_WALLPAPER);
                } else if (tempY <= 0) {
                    offsetTopAndBottom(0 - mTotalY);
                    mTotalY = 0;
                } else {
                    offsetTopAndBottom(offsetY);
                    mTotalY += offsetY;
                }
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                resetAnimation();
        }
        return true;
    }

    private void resetAnimation() {
        if (mTotalY == PixelUtil.dp2px(getContext(), DISTANCE_Y_SET_WALLPAPER)) {
            mOnTouchDistanceListener.setWallpaper();
        }
        if (mTotalY == PixelUtil.dp2px(getContext(), DISTANCE_Y_COLLECT_WALLPAPER)) {
            mOnTouchDistanceListener.collectWallpaper();
        }
        if (mResetAnimator != null && mResetAnimator.isRunning()) {
            return;
        }
        mResetAnimator = ObjectAnimator.ofInt(mTotalY, "int", mTotalY, 0);
        mResetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                offsetTopAndBottom(-(mTotalY - animatedValue));
                mTotalY = animatedValue;
            }
        });
        mResetAnimator.start();
        mLastY = 0;
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

    public void setOnTouchDistanceListener(OnTouchDistanceListener listener) {
        this.mOnTouchDistanceListener = listener;
    }


    public void setOnDoubleClickListener(DoubleClickListener listener) {
        this.mDoubleClickListener = listener;
    }

    public interface OnTouchDistanceListener {
        public abstract void setWallpaper();

        public abstract void collectWallpaper();
    }
}
