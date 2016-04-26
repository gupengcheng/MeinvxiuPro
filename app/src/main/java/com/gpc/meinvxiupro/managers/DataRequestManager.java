package com.gpc.meinvxiupro.managers;

import android.content.Context;
import android.text.TextUtils;

import com.gpc.meinvxiupro.apis.ImageInterface;
import com.gpc.meinvxiupro.models.ImageResult;
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
    public static final String IP = "http://image.baidu.com/data/";
    public static final String URL_SEND_FEEDBACK = "http://520mengchong.com/app/feedback/post_feedback.php";
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
                String response = OkHttpManager.getInstance().post(URL_SEND_FEEDBACK, sendMap);
                if (TextUtils.equals(response,"success")) {
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
