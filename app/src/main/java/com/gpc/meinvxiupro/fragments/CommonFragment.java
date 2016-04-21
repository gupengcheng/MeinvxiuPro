package com.gpc.meinvxiupro.fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.activities.ImageDetailActivity;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.NetworkUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.views.adapters.CommonFragmentAdapter;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
import com.gpc.meinvxiupro.views.widgets.RecyclerViewItemOffsetDecoration;
import com.gpc.meinvxiupro.views.widgets.hitblockrefresh.FunGameRefreshView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by pcgu on 16-3-23.
 */
public class CommonFragment extends BaseFragment {
    private static final String TAG = "CommonFragment";

    private FunGameRefreshView mFunGameRefreshView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RelativeLayout mLoadingView;
    private RelativeLayout mLoadMoreView;
    private CommonFragmentAdapter mAdapter;
    private RecyclerViewItemOffsetDecoration mItemOffsetDecoration;
    private List<ImgsEntity> mItems = new ArrayList<>();
    private static final int PAGE_NUM = 30;

    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mFirstVisibleItem;

    public static CommonFragment newInstance(String title) {
        CommonFragment commonFragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(Constant.BundleConstant.FRAGMENT_TITLE, title);
        commonFragment.setArguments(args);
        return commonFragment;
    }

    @Override
    protected void loadDataFirst() {
        super.loadDataFirst();
    }

    @Override
    protected void initInflateView() {
        super.initInflateView();
        setInflateLayout(R.layout.fragment_common);
    }

    @Override
    protected void findViewByIds() {
        mFunGameRefreshView = (FunGameRefreshView) getInflateView().findViewById(R.id.common_fungamerefreshview);
        mRecyclerView = (RecyclerView) getInflateView().findViewById(R.id.common_recyclerview);
        mLoadingView = (RelativeLayout) getInflateView().findViewById(R.id.common_loading);
        mLoadMoreView = (RelativeLayout) getInflateView().findViewById(R.id.common_load_more);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mAdapter = new CommonFragmentAdapter(mItems, getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mItemOffsetDecoration = new RecyclerViewItemOffsetDecoration(getContext(), R.dimen.item_offset);
        mRecyclerView.addItemDecoration(mItemOffsetDecoration);
        mRecyclerView.setAdapter(mAdapter);
        if (getStartIndex() == 0 && mItems.isEmpty()) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setListeners() {
        mFunGameRefreshView.setOnRefreshListener(new FunGameRefreshView.FunGameRefreshListener() {
            @Override
            public void onRefreshing() {
                refreshData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollListener(dy);
            }
        });

        mAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                LogUtil.e(TAG, "position ->" + position);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.BundleConstant.IMAGE_POSITION, position);
                bundle.putParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS, (ArrayList<ImgsEntity>) mItems);
                ContextUtils.goActivity(getContext(), ImageDetailActivity.class, bundle);
            }
        });
    }

    private List<ImgsEntity> getFilterEndNullItems(ImageResult imageResult) {
        List<ImgsEntity> notNullImageResults;
        if (imageResult.getImgs().get(imageResult.getImgs().size() - 1) != null &&
                !TextUtils.isEmpty(imageResult.getImgs().get(imageResult.getImgs().size() - 1).getId())) {
            notNullImageResults = imageResult.getImgs();
        } else {
            notNullImageResults = imageResult.getImgs().subList(0, imageResult.getImgs().size() - 1);
        }
        return notNullImageResults;
    }

    @Override
    protected void loadCacheData() {
        if (getStartIndex() == 0 && mItems.isEmpty()) {
            DataRequestManager.getInstance().getImageCache(
                    getContext(),
                    getFragmentTitle(),
                    new Subscriber<ImageResult>() {
                        @Override
                        public void onCompleted() {
                            LogUtil.e(TAG, "loadCacheData onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e(TAG, "loadCacheData onError ->" + e.toString());
                            onErrorView();
                        }

                        @Override
                        public void onNext(ImageResult imageResult) {
                            LogUtil.e(TAG, "loadCacheData onNext");
                            onSucceedView(imageResult);
                        }
                    }
            );
        }
    }

    @Override
    protected void loadData() {
        if (NetworkUtils.isNetworkAvailable()) {
            setIsLoadingMoreData(true);
            DataRequestManager.getInstance().getImageResult(
                    getContext(),
                    getFragmentTitle(),
                    getStartIndex(),
                    new Subscriber<ImageResult>() {
                        @Override
                        public void onCompleted() {
                            LogUtil.e(TAG, "loadData onCompleted");
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            LogUtil.e(TAG, "loadData onError ->" + throwable.toString());
                            onErrorView();
                            setIsLoadingMoreData(false);
                            //用户滑动页面还会执行loadFirstData
                            if (getStartIndex() == 0) {
                                setIsLoadFirstPageData(false);
                            }
                        }

                        @Override
                        public void onNext(ImageResult imageResult) {
                            LogUtil.e(TAG, "loadData onNext");
                            onSucceedView(imageResult);
                            setIsLoadingMoreData(false);
                            setStartIndex(getStartIndex() + imageResult.getReturnNumber());
                            setCurrentPage(getCurrentPage() + 1);
                            //表示当前页面的首页数据已经加载完成，用户滑动不用在执行loadFirstData
                            if (getStartIndex() == 0) {
                                setIsLoadFirstPageData(true);
                            }
                        }
                    });
        }
    }

    private void refreshData() {
        setStartIndex(0);
        setCurrentPage(0);
        loadData();
    }

    private void onErrorView() {
        mLoadingView.setVisibility(View.GONE);
        mLoadMoreView.setVisibility(View.GONE);
        mFunGameRefreshView.finishRefreshing();
    }

    private void onSucceedView(ImageResult imageResult) {
        if (null != imageResult && imageResult.getImgs() != null) {
            LogUtil.e(TAG, "onSucceedView" + imageResult.getTotalNum());
            if (getStartIndex() == 0) {
                mItems.clear();
                setTotalPage(imageResult.getTotalNum() % PAGE_NUM == 0 ?
                        imageResult.getTotalNum() / PAGE_NUM :
                        imageResult.getTotalNum() / PAGE_NUM + 1);
            }
            int positionStart = mItems.size();
            List<ImgsEntity> results = getFilterEndNullItems(imageResult);
            mItems.addAll(results);
            mAdapter.notifyItemRangeChanged(positionStart, results.size());
            mLoadMoreView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.GONE);
            mFunGameRefreshView.finishRefreshing();
        }
    }

    private void scrollListener(int dy) {
        if (dy > 0) {
            if (isLoadAll()) {
                return;
            }
            mVisibleItemCount = mRecyclerView.getChildCount();
            mTotalItemCount = mGridLayoutManager.getItemCount();
            mFirstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
            if (!isLoadingMoreData() && getCurrentPage() < getTotalPage() &&
                    mTotalItemCount - mVisibleItemCount - 2 <= mFirstVisibleItem) {
                mLoadMoreView.setVisibility(View.VISIBLE);
                loadData();
            } else if (getCurrentPage() == getTotalPage() &&
                    mTotalItemCount - mVisibleItemCount == mFirstVisibleItem) {
                setIsLoadAll(true);
                ToastUtils.showLongSnakeBar(getView(), getFragmentTitle() +
                                getResources().getString(R.string.load_all),
                        (((ColorDrawable) ((Activity) getContext())
                                .findViewById(R.id.home_toolbar).getBackground()).getColor()));
            }
        }
    }
}
