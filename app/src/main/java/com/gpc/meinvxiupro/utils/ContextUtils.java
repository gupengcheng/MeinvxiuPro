package com.gpc.meinvxiupro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by pcgu on 16-3-10.
 */
public class ContextUtils {
    public static void goActivity(Context currentActivity, Class<?> targetActivity) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
    }

    public static void goActivity(Context currentActivity, Class<?> targetActivity, boolean closeCurrentActivity) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
        if (closeCurrentActivity) {
            ((Activity) currentActivity).finish();
        }
    }

    public static void goActivity(Context currentActivity, Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        intent.putExtra(Constant.ContextConstant.BUNDLE_NAME, bundle);
        currentActivity.startActivity(intent);
    }

    public static void goActivity(Context currentActivity, Class<?> targetActivity, Bundle bundle, boolean closeCurrentActivity) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        intent.putExtra(Constant.ContextConstant.BUNDLE_NAME, bundle);
        currentActivity.startActivity(intent);
        if (closeCurrentActivity) {
            ((Activity) currentActivity).finish();
        }
    }

    public static void launchMarketApp(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}