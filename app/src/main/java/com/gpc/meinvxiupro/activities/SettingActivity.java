package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.SettingItem;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.adapters.SettingAdapter;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;

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
        getDatas();
        initToolbar();
        initRecyclerViews();
    }

    private void getDatas() {
        mDatas = new ArrayList<>();
        String[] settingKey = getResources().getStringArray(R.array.page_style_key);
        String[] settingContent = getResources().getStringArray(R.array.page_style_content);
        SettingItem title = new SettingItem();
        title.setSettingContent(getResources().getString(R.string.page_style));
        mDatas.add(title);
        for (int i = 0; i < Math.min(settingKey.length, settingContent.length); i++) {
            SettingItem item = new SettingItem();
            item.setSettingKey(settingKey[i]);
            item.setSettingContent(settingContent[i]);
            mDatas.add(item);
        }
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

}
