package cn.bproject.neteasynews.network.Interceptor

import android.text.TextUtils
import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException
import java.lang.RuntimeException

/**
 * 更换 baseUrl 路径
 */
class BaseUrlInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        //获取request
        val request = chain.request()
        //从request中获取原有的HttpUrl实例oldHttpUrl
        val oldHttpUrl = request.url()
        //获取request的创建者builder
        val builder = request.newBuilder()
        //从request中获取headers，通过给定的键 url_name
        val headerValues = request.headers("url_change")
        if (headerValues != null && headerValues.size > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            val newUrl = headerValues[0]
            builder.removeHeader("url_change")
            if (TextUtils.isEmpty(newUrl)){
                throw RuntimeException("new Url is Empty, if header contain url_change ,the value must not be empty!")
            }
            //匹配获得新的BaseUrl
            val newBaseUrl = HttpUrl.parse(newUrl)
            if (newBaseUrl != null) {
                //重建新的HttpUrl，修改需要修改的url部分
                val newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())//更换网络协议
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build()
                //重建这个request，通过builder.url(newFullUrl).build()；
                // 然后返回一个response至此结束修改
                Log.e("Url", "intercept: $newFullUrl")
                return chain.proceed(builder.url(newFullUrl).build())
            }
        }
        return chain.proceed(request)
    }
}
