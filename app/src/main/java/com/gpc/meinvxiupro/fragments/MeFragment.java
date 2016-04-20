package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.activities.AboutUsActivity;
import com.gpc.meinvxiupro.activities.MyCollectActivity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;

/**
 * Created by pcgu on 16-4-20.
 */
public class MeFragment extends BaseFragment {
    private TextView mCollectTv;
    private TextView mAboutUsTv;

    public static MeFragment newInstance(String tag) {
        MeFragment meFragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(Constant.BundleConstant.FRAGMENT_TITLE, tag);
        meFragment.setArguments(args);
        return meFragment;
    }

    @Override
    protected void initInflateView() {
        super.initInflateView();
        setInflateLayout(R.layout.fragment_me);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mCollectTv = (TextView) getInflateView().findViewById(R.id.my_collect);
        mAboutUsTv = (TextView) getInflateView().findViewById(R.id.about_us);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initListener();
    }

    private void initListener() {
        mCollectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), MyCollectActivity.class);
            }
        });
        mAboutUsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), AboutUsActivity.class);
            }
        });
    }
}
