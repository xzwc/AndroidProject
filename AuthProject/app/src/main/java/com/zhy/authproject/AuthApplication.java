package com.zhy.authproject;

import android.app.Application;
import android.content.Context;

import com.zhy.authproject.di.AppComponent;
import com.zhy.authproject.di.AppModule;

/**
 * Created by zhanghaoye on 10/21/16.
 */

public class AuthApplication extends Application{
    private AppComponent appComponent;

    public static AuthApplication get(Context context){
        return (AuthApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }

}
