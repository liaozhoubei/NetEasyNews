package cn.bproject.neteasynews.network.Interceptor

import android.content.Context
import android.webkit.WebSettings
import cn.bproject.neteasynews.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

/**
 * 统一添加请求头
 */
class HeaderInterceptor(var context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                // https://www.jianshu.com/p/4132b381f07e
                // 某些服务器使用 okhttp 请求出现 HTTP 403 Forbidden 的解决办法
            .removeHeader("User-Agent")//移除旧的
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))//添加真正的头部

            //                .addHeader("Accept-Encoding", "gzip, deflate")

//            .addHeader("Connection", "keep-alive")
//            .addHeader("Accept", "*/*")
            //                .addHeader("Cookie", "add cookies here")
            //                .addHeader("sessionid", "2018022619571443")


            .build()

        return chain.proceed(request)

    }
}
