package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.PageUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.adapters.ImageDetailAdapter;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private ViewPager mDetailViewPager;
    private Toolbar mDetailToolbar;
    private ImageDetailAdapter mAdapter;

    private int mParentImagePosition;
    private int mLoadUrlTag;
    private ArrayList<ImgsEntity> mItems;
    private static final int DEFAULT_PARENT_IMAGE_POSITION = 0;
    private boolean mFromPush = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mDetailViewPager = (ViewPager) findViewById(R.id.viewpager);
        mDetailToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
        initToolbar();
        initViewPager();
        initListener();
    }

    private void getData() {
        LogUtil.e("ImageDetailActivity", "getData");
        Bundle bundle = getIntent().getBundleExtra(Constant.ContextConstant.BUNDLE_NAME);
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        mItems = bundle.getParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS);
        mLoadUrlTag = bundle.getInt(Constant.BundleConstant.LOAD_TAG, Constant.CommonData.LOAD_DEFAULT);
        mFromPush = bundle.getBoolean(Constant.BundleConstant.FROM_PUSH, false);
    }

    private void initToolbar() {
        mDetailToolbar.setBackgroundResource(R.color.argb_33000000);
        mDetailToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exit();
                    }
                }
        );
        mDetailToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mDetailToolbar.findViewById(R.id.tool_title))
                .setText(mItems.get(mParentImagePosition).getDesc());
        if (mLoadUrlTag == Constant.CommonData.LOAD_DEFAULT) {
            mDetailToolbar.findViewById(R.id.download).setVisibility(View.VISIBLE);
        } else {
            mDetailToolbar.findViewById(R.id.download).setVisibility(View.GONE);
        }
        mDetailToolbar.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = mDetailViewPager.findViewWithTag(mDetailViewPager.getCurrentItem());
                ToastUtils.showShortSnakeBar(view,
                        getResources().getString(R.string.download_wallpaper_start));
                WallpaperUtils.downloadWallpaper(mItems.get(mParentImagePosition).getTitle() + "-" +
                                mItems.get(mParentImagePosition).getId(),
                        ((CustomImageView) view.findViewById(R.id.img_detail)),
                        new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {
                                    ToastUtils.showShortSnakeBar(findViewById(android.R.id.content),
                                            getResources().getString(R.string.download_wallpaper_succeed));
                                } else {
                                    ToastUtils.showShortSnakeBar(findViewById(android.R.id.content),
                                            getResources().getString(R.string.download_wallpaper_failed));
                                }
                            }
                        });
            }
        });
    }

    private void initViewPager() {
        mAdapter = new ImageDetailAdapter(mContext, mItems);
        mAdapter.setLoadUrlTag(mLoadUrlTag);
        mDetailViewPager.setAdapter(mAdapter);
        mDetailViewPager.setPageTransformer(true, PageUtils.getPageTransformer(mContext));
        mDetailViewPager.setCurrentItem(mParentImagePosition);
        mDetailViewPager.setOffscreenPageLimit(1);
    }

    private void initListener() {
        mDetailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((TextView) mDetailToolbar.findViewById(R.id.tool_title))
                        .setText(mItems.get(position).getDesc());
                mParentImagePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (mFromPush) {
            ContextUtils.goActivity(ImageDetailActivity.this, HomeActivity.class, true);
        } else {
            ImageDetailActivity.this.finish();
        }
    }
}
