package com.gpc.meinvxiupro.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gpc.meinvxiupro.R;

import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-4-12.
 */
public class WallpaperUtils {
    public static void setWallpaper(final Context context, final String imageFilesPath) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                WallpaperManager mWallManager = WallpaperManager.getInstance(context);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(imageFilesPath);
                    mWallManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(context.getResources().getString(R.string.set_wallpaper_succeed));
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortToast(context, R.string.set_wallpaper_failure);
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showShortToast(context, s);
                    }
                });
    }

    public static void setWallpaper(final Context context, final Bitmap bitmap) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                WallpaperManager mWallManager = WallpaperManager.getInstance(context);
                try {
                    mWallManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(context.getResources().getString(R.string.set_wallpaper_succeed));
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortToast(context, R.string.set_wallpaper_failure);
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showShortToast(context, s);
                    }
                });
    }

}
