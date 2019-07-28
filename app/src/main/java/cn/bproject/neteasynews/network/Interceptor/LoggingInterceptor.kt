package cn.bproject.neteasynews.network.Interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

/**
 * 日志拦截器，主要用于测试网络请求
 */
class LoggingInterceptor : Interceptor {
    internal var tag = "LoggingInterceptor"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        println(
            String.format(
                "Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()
            )
        )
        //        Log.d(tag, );

        val response = chain.proceed(request)

        val t2 = System.nanoTime()

        println(
            String.format(
                "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()
            )
        )

        return response
    }
}