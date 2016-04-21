package com.gpc.meinvxiupro.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by pcgu on 16-4-21.
 */
public class ImageDetailAdapter extends PagerAdapter {
    private static final String TAG = "ImageDetailAdapter";
    private ArrayList<ImgsEntity> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public ImageDetailAdapter(Context context, ArrayList<ImgsEntity> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.image_detail_item, container, false);
        loadData(view, mData.get(position).getDownloadUrl());
        initData(view);
        initListener(view, mData.get(position));
        setCollectWallpaperViewText(view, mData.get(position).getId());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void loadData(final View view, String imgUrl) {
        view.findViewById(R.id.common_loading).setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(imgUrl).into((CustomImageView) view.findViewById(R.id.img_detail), new Callback() {
            @Override
            public void onSuccess() {
                view.findViewById(R.id.common_loading).setVisibility(View.GONE);
                getPaletteColor(view, (CustomImageView) view.findViewById(R.id.img_detail));
            }

            @Override
            public void onError() {
                view.findViewById(R.id.common_loading).setVisibility(View.GONE);
            }
        });
    }

    private void initData(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ((CustomImageView) view.findViewById(R.id.img_detail)).setAddStatusBarDistanceYSetWallpaper();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.findViewById(R.id.set_wallpaper).getLayoutParams();
            layoutParams.setMargins(0, PixelUtil.dp2px(mContext, Constant.STATUS_BAR_HEIGHT), 0, 0);
            view.findViewById(R.id.set_wallpaper).setLayoutParams(layoutParams);
        }
    }

    private void initListener(final View view, final ImgsEntity imgsEntity) {
        ((CustomImageView) view.findViewById(R.id.img_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((CustomImageView) view.findViewById(R.id.img_detail)).setOnTouchDistanceListener(new CustomImageView.OnTouchDistanceListener() {
            @Override
            public void setWallpaper() {
                WallpaperUtils.setWallpaper(mContext, ImageUtils.getImageViewBitmap((CustomImageView) view.findViewById(R.id.img_detail)));
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
                        ToastUtils.showShortSnakeBar(((Activity) mContext).findViewById(android.R.id.content), succeed);
                        if (succeed.equalsIgnoreCase(mContext.getResources().getString(R.string.dis_collect_succeed))) {
                            ((TextView) view.findViewById(R.id.collect_wallpaper)).setText(mContext.getResources().getString(R.string.collect_wallpaper));
                        } else {
                            ((TextView) view.findViewById(R.id.collect_wallpaper)).setText(mContext.getResources().getString(R.string.dis_collect_wallpaper));
                        }
                    }
                });
            }

        });
    }

    private void getPaletteColor(final View rootView, ImageView imageView) {
        Palette.PaletteAsyncListener paletteAsyncListener = new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                rootView.setBackgroundColor(mutedColor);
            }
        };
        Palette.from(ImageUtils.getImageViewBitmap(imageView)).generate(paletteAsyncListener);
    }

    private void setCollectWallpaperViewText(final View view, String id) {
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
                ((TextView) view.findViewById(R.id.collect_wallpaper)).setText(succeed);
            }
        });
    }
}
