package com.gpc.meinvxiupro.managers;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.Map;

/**
 * Created by gupengcheng on 16/4/23.
 */
public class OkHttpManager {
    private static OkHttpClient mOkHttpClient;
    private static OkHttpManager mInstance;

    public static OkHttpManager getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpManager();
            mOkHttpClient = new OkHttpClient();
        }
        return mInstance;
    }

    public static String post(String url, Map<String, Object> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), (String) entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        String info = "";
        try {
            com.squareup.okhttp.Response response = mOkHttpClient.newCall(request).execute();
            info = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
}
