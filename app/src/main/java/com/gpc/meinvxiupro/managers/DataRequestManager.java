package com.gpc.meinvxiupro.managers;

import android.content.Context;

import com.gpc.meinvxiupro.apis.ImageInterface;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.utils.LogUtil;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-3-11.
 */
public class DataRequestManager {
    private static final String TAG = "DataRequestManager";
    public static final String IP = "http://image.baidu.com/data/";
    private static DataRequestManager mInstance;
    private static ImageInterface mImageInterface;

    private DataRequestManager() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mImageInterface = mRetrofit.create(ImageInterface.class);
    }

    public static synchronized DataRequestManager getInstance() {
        if (null == mInstance) {
            mInstance = new DataRequestManager();
        }
        return mInstance;
    }

    public synchronized void getImageResult(final Context context, final String tag, int pageNum,
                                            Scheduler scheduler,
                                            Subscriber<ImageResult> callback) {
        // get cache data
        Observable.create(new Observable.OnSubscribe<ImageResult>() {
            @Override
            public void call(Subscriber<? super ImageResult> subscriber) {
                LogUtil.e(TAG, "currentThread1 ->" + Thread.currentThread());
                ImageResult imageResult = SharedPreferencesUtils.getCacheImageResult(context, tag);
                subscriber.onNext(imageResult);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
        // request data from network
        mImageInterface.getImages(tag, pageNum)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }
}
