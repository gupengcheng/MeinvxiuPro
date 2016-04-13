package com.gpc.meinvxiupro.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.interfaces.DoubleClickListener;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;
import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    private CustomImageView mDetailImg;
    private RelativeLayout mLoadingView;
    private TextView mSetWallpaperView;
    private TextView mCollectWallpaperView;

    private int mParentImagePosition;
    private static final int DEFAULT_PARENT_IMAGE_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mDetailImg = (CustomImageView) findViewById(R.id.img_detail);
        mLoadingView = (RelativeLayout) findViewById(R.id.common_loading);
        mSetWallpaperView = (TextView) findViewById(R.id.set_wallpaper);
        mCollectWallpaperView = (TextView) findViewById(R.id.collect_wallpaper);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
        initListener();
    }

    private void getData() {
        Bundle bundle = getIntent().getBundleExtra(Constant.ContextConstant.BUNDLE_NAME);
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        ImgsEntity imgsEntity = bundle.getParcelable(Constant.BundleConstant.IMAGE_ENTITY);
        loadData(imgsEntity.getDownloadUrl());
    }

    private void loadData(String imagePath) {
        mLoadingView.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(imagePath).into(mDetailImg, new Callback() {
            @Override
            public void onSuccess() {
                mLoadingView.setVisibility(View.GONE);
                getPaletteColor(mDetailImg);
            }

            @Override
            public void onError() {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }

    private void initListener() {
        mDetailImg.setOnDoubleClickListener(new DoubleClickListener() {
            @Override
            public void OnTwiceClickListener() {
                ImageDetailActivity.this.finish();
            }
        });

        mDetailImg.setOnTouchDistanceListener(new CustomImageView.OnTouchDistanceListener() {
            @Override
            public void setWallpaper() {
                WallpaperUtils.setWallpaper(mContext, ImageUtils.getImageViewBitmap(mDetailImg));
            }

            @Override
            public void collectWallpaper() {
                LogUtil.e(TAG, "collect wallpaper");
            }

        });
    }

    private void getPaletteColor(ImageView imageView) {
        Palette.PaletteAsyncListener paletteAsyncListener = new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                findViewById(R.id.image_detail_root_view).setBackgroundColor(mutedColor);
            }
        };
        Palette.from(ImageUtils.getImageViewBitmap(imageView)).generate(paletteAsyncListener);
    }
}
