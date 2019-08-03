package cn.bproject.neteasynews.fragment


import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.GridLayoutManager



import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.adapter.*
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.PicListBean

import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.util.DataParse
import cn.bproject.neteasynews.view.DividerGridItemDecoration
import kotlinx.android.synthetic.main.fragment_news_list.*
import okhttp3.HttpUrl



class PicListFragment : BaseFragment() {

    private var TAG ="PicListFragment"
    private var mStartIndex = 0    // 请求数据的起始参数
    private var tid: String? = null // 栏目频道id
    private var cloumn: String? = null // 栏目频道id
    private var adapter:PicListAdapter2? = null
    private var piclist:MutableList<PicListBean> = ArrayList()

    companion object {

        public val KEY = "TID"
        public val KEY_COLUMN = "COLUMN"
        @JvmStatic
        fun newInstance(tid: String, column:String) =
            PicListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY, tid)
                    putSerializable(KEY_COLUMN, column)

                }
            }
    }


    override fun getLayoutResId(): Int {
        return R.layout.fragment_pic_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            //取出保存的频道TID
            tid = arguments!!.getString(KEY)
            cloumn = arguments!!.getString(KEY_COLUMN)
        }

        adapter = PicListAdapter2(cloumn!!)

        adapter!!.list = piclist
        recyclerView.adapter = adapter
        val mLayoutManager = GridLayoutManager(mContext, 1)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.addItemDecoration(DividerGridItemDecoration(mContext))
        setupScrollListener()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume显示")
    }


    override fun initData() {
        Thread(){
            var server = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, mContext!!)
            var call = server.getPicBean(vid=tid!!, startIndex = 0, column =cloumn!! );


            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var picListNormalBeanList = DataParse.PicList(response.body())
                piclist.clear()
                piclist.addAll(picListNormalBeanList)
                mStartIndex += 20;
                mHandler.post { adapter!!.notifyDataSetChanged() }

            }else{
                Log.e("NewsListDataSource", "error");
            }
        }.start()
    }

    private val VISIBLE_THRESHOLD = 5
    private fun setupScrollListener() {
        val layoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.GridLayoutManager
        recyclerView.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (visibleItemCount + lastVisibleItem + VISIBLE_THRESHOLD >= totalItemCount) {
                    requestMore()

                }

            }
        })
    }

    private var isloading = false;
    fun requestMore(){
        Thread(){
            if (!isloading){
                isloading = true;
                try {
                    var server = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, mContext!!)
                    var call = server.getPicBean(vid=tid!!, startIndex = mStartIndex, column =cloumn!! );

                    var response = call.execute()
                    if (response.isSuccessful){
                        var body = response.body()
                        var picListNormalBeanList = DataParse.PicList(response.body())
    //                piclist.clear()
                        piclist.addAll(picListNormalBeanList)
                        mStartIndex += 20;
                        mHandler.post { adapter!!.notifyDataSetChanged() }

                    }else{
                        Log.e("NewsListDataSource", "error");
                    }
                } catch (e: Exception) {
                }
                isloading = false
            }

        }.start()
    }




}
