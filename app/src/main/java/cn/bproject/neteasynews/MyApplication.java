package cn.bproject.neteasynews;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.bproject.neteasynews.Utils.ThreadManager;

/**
 * Created by Bei on 2016/12/24.
 */

public class MyApplication extends Application{
    private static int mainThreadId;
    private static Handler handler;
    private static Context mContext;
    private ThreadManager.ThreadPool mThreadPool;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

//        // 初始化Okhttp
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//        OkHttpUtils.initClient(okHttpClient);

        mThreadPool = ThreadManager.getThreadPool();

    }

    public static Context getContext(){
        return mContext;
    }

    public static Handler getHandler() {
        return handler;
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }
}
