package com.zhy.authproject.di;

import com.zhy.authproject.module.login.LoginComponent;
import com.zhy.authproject.module.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhanghaoye on 10/24/16.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    LoginComponent plus(LoginModule loginModule);
}
