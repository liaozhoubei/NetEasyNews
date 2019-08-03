package cn.bproject.neteasynews.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import cn.bproject.neteasynews.bean.PicListBean
import cn.bproject.neteasynews.network.NetEasyService
import cn.bproject.neteasynews.util.DataParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PicListPositionalDataSource(var tid: String,  var column:String,var server:NetEasyService) : PositionalDataSource<PicListBean>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PicListBean>) {
        try {
            var call = server.getPicBean(vid=tid, startIndex=params.requestedStartPosition, column =column );


            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var picListNormalBeanList = DataParse.PicList(response.body())
                callback.onResult(picListNormalBeanList,0)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PicListBean>) {
        try {
            var call = server.getPicBean(vid=tid, startIndex=params.startPosition, column =column);
            var response = call.execute()
            if (response.isSuccessful){
                var body = response.body()
                var picListNormalBeanList = DataParse.PicList(response.body())
                callback.onResult(picListNormalBeanList)
            }else{
                Log.e("NewsListDataSource", "error");
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

