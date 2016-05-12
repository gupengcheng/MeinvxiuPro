package com.gpc.meinvxiupro.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.managers.ImageLoaderManager;
import com.gpc.meinvxiupro.utils.ContextUtils;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;

public class WelcomeActivity extends AppCompatActivity {
    private static final int GO_HOME_DELAY_MILLIS = 3000;
    private ImageView mWelImg;
    private Context mContext;
    private String mWelcomeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mContext = this;
        initViews();
        loadWelImg();
        goHome();
    }

    private void initViews() {
        mWelImg = (ImageView) findViewById(R.id.img_welcome);
        mWelcomeUrl = SharedPreferencesUtils.getWelcomeUrl(mContext);
    }

    private void loadWelImg() {
        LogUtil.e("tst", "mWelcomeUrl ->" + mWelcomeUrl);
        if (TextUtils.isEmpty(mWelcomeUrl)) {
            ImageLoaderManager.getPicassoInstance(mContext)
                    .load(R.mipmap.wel_default)
                    .into(mWelImg);
        } else {
            ImageLoaderManager.getPicassoInstance(mContext)
                    .load(mWelcomeUrl)
                    .config(Bitmap.Config.RGB_565)
                    .error(R.mipmap.wel_default)
                    .into(mWelImg);
        }
    }

    private void goHome() {
        new Handler().postDelayed(GO_HOME_RUNNABLE, GO_HOME_DELAY_MILLIS);
    }

    Runnable GO_HOME_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            ContextUtils.goActivity(WelcomeActivity.this, HomeActivity.class, true);
        }
    };
}
