package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.SettingItem;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.views.adapters.SettingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-4-22.
 */
public class SettingActivity extends BaseActivity {
    private Toolbar mSettingToolbar;
    private RecyclerView mSettingRecyclerView;
    private List<SettingItem> mDatas;
    private SettingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mSettingToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mSettingRecyclerView = (RecyclerView) findViewById(R.id.setting_recycler_view);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
        initToolbar();
        initRecyclerViews();
    }

    private void getData() {
        mDatas = new ArrayList<>();
        setListData(getResources().getString(R.string.page_style),
                getResources().getStringArray(R.array.page_style_key),
                getResources().getStringArray(R.array.page_style_content),
                Constant.SettingType.TRANSFORM_ITEM);

        setListData(getResources().getString(R.string.auto_set_download_wallpaper),
                getResources().getStringArray(R.array.auto_set_download_wallpaper_key),
                getResources().getStringArray(R.array.auto_set_download_wallpaper_content),
                Constant.SettingType.AUTO_SET_DOWNLOAD_WALLPAPER_ITEM);
    }

    private void initToolbar() {
        setSupportActionBar(mSettingToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSettingToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SettingActivity.this.finish();
                    }
                }
        );
        mSettingToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mSettingToolbar.findViewById(R.id.tool_title))
                .setText(getResources().getString(R.string.setting));
        mSettingToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private void initRecyclerViews() {
        mAdapter = new SettingAdapter(mContext, mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSettingRecyclerView.setLayoutManager(layoutManager);
        mSettingRecyclerView.setAdapter(mAdapter);
    }

    private void setListData(String title, String[] key, String[] content, int itemType) {
        SettingItem autoSetDownloadWallpaperTitle = new SettingItem();
        autoSetDownloadWallpaperTitle.setSettingContent(title);
        autoSetDownloadWallpaperTitle.setType(Constant.SettingType.TITLE);
        mDatas.add(autoSetDownloadWallpaperTitle);
        for (int i = 0; i < Math.min(key.length, content.length); i++) {
            SettingItem item = new SettingItem();
            item.setSettingKey(key[i]);
            item.setSettingContent(content[i]);
            item.setType(itemType);
            mDatas.add(item);
        }
    }

}
