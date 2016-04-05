package com.gpc.meinvxiupro.managers;

import com.gpc.meinvxiupro.apis.ImageInterface;
import com.gpc.meinvxiupro.models.ImageResult;
import com.gpc.meinvxiupro.utils.LogUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by pcgu on 16-3-11.
 */
public class DataRequestManager {
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

    public synchronized void getImageResult(String tag, int pageNum,
                                            Scheduler scheduler,
                                            Subscriber<ImageResult> callback) {
        LogUtil.e("getImageResult == " + tag + "  pageNum == " + pageNum);
        mImageInterface.getImages(tag, pageNum)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }
}
