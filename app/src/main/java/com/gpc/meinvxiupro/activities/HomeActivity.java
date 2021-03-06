package com.gpc.meinvxiupro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.fragments.CommonTagFragment;
import com.gpc.meinvxiupro.fragments.MeFragment;
import com.gpc.meinvxiupro.services.AutoSetWallpaperService;
import com.gpc.meinvxiupro.utils.PageUtils;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.views.adapters.HomePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
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
        initToolbar();
        initTabLayoutTitle();
        initViewPager();
        initTabLayout();
        setFirstLoad();
        startService();
    }

    private void initToolbar() {
        setSupportActionBar(mHomeToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mHomeToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private void initTabLayoutTitle() {
        mHomeTabTitles.addAll(Arrays.asList(getResources().getStringArray(R.array.tag_array)));
    }

    private void initViewPager() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        for (String title : mHomeTabTitles) {
            mAdapter.addTab(CommonTagFragment.newInstance(title), title);
        }
        mAdapter.addTab(MeFragment.newInstance(getResources().getString(R.string.tag_me))
                , getResources().getString(R.string.tag_me));
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, PageUtils.getPageTransformer(mContext));
        mViewPager.setOffscreenPageLimit(1);
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setFirstLoad() {
        if (SharedPreferencesUtils.getApplicationFirstLoadMore(mContext)) {
            SharedPreferencesUtils.setApplicationFirstLoadMore(mContext, false);
        }
    }

    private void startService() {
        Intent intent = new Intent(mContext, AutoSetWallpaperService.class);
        mContext.startService(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            ToastUtils.showShortToast(mContext, "home key down");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
