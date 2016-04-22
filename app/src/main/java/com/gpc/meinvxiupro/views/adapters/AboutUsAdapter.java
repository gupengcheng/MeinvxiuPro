package com.gpc.meinvxiupro.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.AboutUsItem;

import java.util.List;

/**
 * Created by pcgu on 16-4-22.
 */
public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.BaseViewHolder> {
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDesc;

        public BaseViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
            mDesc = (TextView) view.findViewById(R.id.desc);
        }

    }

    private Context mContext;
    private List<AboutUsItem> mDatas;
    private LayoutInflater mInflater;

    public AboutUsAdapter(Context context, List<AboutUsItem> datas) {
        this.mDatas = datas;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        AboutUsItem item = mDatas.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mDesc.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_about_us, parent, false);
        return new BaseViewHolder(view);
    }
}
