package com.gpc.meinvxiupro.views.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pcgu on 16-4-20.
 */
public class RecyclerViewItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int mItemOffset;
    private int mPosition;
    private int mHalfItemOffSet;
    private int mItemCount;

    public RecyclerViewItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
        mHalfItemOffSet = mItemOffset / 2;
    }

    public RecyclerViewItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        mPosition = parent.getChildAdapterPosition(view);
        mItemCount = state.getItemCount();
        if (mPosition == 0) {
            outRect.set(mItemOffset, mItemOffset, mHalfItemOffSet, mHalfItemOffSet);
            return;
        }
        if (mPosition == 1) {
            outRect.set(mHalfItemOffSet, mItemOffset, mItemOffset, mHalfItemOffSet);
            return;
        }
        if (mPosition == mItemCount - 2) {
            outRect.set(mItemOffset, mHalfItemOffSet, mHalfItemOffSet, mItemOffset);
            return;
        }
        if (mPosition == mItemCount - 1) {
            outRect.set(mHalfItemOffSet, mHalfItemOffSet, mItemOffset, mItemOffset);
            return;
        }

        if (mPosition % 2 == 0) {
            outRect.set(mItemOffset, mHalfItemOffSet, mHalfItemOffSet, mHalfItemOffSet);
        } else {
            outRect.set(mHalfItemOffSet, mHalfItemOffSet, mItemOffset, mHalfItemOffSet);
        }
    }
}
