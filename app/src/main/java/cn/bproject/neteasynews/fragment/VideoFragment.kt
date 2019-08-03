package cn.bproject.neteasynews.fragment


import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.adapter.PicListPositionalDataSource
import cn.bproject.neteasynews.adapter.VideoListAdapter
import cn.bproject.neteasynews.adapter.VideoListPositionalDataSource
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.PicListBean
import cn.bproject.neteasynews.bean.VideoBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.NetEasyService
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.view.DividerGridItemDecoration
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_video.*
import okhttp3.HttpUrl


class VideoFragment : BaseFragment() {

    var handler: Handler = Handler();
    private val adapter = VideoListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentInteractionListener?.onFragmentTitleChange("视频")
        recyclerView.adapter = adapter
        val mLayoutManager = GridLayoutManager(mContext, 1)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.addItemDecoration(DividerGridItemDecoration(mContext))



    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_news_list
    }

    override fun initData() {

        val config = PagedList.Config.Builder()
            .setPageSize(20)                         //配置分页加载的数量
            .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(20)              //初始化加载的数量
            .build()
        var server = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, mContext!!)
        val liveData = LivePagedListBuilder(MyDataSource( server), config)
            .build()
        liveData.observe(this, Observer<PagedList<VideoBean>>{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    class MyDataSource( var server: NetEasyService) : DataSource.Factory<Int, VideoBean>() {
        override fun create(): DataSource<Int, VideoBean> {
            return VideoListPositionalDataSource(server)
        }
    }

    fun itemClic(position:Int, bean:VideoBean ){
//        val intent = Intent(activity, VideoDetailActivity::class.java)
//        intent.putExtra(VID, mVideoBeanList.get(position).vid)
//        intent.putExtra(MP4URL, mVideoBeanList.get(position).mp4_url)
//        activity.startActivity(intent)
    }

}
