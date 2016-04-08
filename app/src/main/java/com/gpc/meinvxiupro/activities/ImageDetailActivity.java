package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.jaeger.library.StatusBarUtil;

/**
 * Created by pcgu on 16-4-8.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String TAG = "ImageDetailActivity";
    private int mParentImagePosition;
    private static final int DEFAULT_PARENT_IMAGE_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.rgb_333333));

    }

    @Override
    protected void findViewByIds() {
        super.findViewByIds();
    }

    @Override
    protected void initViews() {
        super.initViews();
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mParentImagePosition = bundle.getInt(Constant.BundleConstant.IMAGE_POSITION,
                DEFAULT_PARENT_IMAGE_POSITION);
        ImgsEntity imgsEntity = bundle.getParcelable(Constant.BundleConstant.IMAGE_ENTITY);
        if (null != imgsEntity) {
            LogUtil.e(TAG, "title ->" + imgsEntity.getTitle());
        }
        LogUtil.e(TAG, " mParentImagePosition ->" + mParentImagePosition);
    }
}
