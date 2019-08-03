package cn.bproject.neteasynews.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import cn.bproject.neteasynews.R

import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.page_error.*

/**
 *  https://blog.csdn.net/njp_njp/article/details/80003406 fragment 懒加载
 */
open abstract class BaseFragment : Fragment(), View.OnClickListener {
    private var mLoading: LinearLayout? = null;
    private var attaach = false;
    var mHandler = Handler()
    var mContext: Context? = null
    private var isFirstLoad = false
    public var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var parentView: View = inflater.inflate(R.layout.fragment_base, container, false);
        val activity = activity
        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(getLayoutResId(), null, false)
        (parentView.findViewById<View>(R.id.frame_container) as FrameLayout).addView(view)

        return parentView
    }
    // 在此次判断是否fragment 初次显示，若是则需要加载数据
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirstLoad = true;//视图创建完成，将变量置为true
        if (getUserVisibleHint()) {//判断Fragment是否可见
            onlazyLoadData();//数据加载操作
            isFirstLoad = false;//将变量置为false
        }
        onNetWorkRefresh()
    }

    abstract fun getLayoutResId(): Int

    open override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
        attaach = true;
        if (context is OnFragmentInteractionListener) {
            onFragmentInteractionListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isFirstLoad && isVisibleToUser) {//视图变为可见并且是第一次加载
            onlazyLoadData();
            isFirstLoad = false;
        }
        super.setUserVisibleHint(isVisibleToUser)
    }

    private fun onlazyLoadData() {
        initData()
    }

    /**
     * 加载要显示的数据
     */
    protected abstract fun initData()

    override fun onDetach() {
        super.onDetach()
        attaach = false
    }


    /**
     * 点击无网络更新
     */
    protected fun onNetWorkRefresh() {
        var thread = Thread() {
            if (NetworkUtils.isAvailable()) {
                mHandler.post({ if (attaach) layout_net_error.visibility = View.GONE })

            } else {
                mHandler.post {
                    if (attaach) {
                        Toast.makeText(activity, "网络链接失败，请检查网络配置", Toast.LENGTH_SHORT).show();
                        layout_net_error.visibility = View.VISIBLE;
                        btn_retry.setOnClickListener(this)
                    }
                }

            }
        }

    }

    protected fun initLoading(root: View) {
        if (root == null) {
            return
        }
        if (activity != null && (activity is BaseActivity)) {
            mLoading = root.findViewById(R.id.loading)
        }
    }

    public fun showLoading(show: Boolean) {
        if (activity != null && (activity is BaseActivity)) {
            if (show) {
                mLoading?.visibility = View.VISIBLE
            } else {
                mLoading?.visibility = View.GONE
            }
        }
    }

    fun showNoContentView(show: Boolean) {

        if (activity != null && (activity is BaseActivity)) {
            if (show) {
                layout_page_empty.visibility = View.VISIBLE
            } else {
                layout_page_empty.visibility = View.GONE
            }
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_retry -> onNetWorkRefresh();
        }
    }


    /**
     * 返回操作，true表示事件被消费
     */
    fun onBackPressed(): Boolean {
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = false;//视图销毁将变量置为false
    }


    /**
     * 销毁当前fragment
     */
    fun finishFragment() {
        try {
            activity?.onBackPressed()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



}
