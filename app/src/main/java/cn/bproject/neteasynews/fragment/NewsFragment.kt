package cn.bproject.neteasynews.fragment


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.NewsBean
import cn.bproject.neteasynews.bean.PicListBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_news.*
import okhttp3.HttpUrl

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : BaseFragment() {
    var handler: Handler = Handler();
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var thread:Thread = Thread(Runnable {
            context?.let {
                var retrofitHelper= RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, it);


                           var call =  retrofitHelper.getNewsListNormalBean("T1467284926140", 0);
            var reponse= call.execute();
            var body: NewsBean? = reponse.body();
                var list =body?.getNewslist()
                var stringlist = arrayListOf<String>();
                for (index in list!!){
                    index.title?.let { it1 -> stringlist.add(it1) }
                }
                val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, stringlist)
                handler.post {    lv_fragment_news.adapter = adapter; }

            };
        })
        thread.start()
    }
    override fun getLayoutResId(): Int {
        return R.layout.fragment_news
    }


}
