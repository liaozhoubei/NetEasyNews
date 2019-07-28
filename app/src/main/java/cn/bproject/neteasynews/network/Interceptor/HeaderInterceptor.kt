package cn.bproject.neteasynews.network.Interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

/**
 * 统一添加请求头
 */
class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

            //                .addHeader("Accept-Encoding", "gzip, deflate")

            .addHeader("Connection", "keep-alive")
            .addHeader("Accept", "*/*")
            //                .addHeader("Cookie", "add cookies here")
            //                .addHeader("sessionid", "2018022619571443")


            .build()

        return chain.proceed(request)

    }
}
