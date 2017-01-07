package cn.bproject.neteasynews.Utils;

import android.content.Context;
import android.os.Handler;

import cn.bproject.neteasynews.MyApplication;

public class UIUtils {

	public static Context getContext() {
		return MyApplication.getContext();
	}

	public static Handler getHandler() {
		return MyApplication.getHandler();
	}

	public static int getMainThreadId() {
		return MyApplication.getMainThreadId();
	}

	public static ThreadManager.ThreadPool getThreadPool() {
		return MyApplication.getThreadPool();
	}

	// /////////////////判断是否运行在主线程//////////////////////////
	public static boolean isRunOnUIThread() {
		// 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
		int myTid = android.os.Process.myTid();
		if (myTid == getMainThreadId()) {
			return true;
		}

		return false;
	}

	// 运行在主线程
	public static void runOnUIThread(Runnable r) {
		if (isRunOnUIThread()) {
			// 已经是主线程, 直接运行
			r.run();
		} else {
			// 如果是子线程, 借助handler让其运行在主线程
			Handler handler = getHandler();
			handler.post(r);
		}
	}

}
