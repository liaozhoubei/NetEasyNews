package cn.bproject.neteasynews;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.bproject.neteasynews.Utils.ThreadManager;

/**
 * Created by Bei on 2016/12/24.
 */

public class MyApplication extends Application {
    private static int mainThreadId;
    private static Handler handler;
    private static Context mContext;
    private static ThreadManager.ThreadPool mThreadPool;

//    private static MyApplication sMyApplication;


    public void setThreadPool(ThreadManager.ThreadPool threadPool) {
        mThreadPool = threadPool;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        mThreadPool = ThreadManager.getThreadPool();

        initBugly();


    }

    /**
     * 初始化bugly
     */
    private void initBugly() {
    // 获取当前包名
        String packageName = mContext.getPackageName();
    // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
    // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "7a544c9222", false);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


//    public static MyApplication getInstance() {
//        // if语句下是不会走的，Application本身已单例
//        if (sMyApplication == null) {
//            synchronized (MyApplication.class) {
//                if (sMyApplication == null) {
//                    sMyApplication = new MyApplication();
//                }
//            }
//        }
//        return sMyApplication;
//    }


    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return handler;
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static ThreadManager.ThreadPool getThreadPool() {
        return mThreadPool;
    }
}
