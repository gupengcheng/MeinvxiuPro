package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.views.adapters.ImageDetailAdapter;

import java.util.ArrayList;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    private ViewPager mDetailViewPager;
    private ImageDetailAdapter mAdapter;

    private int mParentImagePosition;
    private ArrayList<ImgsEntity> mItems;
    private static final int DEFAULT_PARENT_IMAGE_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
        mDetailViewPager = (ViewPager) findViewById(R.id.image_detail_viewpager);
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
        initViewPager();
    }

    private void getData() {
        Bundle bundle = getIntent().getBundleExtra(Constant.ContextConstant.BUNDLE_NAME);
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        mItems = bundle.getParcelableArrayList(Constant.BundleConstant.IMAGE_DATAS);
        LogUtil.e(TAG, "mItems.size ->" + mItems.size() + " title ->" + mItems.get(0).getTitle());
    }

    private void initViewPager() {
        mAdapter = new ImageDetailAdapter(mContext, mItems);
        mDetailViewPager.setAdapter(mAdapter);
        mDetailViewPager.setCurrentItem(mParentImagePosition);
        mDetailViewPager.setPageTransformer(true, new CubeOutTransformer());
        mDetailViewPager.setOffscreenPageLimit(1);
    }

}
