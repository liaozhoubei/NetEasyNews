package cn.bproject.neteasynews.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.adapter.FixedPagerAdapter
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.PicListBean
import cn.bproject.neteasynews.bean.ProjectChannelBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.util.CategoryDataUtils
import cn.bproject.neteasynews.util.ListDataSave
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.tablayout_pager.*
import okhttp3.HttpUrl
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class PhotoFragment : BaseFragment() {


    var handler: Handler = Handler();

    private var fixedPagerAdapter: FixedPagerAdapter? = null
    private var fragments: MutableList<BaseFragment>? = null
    private var channelBeanList: List<ProjectChannelBean.TListBean>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentInteractionListener?.onFragmentTitleChange("图片")


        channelBeanList = CategoryDataUtils.getPicCategoryBeans()
        fixedPagerAdapter = FixedPagerAdapter(childFragmentManager)

        fragments = ArrayList()
        fragments!!.clear()
        for (i in channelBeanList!!.indices) {
            // "推荐","","0031"
            // "明星","","0003"使用瀑布流
            val channelBean = channelBeanList!!.get(i)
            val fragment = PicListFragment.newInstance(channelBean.tid!!, channelBean.column!!)
            fragments!!.add(fragment)
        }

        fixedPagerAdapter!!.setChannelBean(channelBeanList)
        fixedPagerAdapter!!.setFragments(fragments)


        tablayout_viewpager.setAdapter(fixedPagerAdapter)
//        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE); //适合很多tab
        tab_layout.setTabMode(TabLayout.MODE_FIXED) // tablayout均分，适合少Tablayout
        tab_layout.setupWithViewPager(tablayout_viewpager)
    }



    override fun getLayoutResId(): Int {
        return R.layout.tablayout_pager
    }

    override fun initData() {

    }


}
