package com.zhy.authproject.data.remote;

/**
 * Created by zhanghaoye on 10/29/16.
 */

public interface SimpleCallback<T> {
    void onStart();
    void onNext(T t);
    void onComplete();
}
