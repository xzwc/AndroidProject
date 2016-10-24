package com.zhy.backgroundtofront;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by zhanghaoye on 10/24/16.
 */

public class BaseActivity extends AppCompatActivity {
    protected ActivityManager mActivityManager;
     boolean isForeground = false;//应用是否处于前端


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunningForeground1()) {
            System.out.println("isRunningForeground1判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground1判断app 不在前台运行");
        }

        if (isRunningForeground2()) {
            System.out.println("isRunningForeground2判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground2判断app 不在前台运行");
        }
    }

    /**
     * 判断程序是否在前台运行
     *
     * @return
     */
    private boolean isRunningForeground1() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (currentPackageName != null && currentPackageName.equals(getPackageName())) {
            return true;
        }
        return false;
    }


    /**
     * 判断程序是否在前台运行
     * <p>
     * <p>
     * 有些机型不支持
     *
     * @return
     */
    public boolean isRunningForeground2() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }


}
