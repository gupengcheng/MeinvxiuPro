package com.gpc.meinvxiupro.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.utils.ContextUtils;

public class WelcomeActivity extends AppCompatActivity {
    private static final int GO_HOME_DELAY_MILLIS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        goHome();
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
