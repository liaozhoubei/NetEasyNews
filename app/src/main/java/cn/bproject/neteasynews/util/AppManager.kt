package cn.bproject.neteasynews.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

public class AppManager {

    private var activityStack: Stack<Activity>? = null
//    private var instance: AppManager? = null

    /**
     * 单一实例
     */
    companion object{
        val instance: AppManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AppManager() }
    }


    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        if (activityStack!!.size > 0) {
            activity = activityStack!!.lastElement()
        }
        return activity
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    @Synchronized
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    /**
     * 移除指定的Activity
     */
    @Synchronized
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
        }
    }

    fun finishAllActivityExcept(clz: Class<*>) {
        for (i in activityStack!!.indices) {
            val activity = activityStack!![i]
            if (activity.javaClass != clz) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (i in activityStack!!.indices) {
            val activity = activityStack!![i]
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    @Synchronized
    fun finishAllActivity() {
        if (activityStack == null) {
            return
        }
        try {
            var i = 0
            val size = activityStack!!.size
            while (i < size) {
                if (null != activityStack!![i]) {
                    activityStack!![i].finish()
                }
                i++
            }
            activityStack!!.clear()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * 判断指定界面是否存在
     *
     * @param cls
     * @return boolean
     * @Title: isActivityExist
     */
    fun isActivityExist(cls: Class<*>): Boolean {
        if (activityStack == null || activityStack!!.isEmpty()) {
            return false
        }
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            val activity = activityStack!![i]
            if (activity != null && activity.javaClass == cls) {
                return true
            }
            i++
        }
        return false
    }


    fun getActivityStack(): Stack<Activity>? {
        return activityStack
    }

    /**
     * 退出应用程序
     *
     * @param context      上下文
     * @param isBackground 是否开开启后台运行
     */
    fun AppExit(context: Context, isBackground: Boolean?) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
        } catch (e: Exception) {

        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
//            if ((!isBackground)!!) {
//                System.exit(0)
//            }
        }
    }
}