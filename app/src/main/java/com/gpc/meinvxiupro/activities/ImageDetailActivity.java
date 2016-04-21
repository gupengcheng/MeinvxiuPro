package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
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
    private static final String TAG = "ImageDetailActivity";
    private ViewPager mDetailViewPager;
    private Toolbar mDetailToolbar;
    //    private RelativeLayout mLoadingMoreView;
    private ImageDetailAdapter mAdapter;

    private int mParentImagePosition;
    private ArrayList<ImgsEntity> mItems;
    private static final int DEFAULT_PARENT_IMAGE_POSITION = 0;

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
//        mLoadingMoreView = (RelativeLayout) findViewById(R.id.loading_more);
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
        Bundle bundle = getIntent().getBundleExtra(Constant.ContextConstant.BUNDLE_NAME);
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        mItems = bundle.getParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS);
    }

    private void initToolbar() {
        mDetailToolbar.setBackgroundResource(R.color.argb_33000000);
        mDetailToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageDetailActivity.this.finish();
                    }
                }
        );
        mDetailToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mDetailToolbar.findViewById(R.id.tool_title))
                .setText(mItems.get(mParentImagePosition).getDesc());
        mDetailToolbar.findViewById(R.id.download).setVisibility(View.VISIBLE);
        mDetailToolbar.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = (View) mDetailViewPager.findViewWithTag(mDetailViewPager.getCurrentItem());
                ToastUtils.showShortSnakeBar(view,
                        getResources().getString(R.string.download_wallpaper_start));
                WallpaperUtils.downloadWallpaper(mItems.get(mParentImagePosition).getTitle() +
                                mItems.get(mParentImagePosition).getId(),
                        ((CustomImageView) view.findViewById(R.id.img_detail)),
                        new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.e(TAG, "onError ->" + e.toString());
                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                LogUtil.e(TAG, "onNext ->" + aBoolean);
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
        mDetailViewPager.setAdapter(mAdapter);
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

}
