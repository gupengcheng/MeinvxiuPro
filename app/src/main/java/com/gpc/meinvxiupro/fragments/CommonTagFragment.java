package com.gpc.meinvxiupro.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.adapters.HomePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gupengcheng on 16/4/4.
 */
public class CommonTagFragment extends BaseFragment {
    private static final String TAG = "CommonTagFragment";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private HomePagerAdapter mAdapter;
    private List<String> mCommonTagTabTitles;

    public static CommonTagFragment newInstance(String tag) {
        CommonTagFragment commonTagFragment = new CommonTagFragment();
        Bundle args = new Bundle();
        args.putString(Constant.BundleConstant.FRAGMENT_TITLE, tag);
        commonTagFragment.setArguments(args);
        return commonTagFragment;
    }

    @Override
    protected void initInflateView() {
        super.initInflateView();
        setInflateLayout(R.layout.fragment_tag_common);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mTabLayout = (TabLayout) getInflateView().findViewById(R.id.common_tag_tab_layout);
        mViewPager = (ViewPager) getInflateView().findViewById(R.id.common_tag_viewpager);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mCommonTagTabTitles = new ArrayList<>();
        if (getFragmentTitle().equals("可爱")) {
            mCommonTagTabTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.tag_lovely_array)));
        } else if (getFragmentTitle().equals("高贵")) {
            mCommonTagTabTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.tag_nobility_array)));
        } else if (getFragmentTitle().equals("性感")) {
            mCommonTagTabTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.tag_sexy_array)));
        }
        mAdapter = new HomePagerAdapter(getChildFragmentManager());
        for (String title : mCommonTagTabTitles) {
            LogUtil.e(TAG, "title == " + title);
            mAdapter.addTab(CommonFragment.newInstance(title), title);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new CubeOutTransformer());
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
