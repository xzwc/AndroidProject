package com.zhy.authproject.data.remote;


import com.zhy.authproject.data.remote.model.BaseResponse;
import com.zhy.authproject.data.remote.model.User;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhanghaoye on 10/29/16.
 */

public interface ApiService {
    String SERVER_URL = "http://127.0.0.1:3000/";

    @GET("users")
    Observable<BaseResponse<User>> login(@Query("username") String username, @Query("password") String password);



}
