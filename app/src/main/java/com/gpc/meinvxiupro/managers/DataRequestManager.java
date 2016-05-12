package com.gpc.meinvxiupro.managers;

import android.content.Context;
import android.text.TextUtils;

import com.gpc.meinvxiupro.apis.ImageInterface;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.utils.Constant;
import com.gpc.meinvxiupro.utils.MetaDataUtils;
import com.gpc.meinvxiupro.utils.SharedPreferencesUtils;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by pcgu on 16-3-11.
 */
public class DataRequestManager {
    private static DataRequestManager mInstance;
    private static ImageInterface mImageInterface;

    private DataRequestManager() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(MetaDataUtils.getStringMetaData(Constant.MetaDataName.IMG_IP))
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

    /**
     * get cache data
     */
    public synchronized void getImageCache(final Context context, final String tag,
                                           Subscriber<ImageResult> callback) {
        Observable.create(new Observable.OnSubscribe<ImageResult>() {
            @Override
            public void call(Subscriber<? super ImageResult> subscriber) {
                ImageResult imageResult = SharedPreferencesUtils.getCacheImageResult(context, tag);
                subscriber.onNext(imageResult);
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    /**
     * request data from network
     *
     * @param tag
     * @param pageNum
     * @param callback
     */
    public synchronized void getImageResult(final Context context, final String tag, final int pageNum,
                                            Subscriber<ImageResult> callback) {
        mImageInterface.getImages(tag, pageNum)
                .doOnNext(new Action1<ImageResult>() {
                    @Override
                    public void call(ImageResult imageResult) {
                        if (pageNum == 0) {
                            SharedPreferencesUtils.setCacheImageResult(context, tag, imageResult);
                        }
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public synchronized void sendFeedback(final Map<String, Object> sendMap,
                                          Subscriber<Boolean> callback) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                String response = OkHttpManager.getInstance().post(
                        MetaDataUtils.getStringMetaData(Constant.MetaDataName.FEEDBACK_IP), sendMap);
                if (TextUtils.equals(response, "success")) {
                    subscriber.onNext(true);
                } else {
                    subscriber.onNext(false);
                }
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

}
