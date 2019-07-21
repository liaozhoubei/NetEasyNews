package cn.bproject.neteasynews.base

import android.app.PendingIntent.getActivity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.util.AppManager
import com.blankj.utilcode.util.DeviceUtils

abstract class BaseActivity : AppCompatActivity() {
    var mToolbar: Toolbar? = null;
    var toolBarTextView: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getContentViewId())
        if (getLayoutId() > 0) { // 设置界面通用布局
            mToolbar = findViewById(R.id.my_toolbar)
            layoutInflater.inflate(getLayoutId(), findViewById(R.id.base_container) as ViewGroup, true)
            initToolbar()
        }
    }

    /**
     * 设置沉浸式状态栏
     */
    private fun setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }

    protected fun getContentViewId(): Int {
        return R.layout.activity_base
    }

    abstract fun getLayoutId(): Int


    /**
     * 设置toolbar标题居中，没有返回键
     */
    fun initToolbar() {
        toolBarTextView = findViewById(R.id.toolbar_title)
        setSupportActionBar(mToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(hasBackButton())
            actionBar.setDisplayShowTitleEnabled(false)
        }


    }

    /**
     * 是否有返回键
     */
    open fun hasBackButton(): Boolean {
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}
