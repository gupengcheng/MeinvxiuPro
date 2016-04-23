package com.gpc.meinvxiupro.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImageResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pcgu on 16-3-23.
 */
public class SharedPreferencesUtils {
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(
                getDefaultSharedPreferencesName(context),
                getDefaultSharedPreferencesMode());
    }

    private static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    private static int getDefaultSharedPreferencesMode() {
        return Context.MODE_PRIVATE;
    }

    public static int getApkVersionCode(Context context, String packageName) {
        int versionCode = 0;
        // TODO:if null,return a default value (1.0)
        if (context == null || TextUtils.isEmpty(packageName)) {
            return 0;
        }

        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_CONFIGURATIONS);
            versionCode = pinfo.versionCode;
        } catch (Exception e) {
            LogUtil.e("Utils", "Apk(" + packageName + ") getApkVersionCode error:" + e);
        }
        return versionCode;
    }

    public static List<String> getHomeTabsTitle(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        String homeTabsTitle = sp.getString(Constant.SharedPreferencesKey.HOME_TABS_TITLE, "");
        List<String> tabsList = new ArrayList<>();
        if (!TextUtils.isEmpty(homeTabsTitle)) {
            tabsList = JsonUtils.fromJsonToList(homeTabsTitle);
        } else {
            String[] mLovelyTags = context.getResources().getStringArray(R.array.tag_lovely_array);
            tabsList.addAll(Arrays.asList(mLovelyTags));
        }
        return tabsList;
    }

    public static void setHomeTabsTitle(Context context, List<String> tabsJson) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        if (tabsJson.isEmpty()) {
            String[] mLovelyTags = context.getResources().getStringArray(R.array.tag_lovely_array);
            tabsJson.addAll(Arrays.asList(mLovelyTags));
        }
        String homeTabsTitle = JsonUtils.toJson(tabsJson);
        editor.putString(Constant.SharedPreferencesKey.HOME_TABS_TITLE, homeTabsTitle);
        editor.apply();
    }

    public static boolean getApplicationFirstLoadMore(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        return sp.getBoolean(
                Constant.SharedPreferencesKey.APPLICATION_FIRST_INSTALLED
                        + getApkVersionCode(context, context.getPackageName()), true);
    }

    public static void setApplicationFirstLoadMore(Context context, boolean installed) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        editor.putBoolean(Constant.SharedPreferencesKey.APPLICATION_FIRST_INSTALLED
                + getApkVersionCode(context, context.getPackageName()), installed);
        editor.apply();
    }

    public static ImageResult getCacheImageResult(Context context, String tag) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        String cacheData = sp.getString(tag, "");
        if (!TextUtils.isEmpty(cacheData)) {
            ImageResult imageResult = JsonUtils.fromJson(cacheData, ImageResult.class);
            return imageResult;
        }
        return null;
    }

    public static void setCacheImageResult(Context context, String tag, ImageResult imageResult) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        String cacheJson = JsonUtils.toJson(imageResult);
        if (!TextUtils.isEmpty(cacheJson)) {
            editor.putString(tag, cacheJson);
            editor.apply();
        }
    }

    public static String getTransformer(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        String transformer = sp.getString(Constant.SharedPreferencesKey.TRANSFORMER, "");
        return transformer;
    }

    public static void setTransformer(Context context, String transformer) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        if (!TextUtils.isEmpty(transformer)) {
            editor.putString(Constant.SharedPreferencesKey.TRANSFORMER, transformer);
            editor.apply();
        }
    }

    /**
     * 获取当前选中的页面切换动画的位置，用于设置选中背景
     * 默认值为1，表示position为1的位置
     *
     * @param context
     * @return
     */
    public static int getTransformerPosition(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        int transformerPosition = sp.getInt(Constant.SharedPreferencesKey.TRANSFORMER_POSITION, 1);
        return transformerPosition;
    }

    public static void setTransformerPosition(Context context, int position) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        editor.putInt(Constant.SharedPreferencesKey.TRANSFORMER_POSITION, position);
        editor.apply();
    }

    // get the imei of current device
    public static String getDeviceIMEI(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        return sp.getString(Constant.SharedPreferencesKey.DEVICE_IMEI, "");
    }

    public static void setDeviceIMEI(Context context, String deviceImei) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(
                context).edit();
        editor.putString(Constant.SharedPreferencesKey.DEVICE_IMEI, deviceImei);
        editor.apply();
    }

}
