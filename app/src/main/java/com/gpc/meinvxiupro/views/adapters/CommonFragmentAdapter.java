package com.gpc.meinvxiupro.views.adapters;

import android.app.Activity;
import android.content.Context;
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
import com.gpc.meinvxiupro.utils.ImageUtils;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-3-25.
 */
public class CommonFragmentAdapter extends RecyclerView.Adapter<CommonFragmentAdapter.BaseViewHolder> {
    private static final String TAG = "CommonFragmentAdapter";
    private List<ImgsEntity> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public CommonFragmentAdapter(List<ImgsEntity> items, Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        ImageLoaderManager.getPicassoInstance(mContext).load(mItems.get(position)
                .getThumbLargeUrl()).into(holder.mImageView, new Callback() {
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
                mOnItemClickListener.onItemClickListener(mItems.get(position), position);
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
                setTopTabLayoutTextColor(mutedColor);
            }
        };
        Palette.from(ImageUtils.getImageViewBitmap(imageView)).generate(paletteAsyncListener);
    }

    private void setTopTabLayoutTextColor(int mutedColor) {
        ViewPager viewPager = (ViewPager) ((Activity) mContext).findViewById(R.id.home_viewpager);
        HomePagerAdapter adapter = (HomePagerAdapter) viewPager.getAdapter();
        //最外层viewpager的当前item
        android.support.v4.app.Fragment fragment = adapter.getItem(viewPager.getCurrentItem());
        if (fragment.getView() != null) {
            TabLayout topTabLayout = (TabLayout) fragment.getView().findViewById(R.id.common_tag_tab_layout);
            topTabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
        }
        //最外层viewpager的当前item之前的item
        if (viewPager.getCurrentItem() - 1 >= 0) {
            android.support.v4.app.Fragment lastFragment = adapter.getItem(viewPager.getCurrentItem() - 1);
            if (lastFragment.getView() != null) {
                TabLayout lastTabLayout = (TabLayout) lastFragment.getView().findViewById(R.id.common_tag_tab_layout);
                lastTabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.rgb_333333), mutedColor);
            }
        }
        //最外层viewpager的当前item之后的item
        if (viewPager.getCurrentItem() + 1 < adapter.getCount()) {
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
}
