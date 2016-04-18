package com.gpc.meinvxiupro.fragments;

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
import com.gpc.meinvxiupro.utils.JsonUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.views.adapters.CommonFragmentAdapter;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
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
    private static final String TAG = "CommonFragment";

    private FunGameRefreshView mFunGameRefreshView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RelativeLayout mLoadingView;
    private RelativeLayout mLoadMoreView;
    private CommonFragmentAdapter mAdapter;
    private List<ImgsEntity> mItems = new ArrayList<>();

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
        mLoadMoreView = (RelativeLayout) getInflateView().findViewById(R.id.common_loadmore);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mAdapter = new CommonFragmentAdapter(mItems, getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if (getStartIndex() == 0) {
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

        mAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(ImgsEntity imgsEntity, int position) {
                LogUtil.e(TAG, "position ->" + position + "  imgsEntity ->" + imgsEntity.getTitle());
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.BundleConstant.IMAGE_ENTITY, imgsEntity);
                bundle.putInt(Constant.BundleConstant.IMAGE_POSITION, position);
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

    @Override
    protected void loadData() {
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
                    }

                    @Override
                    public void onNext(ImageResult imageResult) {
                        LogUtil.e(TAG, "loadData onNext");
                        onSucceedView(imageResult);
                        setStartIndex(getStartIndex() + 1);
                    }
                });
    }

    private void onErrorView() {
        if (mItems.isEmpty()) {
            setIsLoadData(false);
        }
        mLoadingView.setVisibility(View.GONE);
        mLoadMoreView.setVisibility(View.GONE);
        mFunGameRefreshView.finishRefreshing();
    }

    private void onSucceedView(ImageResult imageResult) {
        LogUtil.e(TAG, "onSucceedView");
        if (null != imageResult && imageResult.getImgs() != null) {
            if (getStartIndex() == 0) {
                mItems.clear();
            }
            mItems.addAll(getFilterEndNullItems(imageResult));
            mAdapter.notifyDataSetChanged();
            mLoadMoreView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.GONE);
            mFunGameRefreshView.finishRefreshing();
        }
    }
}
