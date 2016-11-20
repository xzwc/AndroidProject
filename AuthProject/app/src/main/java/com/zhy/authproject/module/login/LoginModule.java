package com.zhy.authproject.module.login;

import com.zhy.authproject.data.local.PreferencesManager;
import com.zhy.authproject.data.remote.ApiManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhanghaoye on 10/21/16.
 */
@Module
public class LoginModule {
    private final LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginView provideLoginView() {
        return loginView;
    }


    @Provides
    LoginPresenter provideLoginPresenter(ApiManager apiManager, PreferencesManager preferencesManager) {
        return new LoginPresenter(loginView,apiManager,preferencesManager);
    }
}
