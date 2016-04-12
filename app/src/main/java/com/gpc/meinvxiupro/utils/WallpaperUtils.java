package com.gpc.meinvxiupro.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gpc.meinvxiupro.R;

import java.io.IOException;

/**
 * Created by pcgu on 16-4-12.
 */
public class WallpaperUtils {
    public static void setWallpaper(Context context, String imageFilesPath) {
        WallpaperManager mWallManager = WallpaperManager.getInstance(context);
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilesPath);
            mWallManager.setBitmap(bitmap);
            ToastUtils.showShortToast(context, R.string.set_wallpaper_succeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setWallpaper(Context context, Bitmap bitmap) {
        WallpaperManager mWallManager = WallpaperManager.getInstance(context);
        try {
            mWallManager.setBitmap(bitmap);
            ToastUtils.showShortToast(context, R.string.set_wallpaper_succeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
