package com.gpc.meinvxiupro.apis;

import com.gpc.meinvxiupro.models.ImageResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pcgu on 16-3-11.
 */
public interface ImageInterface {
    @GET("imgs?col=美女&sort=0&tag3=&amp&rn=30&p=channel&from=1")
    Call<ImageResult> contributor(
            @Query("tag") String tag,
            @Query("pn") int pageNum
    );
}
