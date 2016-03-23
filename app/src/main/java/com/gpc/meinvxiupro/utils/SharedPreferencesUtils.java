package com.gpc.meinvxiupro.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.gpc.meinvxiupro.R;

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
}
