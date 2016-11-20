package com.zhy.authproject.module.login;

import dagger.Subcomponent;

/**
 * Created by zhanghaoye on 10/21/16.
 */
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    LoginActivity inject(LoginActivity loginActivity);
}
