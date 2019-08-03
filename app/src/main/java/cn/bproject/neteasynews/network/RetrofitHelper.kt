package cn.bproject.neteasynews.network

import android.annotation.SuppressLint
import android.content.Context
import cn.bproject.neteasynews.network.Interceptor.BaseUrlInterceptor
import cn.bproject.neteasynews.network.Interceptor.HeaderInterceptor
import cn.bproject.neteasynews.network.Interceptor.LoggingInterceptor
import cn.bproject.neteasynews.network.cookie.CookiesManager

import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.net.ssl.*
import java.io.File
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit

class RetrofitHelper {


    companion object {
        private var retrofitService: NetEasyService? = null

        fun getInstance(baseUrl: HttpUrl, context: Context): NetEasyService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(context))
                .addConverterFactory(CompositeConverterFactory.create(GsonConverterFactory.create()))
                .build()
            val netEasyService = retrofit.create(NetEasyService::class.java)
            return netEasyService
        }


        fun getOkHttpClient(context: Context): OkHttpClient {
            var okHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(BaseUrlInterceptor())
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(HeaderInterceptor(context))
//                .cookieJar(CookiesManager(context)) // cookie 管理
                .sslSocketFactory(createSSLSocketFactory()!!, TrustAllManager())  // 测试版本，信任所有未知来源证书
                .build()
            return okHttpClient
        }

        // ---------------------------- 以下方法仅供测试使用  -------------------------------------------

        /**
         * 默认信任所有的证书
         * TODO 最好加上证书认证，主流App都有自己的证书
         * Android Https相关完全解析 当OkHttp遇到Https
         * https://blog.csdn.net/lmj623565791/article/details/48129405
         *
         * @return
         */
        @SuppressLint("TrulyRandom")
        private fun createSSLSocketFactory(): SSLSocketFactory? {
            var sSLSocketFactory: SSLSocketFactory? = null
            try {
                val sc = SSLContext.getInstance("TLS")
                sc.init(
                    null, arrayOf<TrustManager>(TrustAllManager()),
                    SecureRandom()
                )
                sSLSocketFactory = sc.socketFactory
            } catch (e: Exception) {
            }

            return sSLSocketFactory
        }
    }

    private class TrustAllManager : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate?> {
            return arrayOfNulls(0)
        }
    }

    private class TrustAllHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

    // ---------------------------- 以上方法仅供测试使用  -------------------------------------------


    /**
     * 异步上传
     * 上传文件的简单封装，部分参数需要针对服务器进行修改
     *
     * @param file
     * @param callback
     */
    fun uploadFile(file: File, callback: Callback<String>) {
        //创建表单map,里面存储服务器本接口所需要的数据;
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            //在这里添加服务器除了文件之外的其他参数
            .addFormDataPart("enctype", "multipart/form-data")
        //设置文件的格式;两个文件上传在这里添加
        val body = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        //添加文件(uploadfile就是你服务器中需要的文件参数)
        builder.addFormDataPart("uploadfile", file.name, body)
        //builder.addFormDataPart("uploadfile1", file1.getName(), imageBody1);
        //生成接口需要的list
        val parts = builder.build().parts()
//        val call = retrofitService!!.upLoading(parts)
//        call.enqueue(callback)
    }


    /**
     * 同步 Get 请求
     *
     * @param call
     * @param <T>
     * @return
    </T> */
    fun <T> synRequest(call: Call<T>): T? {

        try {
            val execute = call.execute()
            val body = execute.body()
            if (body != null) {
                return body
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return null
    }


}
