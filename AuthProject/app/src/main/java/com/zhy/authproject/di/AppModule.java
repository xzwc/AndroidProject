package com.zhy.authproject.di;

import android.app.Application;

import com.zhy.authproject.data.local.PreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhanghaoye on 10/24/16.
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public PreferencesManager provideSharedPreferences(){
        return  new PreferencesManager(application);
    }

}