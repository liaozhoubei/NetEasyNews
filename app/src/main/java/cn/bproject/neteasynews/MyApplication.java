package cn.bproject.neteasynews;

import android.app.Application;
import android.os.Handler;

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
    }

    public static Handler getHandler() {
        return handler;
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }
}
