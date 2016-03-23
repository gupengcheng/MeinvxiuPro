package com.gpc.meinvxiupro.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gpc.meinvxiupro.utils.Constant;

/**
 * Created by pcgu on 16-3-23.
 */
public class BaseActivity extends AppCompatActivity {
    private DataReceiver mDataReceiver;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerDataReceiver();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mContext = this;
        findViewByIds();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterDataReceiver();
    }

    private void registerDataReceiver() {
        mDataReceiver = new DataReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BroadcastReceiverAction.EXIT_TO_DESKTOP);
        registerReceiver(mDataReceiver, intentFilter);
    }

    private void unRegisterDataReceiver() {
        unregisterReceiver(mDataReceiver);
    }

    class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.BroadcastReceiverAction.EXIT_TO_DESKTOP)) {
                ((Activity) context).finish();
            }
        }
    }

    protected void findViewByIds() {

    }

    protected void initViews() {

    }
}
