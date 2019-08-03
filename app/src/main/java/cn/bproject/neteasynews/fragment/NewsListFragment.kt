package cn.bproject.neteasynews.fragment


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager


import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.adapter.NewsListAdapter
import cn.bproject.neteasynews.adapter.NewsListPositionalDataSource
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.NewsListNormalBean

import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.NetEasyService
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.util.DataParse
import kotlinx.android.synthetic.main.fragment_news_list.*
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsListFragment : BaseFragment() {
    private var mStartIndex = 0    // 请求数据的起始参数
    private var tid: String? = null // 栏目频道id
    private val adapter = NewsListAdapter()

    companion object {

        public val KEY = "TID"
        @JvmStatic
        fun newInstance(tid: String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY, tid)

                }
            }
    }


    override fun getLayoutResId(): Int {
        return R.layout.fragment_news_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        val mLayoutManager = LinearLayoutManager(mContext)
        recyclerView.setLayoutManager(mLayoutManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initData() {
        if (arguments != null) {
            //取出保存的频道TID
            tid = arguments!!.getString("TID")
        }
        val config = PagedList.Config.Builder()
            .setPageSize(20)                         //配置分页加载的数量
            .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(20)              //初始化加载的数量
            .build()
        var server = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, mContext!!)
        val liveData = LivePagedListBuilder(MyDataSource(tid!!, server), config)
            .build()
        liveData.observe(this, Observer<PagedList<NewsListNormalBean>>{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    class MyDataSource(var tid: String, var server: NetEasyService) : DataSource.Factory<Int, NewsListNormalBean>() {
        override fun create(): DataSource<Int, NewsListNormalBean> {
            return NewsListPositionalDataSource(tid!!, server)
        }

    }


}
