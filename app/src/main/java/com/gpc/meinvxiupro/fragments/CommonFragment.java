package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.adapters.CommonFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-3-23.
 */
public class CommonFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
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
        DataRequestManager.getInstance().getImageResult(getFragmentTitle(), 0,
                Schedulers.computation(),
                new Subscriber<ImageResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        setIsLoadData(false);
                    }

                    @Override
                    public void onNext(ImageResult imageResult) {
                        setIsLoadData(true);
                        if (null != imageResult && imageResult.getImgs() != null) {
                            mItems.addAll(imageResult.getImgs());
                            LogUtil.e(getFragmentTitle(), "onNext == " +
                                    imageResult.getImgs().get(0).getTitle() +
                                    " size == " + imageResult.getImgs().size());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void initViews() {
        LogUtil.e("CommonFragment", "isLoadData == " + isLoadData());
        setInflateLayout(R.layout.fragment_common);
        mItems = new ArrayList<>();
    }

    @Override
    protected void findViewByIds() {
        mRecyclerView = (RecyclerView) getInflateView().findViewById(R.id.common_recyclerview);
        mAdapter = new CommonFragmentAdapter(mItems, getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mAdapter);
    }
}
