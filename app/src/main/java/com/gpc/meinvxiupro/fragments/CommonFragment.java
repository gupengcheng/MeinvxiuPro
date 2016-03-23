package com.gpc.meinvxiupro.fragments;

import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.utils.LogUtil;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-3-23.
 */
public class CommonFragment extends BaseFragment {
    private TextView mTitleTv;

    @Override
    protected void loadDataFirst() {
        DataRequestManager.getInstance().getImageResult(getFragmentTitle(), 0, Schedulers.computation(),
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
                        LogUtil.e(getFragmentTitle(), "onNext == " +
                                imageResult.getImgs().get(0).getTitle());
                        mTitleTv.setText(getFragmentTitle() +
                                imageResult.getImgs().get(0).getTitle());
                    }
                });
    }

    @Override
    protected void initViews() {
        setInflateLayout(R.layout.fragment_common);
    }

    @Override
    protected void findViewByIds() {
        mTitleTv = (TextView) getInflateView().findViewById(R.id.common_text);
        setIsLoadData(true);
    }
}
