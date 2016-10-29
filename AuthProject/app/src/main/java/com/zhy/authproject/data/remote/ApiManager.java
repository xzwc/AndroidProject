package com.zhy.authproject.data.remote;

import android.app.Application;

import com.zhy.authproject.data.remote.model.BaseResponseFunc;
import com.zhy.authproject.data.remote.model.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghaoye on 10/24/16.
 */

public class ApiManager {
    private final ApiService apiService;

    private final Application application;

    public ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    public void login(String username, String password,SimpleCallback<User> simpleCallback){
        apiService.login(username,password)
                .flatMap(new BaseResponseFunc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));
    }
}
