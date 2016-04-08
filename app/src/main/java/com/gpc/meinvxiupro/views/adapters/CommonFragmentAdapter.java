package com.gpc.meinvxiupro.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        Picasso.with(mContext).load(mItems.get(position).getThumbLargeUrl()).into(holder.mImageView);
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
}
