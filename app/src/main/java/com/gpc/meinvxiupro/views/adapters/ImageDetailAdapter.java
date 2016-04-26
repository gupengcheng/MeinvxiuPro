package com.gpc.meinvxiupro.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.ImageLoaderManager;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.SystemConfigUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by pcgu on 16-4-21.
 */
public class ImageDetailAdapter extends PagerAdapter {
    private ArrayList<ImgsEntity> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mHeightPix;
    private int mWidthPix;
    private int mLoadTag;

    public ImageDetailAdapter(Context context, ArrayList<ImgsEntity> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mHeightPix = metrics.heightPixels;
        mWidthPix = metrics.widthPixels;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.item_image_detail, container, false);
        loadData(view, mData.get(position).getDownloadUrl());
        initData(view);
        initListener(view, mData.get(position));
        setCollectWallpaperViewText(view, mData.get(position).getId());
        container.addView(view);
        view.setTag(position);
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
        RequestCreator creator;
        if (mLoadTag == Constant.CommonData.LOAD_DEFAULT) {
            creator = ImageLoaderManager.getPicassoInstance(mContext)
                    .load(imgUrl);
        } else {
            creator = ImageLoaderManager.getPicassoInstance(mContext)
                    .load(new File(imgUrl));
        }
        creator.error(R.color.rgb_e9e9e9)
                .config(Bitmap.Config.RGB_565)
                .resize(mWidthPix, mHeightPix)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .centerCrop()
                .into((CustomImageView) view.findViewById(R.id.img_detail), new Callback() {
                    @Override
                    public void onSuccess() {
                        view.findViewById(R.id.common_loading).setVisibility(View.GONE);
                        if (mLoadTag == Constant.CommonData.LOAD_DEFAULT) {
                            view.findViewById(R.id.collect_wallpaper).setVisibility(View.VISIBLE);
                        } else {
                            view.findViewById(R.id.collect_wallpaper).setVisibility(View.GONE);
                        }
                        view.findViewById(R.id.set_wallpaper).setVisibility(View.VISIBLE);
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
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    view.findViewById(R.id.set_wallpaper).getLayoutParams();
            layoutParams.setMargins(0, SystemConfigUtils.getInternalDimensionSize() == 0 ?
                    PixelUtil.dp2px(mContext, Constant.STATUS_BAR_HEIGHT)
                    : SystemConfigUtils.getInternalDimensionSize(), 0, 0);
            view.findViewById(R.id.set_wallpaper).setLayoutParams(layoutParams);

            FrameLayout.LayoutParams toolbarLayoutParams = (FrameLayout.LayoutParams)
                    ((Activity) mContext).findViewById(R.id.toolbar).getLayoutParams();
            toolbarLayoutParams.setMargins(0, SystemConfigUtils.getInternalDimensionSize() == 0 ?
                    PixelUtil.dp2px(mContext, Constant.STATUS_BAR_HEIGHT)
                    : SystemConfigUtils.getInternalDimensionSize(), 0, 0);
            ((Activity) mContext).findViewById(R.id.toolbar).setLayoutParams(toolbarLayoutParams);
        }
    }

    private void initListener(final View view, final ImgsEntity imgsEntity) {
        ((CustomImageView) view.findViewById(R.id.img_detail)).setOnTouchListener
                (new CustomImageView.OnTouchListener() {
                    @Override
                    public void setWallpaper() {
                        ToastUtils.showShortSnakeBar(((Activity) mContext).getWindow()
                                        .getDecorView().findViewById(android.R.id.content),
                                mContext.getResources().getString(R.string.start_setting_wallpaper));
                        if (mLoadTag == Constant.CommonData.LOAD_DEFAULT) {
                            WallpaperUtils.setWallpaper(mContext,
                                    ImageUtils.getImageViewBitmap((CustomImageView) view
                                            .findViewById(R.id.img_detail)));
                        } else {
                            WallpaperUtils.setWallpaper(mContext, imgsEntity.getDownloadUrl());
                        }
                    }

                    @Override
                    public void collectWallpaper() {
                        if (mLoadTag == Constant.CommonData.LOAD_LOCAL) {
                            return;
                        }
                        WallpaperUtils.collectWallpaper(imgsEntity, new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(String succeed) {
                                ToastUtils.showShortSnakeBar(((Activity) mContext)
                                        .findViewById(android.R.id.content), succeed);
                                if (succeed.equalsIgnoreCase(mContext.getResources().getString(R.string.dis_collect_succeed))) {
                                    ((TextView) view.findViewById(R.id.collect_wallpaper))
                                            .setText(mContext.getResources().getString(R.string.collect_wallpaper));
                                } else {
                                    ((TextView) view.findViewById(R.id.collect_wallpaper))
                                            .setText(mContext.getResources().getString(R.string.dis_collect_wallpaper));
                                }
                            }
                        });
                    }

                    @Override
                    public void onClick() {
                        if (((Activity) mContext).findViewById(R.id.toolbar)
                                .getVisibility() == View.VISIBLE) {
                            ((Activity) mContext).findViewById(R.id.toolbar).setVisibility(View.GONE);
                        } else {
                            ((Activity) mContext).findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                        }
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
            }

            @Override
            public void onNext(String succeed) {
                ((TextView) view.findViewById(R.id.collect_wallpaper)).setText(succeed);
            }
        });
    }

    public void setLoadUrlTag(int loadUrlTag) {
        mLoadTag = loadUrlTag;
    }
}
