package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.SettingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-4-22.
 */
public class SettingActivity extends BaseActivity {
    private RecyclerView mSettingRecyclerView;
    private List<SettingItem> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mSettingRecyclerView = (RecyclerView) findViewById(R.id.setting_recycler_view);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getDatas();
    }

    private void getDatas() {
        mDatas = new ArrayList<>();
        String[] settingKey = getResources().getStringArray(R.array.page_style_key);
        String[] settingContent = getResources().getStringArray(R.array.page_style_content);
        for (int i = 0; i < Math.min(settingKey.length, settingContent.length); i++) {

        }
    }
}
