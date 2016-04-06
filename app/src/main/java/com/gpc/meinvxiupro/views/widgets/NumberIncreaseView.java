package com.gpc.meinvxiupro.views.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.LogUtil;

/**
 * Created by pcgu on 16-4-5.
 */
public class NumberIncreaseView extends View implements View.OnClickListener {
    private static final String TAG = "NumberIncreaseView";
    private int mNum;
    private int mNumColor;
    private float mNumSize;
    private Paint mPaint;
    private Paint mBgPaint;

    private static final int DEFAULT_VIEW_WIDTH = 100;
    private static final int DEFAULT_VIEW_HEIGHT = 100;

    public NumberIncreaseView(Context context) {
        super(context);
        init(context);
    }

    public NumberIncreaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public NumberIncreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    @TargetApi(21)
    public NumberIncreaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        init(context);
    }

    @Override
    public void onClick(View v) {
        increaseNum();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberIncreaseView);
        mNum = typedArray.getInt(R.styleable.NumberIncreaseView_increase_num, 0);
        mNumColor = typedArray.getColor(R.styleable.NumberIncreaseView_num_color,
                ContextCompat.getColor(context, R.color.colorPrimary));
        mNumSize = typedArray.getDimension(R.styleable.NumberIncreaseView_num_size, 12);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(mNumColor);
        mPaint.setTextSize(mNumSize);
        mBgPaint = new Paint();
        mBgPaint.setColor(ContextCompat.getColor(context, R.color.rgb_e9e9e9));
    }

    private void increaseNum() {
        ++mNum;
        LogUtil.e(TAG, "  mNum == " + mNum);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    protected int measureDimension(int defaultSize, int measureSpec) {

        int result = defaultSize;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //1. layout给出了确定的值，比如：100dp
        //2. layout使用的是match_parent，但父控件的size已经可以确定了，比如设置的是具体的值或者match_parent
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize; //建议：result直接使用确定值
        }
        //1. layout使用的是wrap_content
        //2. layout使用的是match_parent,但父控件使用的是确定的值或者wrap_content
        else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize); //建议：result不能大于specSize
        }
        //UNSPECIFIED,没有任何限制，所以可以设置任何大小
        //多半出现在自定义的父控件的情况下，期望由自控件自行决定大小
        else {
            result = defaultSize;
        }

        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.e(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.e(TAG, "onDraw");
        super.onDraw(canvas);
        canvas.drawCircle(0, 0, getMeasuredWidth() / 2, mBgPaint);
        canvas.drawText(String.valueOf(mNum), getMeasuredWidth() / 2, getMeasuredHeight() / 2, mPaint);
    }
}
