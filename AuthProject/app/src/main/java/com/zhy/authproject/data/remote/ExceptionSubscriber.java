package com.zhy.authproject.data.remote;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.zhy.authproject.dialog.ProgressCancelListener;
import com.zhy.authproject.dialog.ProgressDialogHandler;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by zhanghaoye on 10/29/16.
 */

public class ExceptionSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private SimpleCallback<T> simpleCallback;
    private Application application;

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    private Boolean isShow = true;


    public ExceptionSubscriber(SimpleCallback simpleCallback, Application application, Context context, Boolean isShow) {
        this.simpleCallback = simpleCallback;
        this.application = application;
        this.context = context;

        mProgressDialogHandler = new ProgressDialogHandler(context
                , this, true);
        this.isShow = isShow;
    }

    public ExceptionSubscriber(SimpleCallback simpleCallback, Application application, Context context) {
        this.simpleCallback = simpleCallback;
        this.application = application;
        this.context = context;

        mProgressDialogHandler = new ProgressDialogHandler(context
                , this, true);
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (simpleCallback != null)
            simpleCallback.onStart();
        if (isShow)
            showProgressDialog();
    }

    @Override
    public void onCompleted() {
        if (simpleCallback != null)
            simpleCallback.onComplete();
        dismissProgressDialog();

    }

    @Override
    public void onError(Throwable e) {
        try {
            System.out.println("-------"+e.getMessage());
            e.printStackTrace();
            if (e instanceof SocketTimeoutException) {
                Toast.makeText(application, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            } else if (e instanceof ConnectException) {
                Toast.makeText(application, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(application, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (simpleCallback != null)
                simpleCallback.onComplete();
            dismissProgressDialog();
        } catch (Throwable el) {
            dismissProgressDialog();
            el.printStackTrace();
        }


    }

    @Override
    public void onNext(T t) {
        if (simpleCallback != null)
            simpleCallback.onNext(t);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
