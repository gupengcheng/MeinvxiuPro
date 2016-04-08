package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.fragments.CommonFragment;
import com.gpc.meinvxiupro.fragments.CommonTagFragment;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.views.adapters.HomePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";
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
        initToolbar();
        initTabLayoutTitle();
        initViewPager();
        initTabLayout();
    }

    private void initToolbar() {
        setSupportActionBar(mHomeToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initTabLayoutTitle() {
        mHomeTabTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.tag_array)));
    }

    private void initViewPager() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        for (String title : mHomeTabTitles) {
            LogUtil.e(TAG, "tag == " + title);
            mAdapter.addTab(CommonTagFragment.newInstance(title), title);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new CubeOutTransformer());
        mViewPager.setOffscreenPageLimit(1);
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
