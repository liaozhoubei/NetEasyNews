package cn.bproject.neteasynews.network

import retrofit2.Call
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.*


interface NetEasyService {

    // http://c.m.163.com/dlist/article/dynamic?size=10&fn=1&from=T1348648517839&offset=51&devId=1&lat=&lon=
//    @GET("nc/article/list/{tid}/{startIndex}-20.html" )
//    @ResponseConverter(ScalarsConverterFactory::class)
//    fun getNewsListNormalBean(@Path("tid") tid: String, @Path("startIndex") startIndex: Int): Call<String>

    @GET("dlist/article/dynamic")
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getNewsListNormalBean(
        @Query("from") from: String,
        @Query("offset") offset: Int,
        @Query("size") size: Int = 10,
        @Query("devId") devId: String = "44t6%2B5mG3ACAOlQOCLuIHg%3D%3D",
        @Query("lat") lat: String = "",
        @Query("lon") lon: String = "",
        @Query("fn") fn: String = "1"
    ): Call<String>


    // http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/0/20.json
    @Headers("url_change: " + Api.PictureBaseUrl) // 设置 headers, 然后在 Interceptor 中修改 baseUrl
    @GET("photocenter/api/list/{column}/{vid}/{startIndex}/20.json")
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getPicBean(
        @Path("vid") vid: String,
        @Path("column") column: String,
        @Path("startIndex") startIndex: Int
    ): Call<String>


    // http://c.m.163.com/recommend/getChanListNews?&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D&channel=T1457068979049&subtab=Video_Recom&size=10&offset=0&devId=44t6%252B5mG3ACAOlQOCLuIHg%253D%253D
    @GET(Api.SpecialColumn2)
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getVideoBean(
        @Query("channel") channelId: String = "T1457068979049",
        @Query("subtab") Video_Recom: String = "Video_Recom",
        @Query("size") size: Int = 10,
        @Query("offset") startIndex: Int,
        @Query("devId") devId: String = "44t6%2B5mG3ACAOlQOCLuIHg%3D%3D"
    ): Call<String>


    @GET("nc/article/{docid}/full.html")
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getNewsDetail(
        @Path("docid") docid: String
    ): Call<String>


    @GET("photo/api/set/{tid}/{setid}.json")
    @ResponseConverter(ScalarsConverterFactory::class)
    fun getPhotoDetail(
        @Path("tid") tid: String,
        @Path("setid") setid: String
    ): Call<String>
}


