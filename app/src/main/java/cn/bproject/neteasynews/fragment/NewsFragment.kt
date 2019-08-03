package cn.bproject.neteasynews.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.adapter.FixedPagerAdapter
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.ProjectChannelBean
import cn.bproject.neteasynews.util.CategoryDataUtils
import cn.bproject.neteasynews.util.IOUtils
import cn.bproject.neteasynews.util.ListDataSave
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.tablayout_pager.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : BaseFragment() {

    var sharedPreferences: SharedPreferences? = null
    var handler: Handler = Handler();
    private var listDataSave: ListDataSave? = null
    private var myChannelList: List<ProjectChannelBean.TListBean>? = null
    private var moreChannelList: List<ProjectChannelBean.TListBean>? = null
    private var fragments: MutableList<BaseFragment>? = null
    private var fixedPagerAdapter: FixedPagerAdapter? = null
    private var baseFragment: BaseFragment? = null
    // 当前新闻频道的位置
    private var tabPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentInteractionListener?.onFragmentTitleChange("新闻中心")
//        initListener()

        sharedPreferences = mContext?.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        listDataSave = ListDataSave(mContext, "channel")
        fragments = ArrayList<BaseFragment>()
        fixedPagerAdapter = FixedPagerAdapter(childFragmentManager)
        bindData()
        tab_layout.setupWithViewPager(tablayout_viewpager)
    }

    override fun getLayoutResId(): Int {
        return R.layout.tablayout_pager
    }

    override fun initData() {

    }

    private fun bindData() {
        getDataFromSharedPreference()
        fixedPagerAdapter!!.setChannelBean(myChannelList)
        fixedPagerAdapter!!.setFragments(fragments)
        tablayout_viewpager.setAdapter(fixedPagerAdapter)
    }

    private fun initListener() {


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab!!.getPosition()
            }

        })

    }

    /**
     * 判断是否第一次进入程序
     * 如果第一次进入，直接获取设置好的频道
     * 如果不是第一次进入，则从sharedPrefered中获取设置好的频道
     */
    private fun getDataFromSharedPreference() {
        var isFirst = sharedPreferences!!.getBoolean("isFirst", true)
//        if (isFirst) {
            myChannelList = CategoryDataUtils.getChannelCategoryBeans()
            moreChannelList = getMoreChannelFromAsset()
            myChannelList = setType(myChannelList!!)
            moreChannelList = setType(moreChannelList!!)
            listDataSave!!.setDataList("myChannel", myChannelList)
            listDataSave!!.setDataList("moreChannel", moreChannelList)
            val edit = sharedPreferences!!.edit()
            edit.putBoolean("isFirst", false)
            edit.commit()
//        } else {
//            myChannelList = listDataSave!!.getDataList("myChannel", ProjectChannelBean::class.java)
//        }
        fragments!!.clear()
        for (i in myChannelList!!.indices) {
            baseFragment = NewsListFragment.newInstance(myChannelList!!.get(i).tid!!) as BaseFragment

            fragments!!.add(baseFragment!!)
        }
        if (myChannelList!!.size <= 4) {
            tab_layout.setTabMode(TabLayout.MODE_FIXED)
        } else {
            tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE)
        }

    }


    private fun setType(list: List<ProjectChannelBean.TListBean>): List<ProjectChannelBean.TListBean> {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val channelBean = iterator.next()
//            channelBean.tabType = APPConst.ITEM_EDIT
        }
        return list
    }

    /**
     * 从Asset目录中读取更多频道
     *
     * @return
     */
    fun getMoreChannelFromAsset(): List<ProjectChannelBean.TListBean> {
        val moreChannel = IOUtils.readFromFile(mContext, "projectChannel.txt")
        var gson = Gson()
        var projectChannelBean = gson.fromJson(moreChannel, ProjectChannelBean::class.java);
        var projectChannelBeanList = projectChannelBean.tList
        return projectChannelBeanList!!
    }
}
