package cn.bproject.neteasynews.network.Interceptor


import okhttp3.*

import java.io.IOException
import java.util.HashMap

class CommonParamsInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val method = request.method()
        val oldUrl = request.url().toString()
        //        Log.e("---拦截器",request.url()+"---"+request.method()+"--"+request.header("User-agent"));
        val map = HashMap<String, String>()
        //        map.put("ak","12345678");
        if ("GET" == method) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(oldUrl)
            if (oldUrl.contains("?")) {
                if (oldUrl.indexOf("?") == oldUrl.length - 1) {
                } else {
                    stringBuilder.append("&")
                }
            } else {
                stringBuilder.append("?")
            }
            for ((key, value) in map) {
                stringBuilder.append(key)
                    .append("=")
                    .append(value)
                    .append("&")
            }
            if (stringBuilder.indexOf("&") != -1) {
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"))
            }
            val newUrl = stringBuilder.toString()
            request = request.newBuilder()
                .url(newUrl)
                .build()
        } else if ("POST" == method) {
            val oldRequestBody = request.body()
            if (oldRequestBody is FormBody) {
                val oldBody = oldRequestBody as FormBody?
                val builder = FormBody.Builder()
                for (i in 0 until oldBody!!.size()) {
                    builder.add(oldBody.name(i), oldBody.value(i))
                }
                for ((key, value) in map) {
                    builder.add(key, value)
                }
                val newBody = builder.build()
                request = request.newBuilder()
                    .url(oldUrl)
                    .post(newBody)
                    .build()
            }
        }
        return chain.proceed(request)

    }
}
