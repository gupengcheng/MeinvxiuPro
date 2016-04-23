package com.gpc.meinvxiupro.views.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.SettingItem;
import com.gpc.meinvxiupro.utils.PixelUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;

import java.util.List;

/**
 * Created by gupengcheng on 16/4/22.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.BaseViewHolder> {
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private TextView mTransformer;
        private TextView mTransformerTitleLine;
        private TextView mTransformerItemLine;

        public BaseViewHolder(View view) {
            super(view);
            mTransformer = (TextView) view.findViewById(R.id.transformer);
            mTransformerTitleLine = (TextView) view.findViewById(R.id.transformer_title_line);
            mTransformerItemLine = (TextView) view.findViewById(R.id.transformer_item_line);
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
        holder.mTransformer.setText(item.getSettingContent());
        if (position == 0) {
            holder.mTransformerTitleLine.setVisibility(View.VISIBLE);
            holder.mTransformerItemLine.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mTransformer.getLayoutParams();
            layoutParams.setMargins(PixelUtil.dp2px(mContext, 16), 0, 0, 0);
            holder.mTransformer.setLayoutParams(layoutParams);
            holder.mTransformer.setBackgroundResource(R.color.rgb_f8f8f8);
            holder.mTransformer.setTextSize(18);
            holder.mTransformer.setTextColor(ContextCompat.getColor(mContext, R.color.rgb_333333));
        } else {
            holder.mTransformerTitleLine.setVisibility(View.GONE);
            holder.mTransformerItemLine.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mTransformer.getLayoutParams();
            layoutParams.setMargins(PixelUtil.dp2px(mContext, 32), 0, 0, 0);
            holder.mTransformer.setLayoutParams(layoutParams);
            holder.mTransformer.setBackgroundResource(R.drawable.bg_me_item);
            holder.mTransformer.setTextSize(16);
            holder.mTransformer.setTextColor(ContextCompat.getColor(mContext, R.color.rgb_969696));
        }
        if (position == SharedPreferencesUtils.getTransformerPosition(mContext)) {
            holder.mTransformer.setSelected(true);
        } else {
            holder.mTransformer.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.setTransformerPosition(mContext, position);
                SharedPreferencesUtils.setTransformer(mContext, item.getSettingKey());
                notifyDataSetChanged();
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

}
