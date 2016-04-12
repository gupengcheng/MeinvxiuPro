package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    private CustomImageView mDetailImg;
    private RelativeLayout mLoadingView;
    private TextView mSettingWallpaperTv;

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
        mSettingWallpaperTv = (TextView) findViewById(R.id.tv_set_wallpaper);
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
            }

            @Override
            public void onError() {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }

    private void initListener() {
        mSettingWallpaperTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WallpaperUtils.setWallpaper(mContext, ImageUtils.getImageViewBitmap(mDetailImg));
            }
        });

        mDetailImg.setOnDoubleClickListener(new CustomImageView.DoubleClickListener() {
            @Override
            public void OnTwiceClickListener() {
                ImageDetailActivity.this.finish();
            }
        });
    }
}
