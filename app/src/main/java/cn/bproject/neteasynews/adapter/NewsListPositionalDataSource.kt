package cn.bproject.neteasynews.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import cn.bproject.neteasynews.bean.NewsListNormalBean
import cn.bproject.neteasynews.network.NetEasyService
import cn.bproject.neteasynews.util.DataParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListPositionalDataSource(var tid: String, var server:NetEasyService) : PositionalDataSource<NewsListNormalBean>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<NewsListNormalBean>) {
        try {
            var call = server.getNewsListNormalBean(from=tid, offset=params.requestedStartPosition);


            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var newsListNormalBeanList = DataParse.NewsList(response.body(), tid)
                callback.onResult(newsListNormalBeanList,0)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<NewsListNormalBean>) {
        try {
            var call = server.getNewsListNormalBean(tid, params.startPosition);
            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var newsListNormalBeanList = DataParse.NewsList(response.body(), tid)
                callback.onResult(newsListNormalBeanList)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

