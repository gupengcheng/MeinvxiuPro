package com.gpc.meinvxiupro.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.MeinvxiuApplication;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.SettingItem;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.utils.ToastUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;

import java.util.List;

/**
 * Created by gupengcheng on 16/4/22.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.BaseViewHolder> {
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private TextView mSettingTitle;
        private TextView mSettingItem;
        private LinearLayout mSettingItemLay;
        private LinearLayout mSettingTitleLay;

        public BaseViewHolder(View view) {
            super(view);
            mSettingTitle = (TextView) view.findViewById(R.id.setting_title);
            mSettingItem = (TextView) view.findViewById(R.id.setting_item);
            mSettingItemLay = (LinearLayout) view.findViewById(R.id.setting_item_lay);
            mSettingTitleLay = (LinearLayout) view.findViewById(R.id.setting_title_lay);
        }
    }

    private Context mContext;
    private List<SettingItem> mDatas;
    private LayoutInflater mInflater;

    public SettingAdapter(Context context, List<SettingItem> datas) {
        this.mDatas = datas;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        final SettingItem item = mDatas.get(position);
        setSettingItemVisible(holder, item.getType());
        setSettingItemText(holder, item.getSettingContent());
        setSettingItemSelected(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickResponse(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_setting, parent, false);
        return new BaseViewHolder(view);
    }

    private void setSettingItemVisible(BaseViewHolder holder, int type) {
        holder.mSettingTitleLay.setVisibility(type == Constant.SettingType.TITLE ? View.VISIBLE : View.GONE);
        holder.mSettingItemLay.setVisibility(type == Constant.SettingType.TITLE ? View.GONE : View.VISIBLE);
    }

    private void setSettingItemText(BaseViewHolder holder, String text) {
        holder.mSettingTitle.setText(text);
        holder.mSettingItem.setText(text);
    }

    private void setSettingItemSelected(BaseViewHolder holder, int position) {
        holder.mSettingItem.setSelected((position == SharedPreferencesUtils.getTransformerPosition(mContext)
                || position == SharedPreferencesUtils.getAutoSetWallpaperPosition(mContext)) ? true : false);
    }

    private void itemClickResponse(SettingItem item, int position) {
        if (item.getType() == Constant.SettingType.TITLE) {
            return;
        }
        if (item.getType() == Constant.SettingType.TRANSFORM_ITEM) {
            WallpaperUtils.settingTransform(mContext, position, item, this);
        } else {
            WallpaperUtils.autoSetWallpaper(mContext, position, this);
        }
    }
}
