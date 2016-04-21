package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;

/**
 * Created by pcgu on 16-4-20.
 */
public class AboutUsActivity extends BaseActivity {
    private Toolbar mAboutUsToolbar;
    private RecyclerView mAboutUsRecyclerView;

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
        initToolbar();
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
}
