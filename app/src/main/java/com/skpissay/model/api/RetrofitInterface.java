package com.skpissay.model.api;

import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.model.api.entity.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface RetrofitInterface {

    @GET("tags/search")
    Observable<TagSearchResponse> searchTag(@Query("q") String query,
                                            @Query("access_token") String token);

    @GET("tags/{tag_name}/media/recent")
    Observable<MediaListResponse> searchMediaWithTag(@Path("tag_name") String tag,
                                                     @Query("access_token") String token);


    @POST("api/test_new/int/gettabledata.php")
    Observable<Places> searchPlace(@Body User body);
}
