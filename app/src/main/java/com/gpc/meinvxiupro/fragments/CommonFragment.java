package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.adapters.CommonFragmentAdapter;
import com.gpc.meinvxiupro.views.listener.EndlessRecyclerViewOnScrollListener;
import com.gpc.meinvxiupro.views.widgets.hitblockrefresh.FunGameRefreshView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-3-23.
 */
public class CommonFragment extends BaseFragment {
    private FunGameRefreshView mFunGameRefreshView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RelativeLayout mLoadingView;
    private RelativeLayout mLoadMoreView;
    private CommonFragmentAdapter mAdapter;
    private List<ImgsEntity> mItems;

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
        LogUtil.e("tst", "loadDataFirst");
        if (null != mLoadingView && mItems.isEmpty()) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
        setStartIndex(0);
        loadData();
    }

    @Override
    protected void initViews() {
        super.initViews();
        LogUtil.e("CommonFragment", "initViews");
        setInflateLayout(R.layout.fragment_common);
        mItems = new ArrayList<>();
    }

    @Override
    protected void findViewByIds() {
        mFunGameRefreshView = (FunGameRefreshView) getInflateView().findViewById(R.id.common_fungamerefreshview);
        mRecyclerView = (RecyclerView) getInflateView().findViewById(R.id.common_recyclerview);
        mLoadingView = (RelativeLayout) getInflateView().findViewById(R.id.common_loading);
        mLoadMoreView = (RelativeLayout) getInflateView().findViewById(R.id.common_loadmore);
        mAdapter = new CommonFragmentAdapter(mItems, getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
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
                setStartIndex(0);
                loadData();
            }
        });

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(mGridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mLoadMoreView.setVisibility(View.VISIBLE);
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private List<ImgsEntity> getFilterEndNullItems(ImageResult imageResult) {
        List<ImgsEntity> notNullImageResults = new ArrayList<ImgsEntity>();
        if (imageResult.getImgs().get(imageResult.getImgs().size() - 1) != null &&
                !TextUtils.isEmpty(imageResult.getImgs().get(imageResult.getImgs().size() - 1).getId())) {
            notNullImageResults = imageResult.getImgs();
        } else {
            notNullImageResults = imageResult.getImgs().subList(0, imageResult.getImgs().size() - 1);
        }
        return notNullImageResults;
    }

    private void loadData() {
        DataRequestManager.getInstance().getImageResult(getFragmentTitle(), getStartIndex(), Schedulers.computation(),
                new Subscriber<ImageResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (mItems.isEmpty()) {
                            setIsLoadData(false);
                        }
                        mLoadingView.setVisibility(View.GONE);
                        mLoadMoreView.setVisibility(View.GONE);
                        mFunGameRefreshView.finishRefreshing();
                    }

                    @Override
                    public void onNext(ImageResult imageResult) {
                        if (null != imageResult && imageResult.getImgs() != null) {
                            if (getStartIndex() == 0) {
                                mItems.clear();
                                mLoadingView.setVisibility(View.GONE);
                            }
                            setStartIndex(getStartIndex() + 1);
                            mItems.addAll(getFilterEndNullItems(imageResult));
                            mAdapter.notifyDataSetChanged();
                            mLoadMoreView.setVisibility(View.GONE);
                            mFunGameRefreshView.finishRefreshing();
                        }
                    }
                });
    }
}
