package com.gpc.meinvxiupro.managers;

import android.content.Context;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by pcgu on 16-4-18.
 */
public class ImageLoaderManager {
    private static Picasso mPicasso;
    private static final long MAX_DISK_CACHE_SIZE = 100 * 1024 * 1024;
    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    private static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    // Use 1/8th of the available memory for this memory cache.
    private static final int MAX_MEMORY_CACHE_SIZE = maxMemory / 8;

    public static Picasso getPicassoInstance(Context context) {
        if (mPicasso == null) {
            Picasso.Builder builder = new Picasso.Builder(context);
            builder.memoryCache(new LruCache(MAX_MEMORY_CACHE_SIZE));
            builder.downloader(new OkHttpDownloader(context, MAX_DISK_CACHE_SIZE));
            mPicasso = builder.build();

            Picasso.setSingletonInstance(mPicasso);
        }
        return mPicasso;
    }
}
