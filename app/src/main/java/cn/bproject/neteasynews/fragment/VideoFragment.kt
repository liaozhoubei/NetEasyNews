package cn.bproject.neteasynews.fragment


import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.VideoBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_video.*
import okhttp3.HttpUrl

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class VideoFragment : BaseFragment() {

    var handler:Handler = Handler();

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var thread:Thread = Thread(Runnable {
            context?.let {
                var retrofitHelper= RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, it);
                var videoBean: VideoBean? = retrofitHelper.getVideoBean(startIndex=0).execute().body()
                var list =videoBean?.videolist;
                var stringlist = arrayListOf<String>();
                for (index in list!!){
                    index.title?.let { it1 -> stringlist.add(it1) }
                }
                val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, stringlist)
                handler.post {    lv_fragment_video.adapter = adapter; }

            };
        })
        thread.start()


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_video
    }

}
