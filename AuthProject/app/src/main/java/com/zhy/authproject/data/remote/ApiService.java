package com.zhy.authproject.data.remote;


import com.zhy.authproject.data.remote.model.ActivityInfo;
import com.zhy.authproject.data.remote.model.BaseResponse;
import com.zhy.authproject.data.remote.model.CommonInfo;
import com.zhy.authproject.data.remote.model.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhanghaoye on 10/29/16.
 */

public interface ApiService {
    String SERVER_URL = "http://127.0.0.1:3000/";

    @FormUrlEncoded
    @POST("/api/v1/authproject/login")
    Observable<BaseResponse<User>> login(@Field("phone") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/authproject/{id}/modify_activity")
    Observable<BaseResponse<CommonInfo>> modifyActivity(@Path("id")String id,@Field("user_id") String user_id, @Field("access_token") String access_token);

    @GET("/api/v1/authproject/{id}")
    Observable<BaseResponse<ActivityInfo>> getActivityInfo(@Path("id")String id,String user_id,@Query("access_token") String access_token);

}
