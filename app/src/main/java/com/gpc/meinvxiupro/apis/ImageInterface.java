package com.gpc.meinvxiupro.apis;

import com.gpc.meinvxiupro.models.ImageResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pcgu on 16-3-11.
 */
public interface ImageInterface {
    /**
     * get image result api
     *
     * @param tag     image tag
     * @param pageNum means startIndex,from 0 to totalNum
     * @return
     */
    @GET("imgs?col=美女&sort=0&tag3=&amp&rn=30&p=channel&from=1")
    Observable<ImageResult> getImages(
            @Query("tag") String tag,
            @Query("pn") int pageNum
    );
}
