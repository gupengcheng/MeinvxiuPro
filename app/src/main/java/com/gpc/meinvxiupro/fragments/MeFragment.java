package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.activities.AboutUsActivity;
import com.gpc.meinvxiupro.activities.FeedbackActivity;
import com.gpc.meinvxiupro.activities.MyCollectActivity;
import com.gpc.meinvxiupro.activities.MyDownloadActivity;
import com.gpc.meinvxiupro.activities.SettingActivity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;

/**
 * Created by pcgu on 16-4-20.
 */
public class MeFragment extends BaseFragment {
    private TextView mCollectTv;
    private TextView mDownloadTv;
    private TextView mAboutUsTv;
    private TextView mFeedbackTv;
    private TextView mSettingTv;
    private TextView mStarMeTv;

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
        mDownloadTv = (TextView) getInflateView().findViewById(R.id.my_download);
        mAboutUsTv = (TextView) getInflateView().findViewById(R.id.about_us);
        mSettingTv = (TextView) getInflateView().findViewById(R.id.setting);
        mFeedbackTv = (TextView) getInflateView().findViewById(R.id.feedback);
        mStarMeTv = (TextView) getInflateView().findViewById(R.id.star_me);
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
        mDownloadTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), MyDownloadActivity.class);
            }
        });
        mAboutUsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), AboutUsActivity.class);
            }
        });
        mFeedbackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), FeedbackActivity.class);
            }
        });
        mSettingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.goActivity(getContext(), SettingActivity.class);
            }
        });
        mStarMeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.launchMarketApp(getContext(), getContext().getPackageName(),
                        Constant.MarketAppPackage.XIAO_MI);
            }
        });
    }
}
