package com.gpc.meinvxiupro.utils;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.gpc.meinvxiupro.MeinvxiuApplication;
import com.gpc.meinvxiupro.R;
import com.gpc.meinvxiupro.models.ImgsEntity;
import com.gpc.meinvxiupro.models.SettingItem;
import com.gpc.meinvxiupro.provider.MnxDbProvider;
import com.gpc.meinvxiupro.views.adapters.SettingAdapter;
import com.gpc.meinvxiupro.views.widgets.CustomImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showShortSnakeBar(((Activity) context).getWindow()
                                .getDecorView().findViewById(android.R.id.content), s);
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
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showShortSnakeBar(((Activity) context).getWindow()
                                .getDecorView().findViewById(android.R.id.content), s);
                    }
                });
    }


    public static void collectWallpaper(final ImgsEntity imgsEntity, Subscriber<String> callback) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (MnxDbProvider.getInstance().isExistsInCollectTable(imgsEntity.getId())) {
                    MnxDbProvider.getInstance().deleteImgEntity(imgsEntity.getId());
                    subscriber.onNext(MeinvxiuApplication.getInstance().getResources()
                            .getString(R.string.dis_collect_succeed));
                } else {
                    MnxDbProvider.getInstance().insertImgEntity(imgsEntity);
                    subscriber.onNext(MeinvxiuApplication.getInstance().getResources()
                            .getString(R.string.collect_wallpaper_succeed));
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public static void setCollectWallpaperText(final String id, Subscriber<String> callback) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (MnxDbProvider.getInstance().isExistsInCollectTable(id)) {
                    subscriber.onNext(MeinvxiuApplication.getInstance().getResources()
                            .getString(R.string.dis_collect_wallpaper));
                } else {
                    subscriber.onNext(MeinvxiuApplication.getInstance().getResources()
                            .getString(R.string.collect_wallpaper));
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public static void getCollectWallpaper(Subscriber<List<ImgsEntity>> callback) {
        Observable.create(new Observable.OnSubscribe<List<ImgsEntity>>() {
            @Override
            public void call(Subscriber<? super List<ImgsEntity>> subscriber) {
                subscriber.onNext(MnxDbProvider.getInstance().getCollectDatas());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public static void getDownloadWallpaper(Subscriber<List<ImgsEntity>> callback) {
        Observable.create(new Observable.OnSubscribe<List<ImgsEntity>>() {
            @Override
            public void call(Subscriber<? super List<ImgsEntity>> subscriber) {
                File file = new File(MeinvxiuApplication.getInstance().getApplicationContext()
                        .getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
                if (file.isDirectory()) {
                    //获取该文件夹下的文件集合
                    File[] files = file.listFiles();
                    List<ImgsEntity> data = new ArrayList<ImgsEntity>();
                    for (int i = 0; i < files.length; i++) {
                        ImgsEntity imgsEntity = new ImgsEntity();
                        imgsEntity.setDesc(files[i].getName());
                        imgsEntity.setTitle(files[i].getName());
                        imgsEntity.setDownloadUrl(files[i].getAbsolutePath());
                        imgsEntity.setThumbLargeUrl(files[i].getAbsolutePath());
                        data.add(imgsEntity);
                    }
                    if (data.isEmpty()) {
                        subscriber.onNext(null);
                    } else {
                        subscriber.onNext(data);
                    }
                } else {
                    subscriber.onNext(null);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public static void downloadWallpaper(final String fileName, final CustomImageView imageView, Subscriber<Boolean> callback) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(saveBitmap2file(ImageUtils.getImageViewBitmap(imageView), fileName));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public static void settingAutoSetWallpaper(final Context context, final int position, final SettingAdapter adapter) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int lastPosition = SharedPreferencesUtils.getAutoSetWallpaperPosition(context);
                SharedPreferencesUtils.setAutoSetWallpaperPosition(context, position);
                subscriber.onNext(lastPosition);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer lastPosition) {
                        adapter.notifyItemChanged(lastPosition);
                        adapter.notifyItemChanged(position);
                        ToastUtils.showShortSnakeBar(((Activity) context).findViewById(android.R.id.content),
                                context.getResources().getString(R.string.setting_auto_set_wallpaper_succeed));
                    }
                });
    }

    public static void settingTransform(final Context context, final int position,
                                        final SettingItem item, final SettingAdapter adapter) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int lastPosition = SharedPreferencesUtils.getTransformerPosition(context);
                SharedPreferencesUtils.setTransformerPosition(context, position);
                SharedPreferencesUtils.setTransformer(context, item.getSettingKey());
                subscriber.onNext(lastPosition);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer lastPosition) {
                        adapter.notifyItemChanged(lastPosition);
                        adapter.notifyItemChanged(position);
                        ToastUtils.showShortSnakeBar(((Activity) context).findViewById(android.R.id.content),
                                context.getResources().getString(R.string.setting_next_action));
                    }
                });
    }

    // 图片转为文件
    public static boolean saveBitmap2file(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(MeinvxiuApplication.getInstance().getApplicationContext()
                    .getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + filename + ".png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }
}
