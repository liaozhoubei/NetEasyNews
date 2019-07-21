package cn.bproject.neteasynews.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import cn.bproject.neteasynews.R
import java.lang.ref.WeakReference

class SimpleBackActivity : BaseActivity() {

    companion object {
        val BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE"
        val BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS"
    }

    private val TAG = "FLAG_TAG"
    private var mFragment: WeakReference<Fragment>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent ?: throw RuntimeException("you must provide a page info to display")
        val pageValue = data.getIntExtra(BUNDLE_KEY_PAGE, 0)
        val page = SimpleBackPage.getPageByValue(pageValue)
            ?: throw IllegalArgumentException("can not find page by value:$pageValue")

        var titleId = page.title;
        if (titleId <= 0) {
            title = ""
        } else {
            setTitle(titleId)
        }

        try {
            val fragment = page.clz?.newInstance() as Fragment
            val args = data.getBundleExtra(BUNDLE_KEY_ARGS)
            if (args != null) {
                fragment.setArguments(args)
            }
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.container, fragment, TAG)
            trans.commitAllowingStateLoss()

            mFragment = WeakReference<Fragment>(fragment)
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("generate fragment error. by value:$pageValue")
        }

    }

    override fun getLayoutId(): Int {
       return R.layout.activity_simple_fragment;
    }

    override fun onBackPressed() {
        if (mFragment != null && mFragment?.get() != null && mFragment?.get() is BaseFragment) {
            val bf = mFragment?.get() as BaseFragment
            if (bf != null && !bf.onBackPressed()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }


}
