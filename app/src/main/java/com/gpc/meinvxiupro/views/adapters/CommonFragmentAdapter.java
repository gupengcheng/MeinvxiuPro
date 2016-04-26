package com.gpc.meinvxiupro.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.ImageLoaderManager;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-3-25.
 */
public class CommonFragmentAdapter extends RecyclerView.Adapter<CommonFragmentAdapter.BaseViewHolder> {
    private List<ImgsEntity> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int mFragmentTag = TAG_DEFAULT;
    private int mLoadUrlTag = Constant.CommonData.LOAD_DEFAULT;
    private static final int IMAGE_FRAGMENT_TOTAL_NUM = 3;
    private static final int TAG_DEFAULT = 0;
    private static final int TAG_NOT_NEED_TOP_LAYOUT = 1;

    private LayoutInflater mInflater;

    public CommonFragmentAdapter(List<ImgsEntity> items, Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mItems = items;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_common, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        RequestCreator creator;
        if (mLoadUrlTag == Constant.CommonData.LOAD_DEFAULT) {
            creator = ImageLoaderManager.getPicassoInstance(mContext)
                    .load(mItems.get(position).getThumbLargeUrl());
        } else {
            creator = ImageLoaderManager.getPicassoInstance(mContext)
                    .load(new File(mItems.get(position).getThumbLargeUrl()));
        }
        creator
                .placeholder(R.color.rgb_e9e9e9)
                .error(R.color.rgb_e9e9e9)
                .config(Bitmap.Config.RGB_565)
                .into(holder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (position == 0) {
                            getPaletteColor(holder.mImageView);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(position);
            }
        });
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public BaseViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.common_image);
        }

    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private void getPaletteColor(ImageView imageView) {
        Palette.PaletteAsyncListener paletteAsyncListener = new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                StatusBarUtil.setColor(((Activity) mContext), mutedColor);
                setToolbarBackground(mutedColor);
//                setBottomTabLayoutTextColor(mutedColor);
                if (mFragmentTag == TAG_DEFAULT) {
                    setTopTabLayoutTextColor(mutedColor);
                }
            }
        };
        Palette.from(ImageUtils.getImageViewBitmap(imageView)).generate(paletteAsyncListener);
    }

    private void setTopTabLayoutTextColor(int mutedColor) {
        ViewPager viewPager = (ViewPager) ((Activity) mContext).findViewById(R.id.home_viewpager);
        HomePagerAdapter adapter = (HomePagerAdapter) viewPager.getAdapter();
        //最外层viewpager的当前item
        android.support.v4.app.Fragment fragment = adapter.getItem(viewPager.getCurrentItem());
        if (fragment.getView() != null && viewPager.getCurrentItem() < IMAGE_FRAGMENT_TOTAL_NUM) {
            TabLayout topTabLayout = (TabLayout) fragment.getView().findViewById(R.id.common_tag_tab_layout);
            topTabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
        }
        //最外层viewpager的当前item之前的item
        if (viewPager.getCurrentItem() - 1 >= 0 && viewPager.getCurrentItem() < IMAGE_FRAGMENT_TOTAL_NUM) {
            android.support.v4.app.Fragment lastFragment = adapter.getItem(viewPager.getCurrentItem() - 1);
            if (lastFragment.getView() != null) {
                TabLayout lastTabLayout = (TabLayout) lastFragment.getView().findViewById(R.id.common_tag_tab_layout);
                lastTabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
            }
        }
        //最外层viewpager的当前item之后的item
        if (viewPager.getCurrentItem() < IMAGE_FRAGMENT_TOTAL_NUM - 1) {
            android.support.v4.app.Fragment nextFragment = adapter.getItem(viewPager.getCurrentItem() + 1);
            if (nextFragment.getView() != null) {
                TabLayout nextTabLayout = (TabLayout) nextFragment.getView().findViewById(R.id.common_tag_tab_layout);
                nextTabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
            }
        }
    }

    private void setToolbarBackground(int mutedColor) {
        ((Activity) mContext).findViewById(R.id.home_toolbar).setBackgroundColor(mutedColor);
    }

    private void setBottomTabLayoutTextColor(int mutedColor) {
        TabLayout tabLayout = (TabLayout) ((Activity) mContext).findViewById(R.id.home_tab_layout);
        tabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
    }

    public void setNotNeedTabLayoutFragmentTag() {
        mFragmentTag = TAG_NOT_NEED_TOP_LAYOUT;
    }

    public void setLoadUrlTag() {
        mLoadUrlTag = Constant.CommonData.LOAD_LOCAL;
    }

    public int getLoadUrlTag() {
        return mLoadUrlTag;
    }

}
