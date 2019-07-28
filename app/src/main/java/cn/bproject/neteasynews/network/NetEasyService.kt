package cn.bproject.neteasynews.network

import cn.bproject.neteasynews.bean.NewsBean
import cn.bproject.neteasynews.bean.PicListBean
import cn.bproject.neteasynews.bean.VideoBean
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.*


interface NetEasyService {

    // http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/0/20.json
    @GET("nc/article/list/{tid}/{startIndex}-20.html" )
    fun getNewsListNormalBean(@Path("tid") tid: String, @Path("startIndex") startIndex: Int): Call<NewsBean>




    // http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/0/20.json
    @Headers("url_change: " + Api.PictureBaseUrl) // 设置 headers, 然后在 Interceptor 中修改 baseUrl
    @GET("photocenter/api/list/{vid}/{column}/{startIndex}/20.json")
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getPicBean(@Path("vid") vid: String,
                   @Path("column") column: String,
                   @Path("startIndex") startIndex:Int): Call<String>


    // http://c.m.163.com/recommend/getChanListNews?&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D&channel=T1457068979049&subtab=Video_Recom&size=10&offset=0&devId=44t6%252B5mG3ACAOlQOCLuIHg%253D%253D
    @GET(Api.SpecialColumn2 +"?" + Api.devId)
    fun getVideoBean(@Query("channel") channelId:String = "T1457068979049",
                     @Query("subtab") Video_Recom:String ="Video_Recom",
                     @Query("size") size:Int =10,
                     @Query("offset") startIndex: Int,
                     @Query("devId") devId: String="44t6%2B5mG3ACAOlQOCLuIHg%3D%3D"
    ): Call<VideoBean>
}


