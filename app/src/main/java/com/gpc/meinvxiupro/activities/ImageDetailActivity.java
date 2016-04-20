package com.gpc.meinvxiupro.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.DataRequestManager;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.interfaces.DoubleClickListener;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import rx.Subscriber;

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
    private ImgsEntity imgsEntity;
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
        loadData(imgsEntity.getDownloadUrl());
        initData();
        initListener();
        setCollectWallpaperViewText(imgsEntity.getId());
    }

    private void getData() {
        Bundle bundle = getIntent().getBundleExtra(Constant.ContextConstant.BUNDLE_NAME);
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        imgsEntity = bundle.getParcelable(Constant.BundleConstant.IMAGE_ENTITY);
    }

    private void loadData(String imgUrl) {
        mLoadingView.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(imgUrl).into(mDetailImg, new Callback() {
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

    private void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDetailImg.setAddStatusBarDistanceYSetWallpaper();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mSetWallpaperView.getLayoutParams();
            layoutParams.setMargins(0, PixelUtil.dp2px(mContext, Constant.STATUS_BAR_HEIGHT), 0, 0);
            mSetWallpaperView.setLayoutParams(layoutParams);
        }
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
                WallpaperUtils.collectWallpaper(imgsEntity, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "collect wallpaper error ->" + e.toString());
                    }

                    @Override
                    public void onNext(String succeed) {
                        LogUtil.e(TAG, "collect wallpaper onNext ->" + succeed + "  id ->" + imgsEntity.getId());
                        ToastUtils.showShortSnakeBar(findViewById(android.R.id.content), succeed);
                        if (succeed.equalsIgnoreCase(getResources().getString(R.string.dis_collect_succeed))) {
                            mCollectWallpaperView.setText(getResources().getString(R.string.collect_wallpaper));
                        } else {
                            mCollectWallpaperView.setText(getResources().getString(R.string.dis_collect_wallpaper));
                        }
                    }
                });
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

    private void setCollectWallpaperViewText(String id) {
        WallpaperUtils.setCollectWallpaperText(id, new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "set Collect Wallpaper View Text error ->" + e.toString());
            }

            @Override
            public void onNext(String succeed) {
                LogUtil.e(TAG, "set Collect Wallpaper View onNext ->" + succeed);
                mCollectWallpaperView.setText(succeed);
            }
        });
    }
}
