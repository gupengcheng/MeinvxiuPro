package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.AboutUsItem;
import com.gpc.meinvxiupro.views.adapters.AboutUsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-4-20.
 */
public class AboutUsActivity extends BaseActivity {
    private Toolbar mAboutUsToolbar;
    private RecyclerView mAboutUsRecyclerView;
    private AboutUsAdapter mAdapter;
    private List<AboutUsItem> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mAboutUsToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mAboutUsRecyclerView = (RecyclerView) findViewById(R.id.about_us_recyclerview);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
        initToolbar();
        initRecyclerView();
    }

    private void getData() {
        mDatas = new ArrayList<>();
        String[] mTitles = getResources().getStringArray(R.array.about_us_question_title);
        String[] mDesc = getResources().getStringArray(R.array.about_us_question_description);
        for (int i = 0; i < Math.min(mTitles.length, mDesc.length); i++) {
            AboutUsItem item = new AboutUsItem();
            item.setTitle(mTitles[i]);
            item.setDescription(mDesc[i]);
            mDatas.add(item);
        }
    }

    private void initToolbar() {
        setSupportActionBar(mAboutUsToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mAboutUsToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AboutUsActivity.this.finish();
                    }
                }
        );
        mAboutUsToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mAboutUsToolbar.findViewById(R.id.tool_title))
                .setText(getResources().getString(R.string.about_us));
        mAboutUsToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private void initRecyclerView() {
        mAdapter = new AboutUsAdapter(mContext, mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAboutUsRecyclerView.setLayoutManager(layoutManager);
        mAboutUsRecyclerView.setAdapter(mAdapter);
    }
}
