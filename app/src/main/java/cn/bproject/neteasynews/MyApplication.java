package cn.bproject.neteasynews;

import android.app.Application;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Bei on 2016/12/24.
 */

public class MyApplication extends Application{
    private static int mainThreadId;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
//        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

        // 初始化Okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);



    }

    public static Handler getHandler() {
        return handler;
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }
}
