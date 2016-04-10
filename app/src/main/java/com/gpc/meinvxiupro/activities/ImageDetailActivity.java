package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    private ImageView mDetailImg;
    private RelativeLayout mLoadingView;
    private TextView mSettingWallpaperTv;
    private TextView mSettingLockScreenTv;

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
        mDetailImg = (ImageView) findViewById(R.id.img_detail);
        mLoadingView = (RelativeLayout) findViewById(R.id.common_loading);
        mSettingWallpaperTv = (TextView) findViewById(R.id.tv_set_wallpaper);
        mSettingLockScreenTv = (TextView) findViewById(R.id.tv_set_lockscreen);
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
        if (null != imgsEntity) {
            LogUtil.e(TAG, "title ->" + imgsEntity.getTitle());
        }
        LogUtil.e(TAG, " mParentImagePosition ->" + mParentImagePosition);
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

    }
}
