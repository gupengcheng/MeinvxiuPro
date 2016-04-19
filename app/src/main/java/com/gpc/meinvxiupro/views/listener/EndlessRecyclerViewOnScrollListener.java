package com.gpc.meinvxiupro.views.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.gpc.meinvxiupro.utils.NetworkUtils;

/**
 * Created by pcgu on 16-4-5.
 */
public abstract class EndlessRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggerGridLayoutManager;
    private int mFirstVisibleItem;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    private int mCurrentPage = 0;

    public EndlessRecyclerViewOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public EndlessRecyclerViewOnScrollListener(GridLayoutManager gridLayoutManager) {
        this.mGridLayoutManager = gridLayoutManager;
    }

    public EndlessRecyclerViewOnScrollListener(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.mStaggerGridLayoutManager = staggeredGridLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            mVisibleItemCount = recyclerView.getChildCount();
            if (null != mLinearLayoutManager) {
                mTotalItemCount = mLinearLayoutManager.getItemCount();
                mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            } else if (null != mGridLayoutManager) {
                mTotalItemCount = mGridLayoutManager.getItemCount();
                mFirstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
            } else if (null != mStaggerGridLayoutManager) {
                mTotalItemCount = mStaggerGridLayoutManager.getItemCount();
                int[] into = new int[2];
                mFirstVisibleItem = mStaggerGridLayoutManager.findFirstVisibleItemPositions(into)[0];
            }
            if (mLoading) {
                if (mTotalItemCount > mPreviousTotal) {
                    mLoading = false;
                    mPreviousTotal = mTotalItemCount;
                }
            }
            if (!mLoading
                    && (mTotalItemCount - mVisibleItemCount) <= mFirstVisibleItem) {
                mCurrentPage++;
                onLoadMore(mCurrentPage);
                mLoading = true;
            }
        }
    }

    public void setLoading(boolean loading) {
        this.mLoading = loading;
    }

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
    }

    public int getCurrentPage() {
        return this.mCurrentPage;
    }

    public abstract void onLoadMore(int currentPage);
}
