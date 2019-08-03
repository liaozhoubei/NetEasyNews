package cn.bproject.neteasynews.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseActivity
import cn.bproject.neteasynews.bean.NewsDetailBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.util.DataParse
import cn.bproject.neteasynews.util.IOUtils
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.page_loading.*
import okhttp3.HttpUrl

class NewsDetailActivity : BaseActivity() {
    private var mDocid: String? = null
    private var mWebSettings: WebSettings? = null
    private var mContext: Context?= null
    private var mHandler = Handler()
//    private var mNewsDetailBeen: NewsDetailBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolBarTextView?.text=""
        mContext = this;
        val intent = intent
        mDocid = intent.getStringExtra("DOCID")




        Thread(){
            Log.d("test", "htttt")
        }.start()
        initValidata()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_news_detail
    }

    fun initValidata() {
        setWebView();
        // 将设置好的JavaScriptInterface对象传入，第二个参数则是为这个对象设置名称（可随意）
        details_content.addJavascriptInterface(JavaScriptInterface(), "androidMethod");
        requestData();
    }

    private fun requestData() {
        Thread(){
            try {
                var instance = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, this)
                var call = instance.getNewsDetail(mDocid!!)
                var response = call.execute()
                if (response.isSuccessful){
                    var detail = response.body()
                    var deatilBean = DataParse.NewsDetail(detail, mDocid);
                    mHandler.post {
                        loading.visibility = View.INVISIBLE
                        bindData(deatilBean) }
                }else{
//                    var error = response.errorBody()
                    response.code()
                    Log.e("NewsDeatailActivity", "error ${ response.code()}")
                    mHandler.post(){
                        Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

    }

    fun bindData(newsDetailBean: NewsDetailBean) {
        if (newsDetailBean != null) {
            changeNewsDetail(newsDetailBean!!)
            val body = newsDetailBean!!.body
            // 使用css样式的方式设置图片大小
            val css = "<style type=\"text/css\"> img {" +
                    "width:100%;" +
                    "height:auto;" +
                    "}" +
                    "body {" +
                    "margin-right:15px;" +
                    "margin-left:15px;" +
                    "margin-top:15px;" +
                    "font-size:24px;" +
                    "}" +
                    "</style>"
            val html = "<html><header>$css</header><body>$body</body></html>"
//            Log.d(TAG, "html: $html")
            val title = newsDetailBean!!.title
            val ptime = newsDetailBean!!.ptime
            val source = newsDetailBean!!.source

            details_title.text = title
            details_name.text = source
            details_time.text = ptime
            //details_content.loadData(articleBean.getContext(),"text/html","UTF-8");
            try {
                details_content.loadDataWithBaseURL(null, html, "text/html", "UTF-8", "")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
//            showEmptyPage()
        }
    }

    /**
     * 对获得的文章详情判断是否有图片，
     * 如果有图片则通过替换字符串的方式获取图片url
     *
     * @param newsDetail
     */
    private fun changeNewsDetail(newsDetail: NewsDetailBean) {
        val imgSrcs = newsDetail.img
        if (isChange(imgSrcs)) {
            var newsBody = newsDetail.body
            newsBody = changeNewsBody(imgSrcs!!, newsBody!!)
            newsDetail.body = newsBody
        }
    }

    /**
     * 判断是否有图片集合
     * @param imgSrcs
     * @return
     */
    private fun isChange(imgSrcs: List<NewsDetailBean.ImgBean>?): Boolean {
        return imgSrcs != null && imgSrcs.size >= 0
    }

    /**
     * 将图片替换为能够直接使用的标签，即将  =》<img src="xxx"></img>
     *
     * @param imgSrcs  获取到的图片资源数组
     * @param newsBody 修改后的文章详情
     * @return
     */
    private fun changeNewsBody(imgSrcs: List<NewsDetailBean.ImgBean>, newsBody: String): String {
        var newsBody = newsBody
        var oldChars = ""
        var newChars = ""
        for (i in imgSrcs.indices) {
            oldChars = "<!--IMG#$i-->"
            // 在客户端解决WebView图片屏幕适配的问题，在<img标签下添加style='max-width:90%;height:auto;'即可
            // 如："<img" + " style=max-width:100%;height:auto; " + "src=\"" + imgSrcs.get(i).getSrc() + "\"" + "/>"
            newChars = "<img" + " src=\"" + imgSrcs[i].src + "\"" + "/>"
            newsBody = newsBody.replace(oldChars, newChars)
        }
//        Log.d(TAG, "changeNewsBody: $newsBody")
        return newsBody
    }


    /**
     * 设置WebView相关配置
     */
    private fun setWebView() {
        mWebSettings = details_content.getSettings()
        //自适应屏幕
        mWebSettings!!.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        mWebSettings!!.setLoadWithOverviewMode(true) // 缩放至屏幕的大小
        // 打开页面时， 自适应屏幕
        mWebSettings!!.setUseWideViewPort(true) //将图片调整到适合webview的大小
        mWebSettings!!.setSupportZoom(true) //支持缩放
        mWebSettings!!.setJavaScriptEnabled(true)  //开启javascript
        mWebSettings!!.setDomStorageEnabled(true)  //开启DOM
        mWebSettings!!.setDefaultTextEncodingName("utf-8") //设置编码
        // // web页面处理
        mWebSettings!!.setAllowFileAccess(true)// 支持文件流

        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，再进行加载图片
        mWebSettings!!.setBlockNetworkImage(true)
        //开启缓存机制
        mWebSettings!!.setAppCacheEnabled(true)
        setTextSize()
        //设置webview
        details_content.setWebChromeClient(MyWebChromeClient())
        details_content.setWebViewClient(MyWebViewClient())
    }

    /**
     * 被JavaScript调用的Android方法
     * 点击webView中的图片能够跳转到PhotoActivity中查看大图
     */
    internal inner class JavaScriptInterface {
        /**
         * 在javaScript中获得html中的图片url
         * @param imageUrl  图片url
         */
        @android.webkit.JavascriptInterface
        fun startPhotoActivity(imageUrl: String) {
            val intent = Intent(this@NewsDetailActivity, PhotoActivity::class.java)
            intent.putExtra("image_url", imageUrl)
            startActivity(intent)
        }
    }


    internal inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
//            LogUtils.d(TAG, "加载进度发生变化:$newProgress")
        }
    }


    inner  class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
//            LogUtils.d(TAG, "网页开始加载:$url")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
////            LogUtils.d(TAG, "网页加载完成...$url")
            mWebSettings!!.setBlockNetworkImage(false)
//            // 网页加载完毕后，将其js方法注入到网页中
            details_content.loadUrl("javascript:(" + IOUtils.readFromFile(mContext, "js.txt") + ")()")
        }

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
//            LogUtils.d(TAG, "加载的资源:$url")
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            LogUtils.d(TAG, "拦截到URL信息为:$url")
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    private fun setTextSize() {
//        val textSize = Int.valueOf(sharedPreferences.getString("text_size", "2")!!)
//        when (textSize) {
//            0 ->
//                // 超大字体
//                mWebSettings.setTextSize(WebSettings.TextSize.LARGEST)
//            1 ->
//                // 大字体
//                mWebSettings.setTextSize(WebSettings.TextSize.LARGER)
//            2 ->
//                // 正常字体
//                mWebSettings.setTextSize(WebSettings.TextSize.NORMAL)
//            3 ->
//                // 小字体
//                mWebSettings.setTextSize(WebSettings.TextSize.SMALLER)
//            4 ->
//                // 超小字体
//                mWebSettings.setTextSize(WebSettings.TextSize.SMALLEST)
//
//            else -> {
//            }
//        }// settings.setTextZoom(22);
    }

}
