package com.gpc.meinvxiupro.views.widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.ToastUtils;

/**
 * Created by pcgu on 16-4-12.
 */
public class CustomImageView extends ImageView {
    private static final String TAG = "CustomImageView";
    private static final long EXIT_INTERVAL = 1000;
    private long mSysClickLastTime = 0;
    private OnTouchListener mOnTouchListener;
    private int mClickCount = 1;

    private static final int DISTANCE_Y_ADD_STATUS_SET_WALLPAPER = 80;
    private static final int DISTANCE_Y_SET_WALLPAPER = 56;
    private static final int DISTANCE_Y_COLLECT_WALLPAPER = -56;
    //default value is no status height
    private int mDistanceYSetWallpaper = DISTANCE_Y_SET_WALLPAPER;
    private int mLastY;
    //为了判断是不是点击事件
    private int mLastX;
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
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawY = (int) event.getRawY();
        int rawX = (int) event.getRawX();
        int tempY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = rawY;
                mLastX = rawX;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetY = rawY - mLastY;
                tempY = mTotalY + offsetY;
                if (tempY > 0) {
                    if (tempY >= PixelUtil.dp2px(getContext(), mDistanceYSetWallpaper)) {
                        offsetTopAndBottom(PixelUtil.dp2px(getContext(), mDistanceYSetWallpaper) - mTotalY);
                        mTotalY = PixelUtil.dp2px(getContext(), mDistanceYSetWallpaper);
                    } else {
                        offsetTopAndBottom(offsetY);
                        mTotalY += offsetY;
                    }
                } else if (tempY < 0) {
                    if (tempY <= PixelUtil.dp2px(getContext(), DISTANCE_Y_COLLECT_WALLPAPER)) {
                        offsetTopAndBottom(PixelUtil.dp2px(getContext(), DISTANCE_Y_COLLECT_WALLPAPER) - mTotalY);
                        mTotalY = PixelUtil.dp2px(getContext(), DISTANCE_Y_COLLECT_WALLPAPER);
                    } else {
                        offsetTopAndBottom(offsetY);
                        mTotalY += offsetY;
                    }
                } else {
                    offsetTopAndBottom(0 - mTotalY);
                    mTotalY = 0;
                }
                mLastY = rawY;
                mLastX = rawX;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(mLastY - rawY) <= 1 && Math.abs(mLastX - rawX) <= 1 && Math.abs(mTotalY) == 0) {
                    mOnTouchListener.onClick();
                }
                resetAnimation();
                break;
            case MotionEvent.ACTION_CANCEL:
                resetAnimation();
        }
        return true;
    }

    private void resetAnimation() {
        if (mTotalY == PixelUtil.dp2px(getContext(), mDistanceYSetWallpaper)) {
            mOnTouchListener.setWallpaper();
        }
        if (mTotalY == PixelUtil.dp2px(getContext(), DISTANCE_Y_COLLECT_WALLPAPER)) {
            mOnTouchListener.collectWallpaper();
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
        mLastX = 0;
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
//            mDoubleClickListener.OnTwiceClickListener();
            mClickCount = 0;
            mSysClickLastTime = now;
        } else {
            mClickCount = 0;
            mSysClickLastTime = now;
        }
    }

    public void setOnTouchListener(OnTouchListener listener) {
        this.mOnTouchListener = listener;
    }


//    public void setOnDoubleClickListener(DoubleClickListener listener) {
//        this.mDoubleClickListener = listener;
//    }

    public void setAddStatusBarDistanceYSetWallpaper() {
        this.mDistanceYSetWallpaper = DISTANCE_Y_ADD_STATUS_SET_WALLPAPER;
    }

    public interface OnTouchListener {
        void setWallpaper();

        void collectWallpaper();

        void onClick();

    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}
