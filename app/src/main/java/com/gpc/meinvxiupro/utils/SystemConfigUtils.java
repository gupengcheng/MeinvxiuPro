package com.gpc.meinvxiupro.utils;

import android.content.res.Resources;

import com.gpc.meinvxiupro.MeinvxiuApplication;

/**
 * Created by pcgu on 16-4-21.
 */
public class SystemConfigUtils {
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    public static int getInternalDimensionSize() {
        int result = 0;
        int resourceId = MeinvxiuApplication.getInstance().getResources().getIdentifier(STATUS_BAR_HEIGHT_RES_NAME, "dimen", "android");
        if (resourceId > 0) {
            result = MeinvxiuApplication.getInstance().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
