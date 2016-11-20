package com.zhy.authproject.module.login;

import android.app.Activity;

import com.zhy.authproject.data.local.PreferencesManager;
import com.zhy.authproject.data.remote.ApiManager;
import com.zhy.authproject.data.remote.SimpleCallback;
import com.zhy.authproject.data.remote.model.User;

/**
 * Created by zhanghaoye on 10/21/16.
 */

public class LoginPresenter {
    private final LoginView loginView;
    private final ApiManager apiManager;
    private final PreferencesManager preferencesManager;

    public LoginPresenter(LoginView loginView,ApiManager apiManager, PreferencesManager preferencesManager) {
        this.loginView = loginView;
        this.apiManager = apiManager;
        this.preferencesManager = preferencesManager;
    }



    public void login(final String username, final String password) {
        apiManager.login((Activity) loginView, username, password, new SimpleCallback<User>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onNext(User user) {
                loginView.loginSuccess();
            }

            @Override
            public void onComplete() {
            }
        },true);
    }


}
