package com.gpc.meinvxiupro;

import android.app.Application;

/**
 * Created by pcgu on 16-3-1.
 */
public class MeinvxiuApplication extends Application {
    private static MeinvxiuApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MeinvxiuApplication getInstance() {
        return mInstance;
    }
}
