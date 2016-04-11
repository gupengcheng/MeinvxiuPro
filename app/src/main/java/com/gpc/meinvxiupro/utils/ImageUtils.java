package com.gpc.meinvxiupro.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by pcgu on 16-4-11.
 */
public class ImageUtils {
    public static Bitmap getImageViewBitmap(ImageView mImageView) {
        Bitmap bm = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        return bm;
    }
}
