package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.fragments.CommonFragment;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.views.adapters.HomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private Toolbar mHomeToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private HomePagerAdapter mAdapter;
    private List<String> mHomeTabTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mHomeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.home_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initTabLayoutTitle();
        initViewPager();
        initTabLayout();
    }

    private void initTabLayoutTitle() {
        mHomeTabTitles.addAll(SharedPreferencesUtils.getHomeTabsTitle(mContext));
    }

    private void initViewPager() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        for (String title : mHomeTabTitles) {
            CommonFragment commonFragment = new CommonFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.BundleConstant.FRAGMENT_TITLE, title);
            commonFragment.setArguments(bundle);
            mAdapter.addTab(commonFragment, title);
        }
        mViewPager.setAdapter(mAdapter);
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void loadData() {
    }

}
