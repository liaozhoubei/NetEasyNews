package cn.bproject.neteasynews

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import cn.bproject.neteasynews.util.AppManager
import com.blankj.utilcode.util.ProcessUtils

class App : Application() {


    override  fun  onCreate() {
        super.onCreate()

        if (ProcessUtils.isMainProcess()) {

            registerActivityLifeCycle()
        }


    }

    private fun registerActivityLifeCycle() {

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {
                activity?.let { AppManager.instance.removeActivity(it) }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                activity?.let { AppManager.instance.addActivity(it) }
            }

        })
    }


}