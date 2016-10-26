package com.zhy.backgroundtofront;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.jaredrummler.android.processes.AndroidProcesses;

import java.util.List;

/**
 * Created by zhanghaoye on 10/24/16.
 */

public class BaseActivity extends AppCompatActivity {
    protected ActivityManager mActivityManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        if (isRunningForeground1()) {
            System.out.println("isRunningForeground1（getRunningTasks）判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground1（getRunningTasks）判断app 不在前台运行");
        }

        if (isRunningForeground2()) {
            System.out.println("isRunningForeground2（getRunningAppProcesses）判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground2（getRunningAppProcesses）判断app 不在前台运行");
        }

        if (isRunningForeground4()) {
            System.out.println("isRunningForeground4（process进程信息）判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground（process进程信息）判断app 不在前台运行");
        }

        if (isRunningForeground5()) {
            System.out.println("isRunningForeground5（UsageStatsManager）判断app 在前台运行");
        } else {
            System.out.println("isRunningForeground5（UsageStatsManager）判断app 不在前台运行");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        ActivityManager activityManager = (ActivityManager)
                getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager
                    .RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 通过UsageStatsManager来判断
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isRunningForeground5() {
        if(Build.VERSION.SDK_INT >= 21){
            long ts = System.currentTimeMillis();
            UsageStatsManager usageStatsManager = (UsageStatsManager)
                    getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
            List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(
                    UsageStatsManager.INTERVAL_BEST, ts - 2000, ts);
            if (queryUsageStats == null || queryUsageStats.isEmpty()) {
                return false;
            }
            UsageStats recentStats = null;
            for (UsageStats usageStats : queryUsageStats) {
                if (recentStats == null ||
                        recentStats.getLastTimeUsed() < usageStats.getLastTimeUsed())
                {
                    recentStats = usageStats;
                }
            }
            if(getPackageName().equals(recentStats.getPackageName())){
                return true;
            }
        }
        return false;
    }


        /**
         * 读取Linux系统内核保存在/proc目录下的process进程信息
         *
         * @return
         */

    public boolean isRunningForeground4() {
        if (AndroidProcesses.isMyProcessInTheForeground()) {
            return true;
        }
        return false;
    }


}
