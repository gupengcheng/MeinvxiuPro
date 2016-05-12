package com.gpc.meinvxiupro.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.gpc.meinvxiupro.MeinvxiuApplication;

/**
 * Created by pcgu on 16-5-12.
 */
public class MetaDataUtils {
    public static String getStringMetaData(String name) {
        int valueId = 0;
        try {
            ApplicationInfo appInfo = MeinvxiuApplication.getInstance().getApplicationContext().getPackageManager()
                    .getApplicationInfo(MeinvxiuApplication.getInstance().getApplicationContext().getPackageName(),
                            PackageManager.GET_META_DATA);
            valueId = appInfo.metaData.getInt(name);
            if (valueId != 0) {
                LogUtil.e("Tag", "name :" + name + " app key : " + MeinvxiuApplication.getInstance().getApplicationContext().getResources().getString(valueId));
                return MeinvxiuApplication.getInstance().getApplicationContext().getResources().getString(valueId);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
