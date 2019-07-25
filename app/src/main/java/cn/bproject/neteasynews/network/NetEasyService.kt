package cn.bproject.neteasynews.network

import cn.bproject.neteasynews.bean.VideoBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


interface NetEasyService {
    @GET("nc/video/detail/{vid}.html")
    @ResponseFormat("json")
    fun getVideoBean(@Path("user") vid: String): Call<VideoBean>

    @GET("nc/article/list/{yaowenspecialId}/0-20.html")
    @ResponseFormat("xml")
    fun getNewsListNormalBean(@Path("yaowenspecialId") yaowenspecialId: String ): Call<VideoBean>


}