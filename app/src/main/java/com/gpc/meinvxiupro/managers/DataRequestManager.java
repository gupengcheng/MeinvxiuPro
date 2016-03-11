package com.gpc.meinvxiupro.managers;

import com.gpc.meinvxiupro.apis.ImageInterface;
import com.gpc.meinvxiupro.models.ImageResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .build();
        mImageInterface = mRetrofit.create(ImageInterface.class);
    }

    public static synchronized DataRequestManager getInstance() {
        if (null == mInstance) {
            mInstance = new DataRequestManager();
        }
        return mInstance;
    }

    public synchronized void getImageResult(String tag, int pageNum, Callback<ImageResult> callback) {
        Call<ImageResult> imageResult = mImageInterface.contributor(tag, pageNum);
        imageResult.enqueue(callback);
    }
}
