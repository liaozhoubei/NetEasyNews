package cn.bproject.neteasynews.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import cn.bproject.neteasynews.bean.VideoBean
import cn.bproject.neteasynews.network.NetEasyService
import cn.bproject.neteasynews.util.DataParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoListPositionalDataSource(var server:NetEasyService) : PositionalDataSource<VideoBean>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<VideoBean>) {
        try {
            var call = server.getVideoBean(startIndex=params.requestedStartPosition );


            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var videoListNormalBeanList = DataParse.VideoList(response.body())
                callback.onResult(videoListNormalBeanList,0)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<VideoBean>) {
        try {
            var call = server.getVideoBean(startIndex=params.startPosition );
            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var videoListNormalBeanList = DataParse.VideoList(response.body())
                callback.onResult(videoListNormalBeanList)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

