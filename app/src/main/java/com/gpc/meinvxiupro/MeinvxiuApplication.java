package com.gpc.meinvxiupro;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.MetaDataUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by pcgu on 16-3-1.
 */
public class MeinvxiuApplication extends Application {
    private static MeinvxiuApplication mInstance;
    public static final String TAG = "com.gpc.meinvxiupro";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //初始化push推送服务
        if (shouldInit()) {
            MiPushClient.registerPush(this, MetaDataUtils.getStringMetaData(
                            Constant.MetaDataName.XIAOMI_PUSH_ID),
                    MetaDataUtils.getStringMetaData(Constant.MetaDataName.XIAOMI_PUSH_KEY));
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static MeinvxiuApplication getInstance() {
        return mInstance;
    }

}
