package com.gpc.meinvxiupro.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.services.AutoSetWallpaperService;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;
import com.gpc.meinvxiupro.utils.WallpaperUtils;

/**
 * Created by pcgu on 16-4-28.
 */
public class CustomAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.BroadcastReceiverAction.AUTO_SET_WALLPAPER)) {
            int currentFilePosition = SharedPreferencesUtils.getAutoSetWallpaperFilePosition(context);
            ImgsEntity item = WallpaperUtils.getAutoSetWallpaperImgItem(currentFilePosition);
            if (null != item && !TextUtils.isEmpty(item.getDownloadUrl())) {
                WallpaperUtils.setWallpaper(context, item.getDownloadUrl());
                ++currentFilePosition;
                if (currentFilePosition >= WallpaperUtils.getDownloadFileLength()) {
                    currentFilePosition = 0;
                }
                SharedPreferencesUtils.setAutoSetWallpaperFilePosition(context, currentFilePosition);
            }
        } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent service = new Intent(context, AutoSetWallpaperService.class);
            context.startService(service);
        }
    }
}
