package cn.bproject.neteasynews.base

import android.os.Bundle
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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


open class BaseFragment : Fragment() ,View.OnClickListener{
    private var mLoading: LinearLayout? = null;



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var parentView:View =  inflater.inflate(R.layout.fragment_base, container, false);
        val activity = activity
        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(getLayoutResId(), null, false)
        (parentView.findViewById<View>(R.id.frame_container) as FrameLayout).addView(view)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (NetworkUtils.isAvailable()){
            layout_net_error.visibility = View.GONE;
        }else{
            layout_net_error.visibility = View.VISIBLE;
            btn_retry.setOnClickListener(this)
        }
    }

    open fun getLayoutResId(): Int {
        return 0
    }


    /**
     * 点击无网络更新
     */
    protected fun onNetWorkRefresh() {
        if (NetworkUtils.isAvailable()){
            layout_net_error.visibility = View.GONE;
        }else{
            Toast.makeText(activity, "网络链接失败，请检查网络配置", Toast.LENGTH_SHORT).show();
            layout_net_error.visibility = View.VISIBLE;
            btn_retry.setOnClickListener(this)
        }
    }

    protected fun initLoading(root:View){
        if (root == null){
            return
        }
        if (activity != null && (activity is BaseActivity)){
            mLoading = root.findViewById(R.id.loading)
        }
    }

    public fun showLoading(show:Boolean) {
        if (activity != null && (activity is BaseActivity)){
            if (show){
                mLoading?.visibility =View.VISIBLE
            }else{
                mLoading?.visibility =  View.GONE
            }
        }
    }

    fun showNoContentView(show: Boolean) {

        if (activity != null && (activity is BaseActivity)){
            if (show){
                layout_page_empty.visibility = View.VISIBLE
            }else{
                layout_page_empty.visibility = View.GONE
            }
        }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_retry ->onNetWorkRefresh();
        }
    }


    /**
     * 返回操作，true表示事件被消费
     */
    fun onBackPressed(): Boolean {
        return false
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
