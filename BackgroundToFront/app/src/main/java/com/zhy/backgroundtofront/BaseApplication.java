package com.zhy.backgroundtofront;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by zhanghaoye on 10/24/16.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
        isRunningForeground3();
    }

    public void isRunningForeground3() {
        if (Build.VERSION.SDK_INT >= 14) {
            registerActivityLifecycleCallbacks(
                    new Application.ActivityLifecycleCallbacks() {
                        @Override
                        public void onActivityCreated(
                                Activity activity, Bundle bundle) {
                            System.out.println("activity----" + "onActivityCreated");
                        }

                        @Override
                        public void onActivityStarted(Activity activity) {
                            System.out.println("activity----" + "onActivityStarted");
                        }

                        @Override
                        public void onActivityResumed(Activity activity) {
                            System.out.println("ActivityLifecycleCallbacks判断app 在前台运行");
                            System.out.println("activity----" + "onActivityResumed");
                        }

                        @Override
                        public void onActivityPaused(Activity activity) {
                            System.out.println("activity----" + "onActivityPaused");
                            System.out.println("ActivityLifecycleCallbacks判断app 不在前台运行");

                        }

                        @Override
                        public void onActivityStopped(Activity activity) {
                            System.out.println("activity----" + "onActivityStopped");

                        }

                        @Override
                        public void onActivitySaveInstanceState(
                                Activity activity, Bundle bundle) {
                            System.out.println("activity----" + "onActivitySaveInstanceState");

                        }

                        @Override
                        public void onActivityDestroyed(Activity activity) {
                            System.out.println("activity----" + "onActivityDestroyed");
                        }
                    });

        }
    }


}
