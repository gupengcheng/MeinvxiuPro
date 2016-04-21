package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.ContextUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.WallpaperUtils;
import com.gpc.meinvxiupro.views.adapters.CommonFragmentAdapter;
import com.gpc.meinvxiupro.views.interfaces.OnItemClickListener;
import com.gpc.meinvxiupro.views.widgets.RecyclerViewItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by pcgu on 16-4-20.
 */
public class MyCollectActivity extends BaseActivity {
    private static final String TAG = "MyCollectActivity";
    private Toolbar mCollectToolbar;
    private RecyclerView mCollectRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CommonFragmentAdapter mAdapter;
    private RecyclerViewItemOffsetDecoration mItemOffsetDecoration;
    private List<ImgsEntity> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mCollectToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mCollectRecyclerView = (RecyclerView) findViewById(R.id.my_collect_recyclerview);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initToolbar();
        initRecyclerView();
        initListener();
        loadData();
    }

    private void initToolbar() {
        setSupportActionBar(mCollectToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mCollectToolbar.findViewById(R.id.tool_left_view).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyCollectActivity.this.finish();
                    }
                }
        );
        mCollectToolbar.findViewById(R.id.tool_back).setVisibility(View.VISIBLE);
        ((TextView) mCollectToolbar.findViewById(R.id.tool_title))
                .setText(getResources().getString(R.string.my_collect));
        mCollectToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private void initRecyclerView() {
        mAdapter = new CommonFragmentAdapter(mItems, mContext);
        mAdapter.setNotNeedTabLayoutFragmentTag();
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mCollectRecyclerView.setLayoutManager(mGridLayoutManager);
        mItemOffsetDecoration = new RecyclerViewItemOffsetDecoration(mContext, R.dimen.item_offset);
        mCollectRecyclerView.addItemDecoration(mItemOffsetDecoration);
        mCollectRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                LogUtil.e(TAG, "position ->" + position);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.BundleConstant.IMAGE_POSITION, position);
                bundle.putParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS, (ArrayList<ImgsEntity>) mItems);
                ContextUtils.goActivity(mContext, ImageDetailActivity.class, bundle);
            }
        });
    }

    private void loadData() {
        findViewById(R.id.my_collect_loading).setVisibility(View.VISIBLE);
        WallpaperUtils.getCollectWallpaper(new Subscriber<List<ImgsEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "loadData onError ->" + e.toString());
                findViewById(R.id.my_collect_loading).setVisibility(View.GONE);
            }

            @Override
            public void onNext(List<ImgsEntity> imgsEntities) {
                LogUtil.e(TAG, "loadData onNext ->" + imgsEntities.size());
                if (imgsEntities.isEmpty()) {
                    findViewById(R.id.my_collect_no_data).setVisibility(View.VISIBLE);
                } else {
                    mItems.addAll(imgsEntities);
                    mAdapter.notifyDataSetChanged();
                }
                findViewById(R.id.my_collect_loading).setVisibility(View.GONE);
            }
        });
    }

}
