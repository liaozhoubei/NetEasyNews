package cn.bproject.neteasynews.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.bean.NewsDetailBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;

import static cn.bproject.neteasynews.R.id.details_content;

/**
 * Created by liaozhoubei on 2016/12/28.
 */

public class NewsDetailActivity extends AppCompatActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private TextView details_title, details_name, details_time;

    private WebView mWebView;
    private FrameLayout home_framelayout;
    private LinearLayout loading, empty, error;
    private String titleUrl, titleId;
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    //    private RelativeLayout relative_content;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            bindData();
        }
    };
    private WebSettings mWebSettings;

    private String mDocid;
    private NewsDetailBean mNewsDetailBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        Intent intent = getIntent();
        mDocid = intent.getStringExtra("DOCID");
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }


        details_title = (TextView) this.findViewById(R.id.details_title);
        // 设置标题加粗
        TextPaint tp = details_title.getPaint();
        tp.setFakeBoldText(true);
        details_name = (TextView) this.findViewById(R.id.details_name);
        details_time = (TextView) this.findViewById(R.id.details_time);
//        details_ad = (ImageView) this.findViewById(R.id.details_ad);
        mWebView = (WebView) this.findViewById(details_content);

    }

    @Override
    public void initValidata() {
        mWebSettings = mWebView.getSettings();
        // 打开页面时， 自适应屏幕
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        mWebSettings.setSupportZoom(true); //支持缩放

        mWebSettings.setJavaScriptEnabled(true);  //开启javascript
        mWebSettings.setDomStorageEnabled(true);  //开启DOM
        mWebSettings.setDefaultTextEncodingName("utf-8"); //设置编码
        // // web页面处理
        mWebSettings.setAllowFileAccess(true);// 支持文件流

        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，再进行加载图片
        mWebSettings.setBlockNetworkImage(true);
        //开启缓存机制
        mWebSettings.setAppCacheEnabled(true);
        //设置webview
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        // 将设置好的JavaScriptInterface对象传入，第二个参数则是为这个对象设置名称（可随意）
        mWebView.addJavascriptInterface(new JavaScriptInterface(), "androidMethod");
        // 创建线程池
        mThreadPool = ThreadManager.getThreadPool();
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String url = Api.DetailUrl + mDocid + Api.endDetailUrl;
                Log.d(TAG, "文章url为: " + url);
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mNewsDetailBeen = DataParse.NewsDetail(result, mDocid);
                        handler.sendMessage(handler.obtainMessage());
                    }

                    @Override
                    public void onError(String result, Exception e) {

                    }
                });

            }
        });


    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        if (mNewsDetailBeen != null) {

            changeNewsDetail(mNewsDetailBeen);
            String content = mNewsDetailBeen.getBody();
            String title = mNewsDetailBeen.getTitle();
            String ptime = mNewsDetailBeen.getPtime();
            String source = mNewsDetailBeen.getSource();

            details_title.setText(title);
            details_name.setText(source);
            details_time.setText(ptime);

            //details_content.loadData(articleBean.getContext(),"text/html","UTF-8");
            mWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", "");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 对获得的文章详情判断是否有图片，
     * 如果有图片则通过替换字符串的方式获取图片url
     *
     * @param newsDetail
     */
    private void changeNewsDetail(NewsDetailBean newsDetail) {
        List<NewsDetailBean.ImgBean> imgSrcs = newsDetail.getImg();
        if (isChange(imgSrcs)) {
            String newsBody = newsDetail.getBody();
            newsBody = changeNewsBody(imgSrcs, newsBody);
            newsDetail.setBody(newsBody);
        }
    }

    /**
     * 判断是否有图片集合
     * @param imgSrcs
     * @return
     */
    private boolean isChange(List<NewsDetailBean.ImgBean> imgSrcs) {
        return imgSrcs != null && imgSrcs.size() >= 0;
    }

    /**
     * 将图片替换为能够直接使用的标签，即将<!--IMG#3-->  =》<img src="xxx">
     *
     * @param imgSrcs  获取到的图片资源数组
     * @param newsBody 修改后的文章详情
     * @return
     */
    private String changeNewsBody(List<NewsDetailBean.ImgBean> imgSrcs, String newsBody) {
        String oldChars = "";
        String newChars = "";
        for (int i = 0; i < imgSrcs.size(); i++) {
            oldChars = "<!--IMG#" + i + "-->";
            // 在客户端解决WebView图片屏幕适配的问题，在<img标签下添加style='max-width:90%;height:auto;'即可
            newChars = "<img" + " style='max-width:100%;height:auto;' " + "src=\"" + imgSrcs.get(i).getSrc() + "\"" + "/>";
            newsBody = newsBody.replace(oldChars, newChars);
        }
        Log.d(TAG, "changeNewsBody: " + newsBody);
        return newsBody;
    }

    /**
     * 被JavaScript调用的Android方法
     *  点击webView中的图片能够跳转到PhotoActivity中查看大图
     */
    class JavaScriptInterface {
        /**
         * 在javaScript中获得html中的图片url
         * @param imageUrl  图片url
         */
        @android.webkit.JavascriptInterface
        public void startPhotoActivity(String imageUrl) {
            Intent intent = new Intent(NewsDetailActivity.this, PhotoActivity.class);
            intent.putExtra("image_url", imageUrl);
            startActivity(intent);
        }
    }

    /**
     * 读取资源文件，将js方法以字符串方法返回
     *
     * @return
     */
    private String readJS() {
        try {
            InputStream inStream = getAssets().open("js.txt");
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inStream.read(bytes)) > 0) {
                outStream.write(bytes, 0, len);
            }
            return outStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            LogUtils.d(TAG, "加载进度发生变化:" + newProgress);
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            LogUtils.d(TAG, "网页开始加载:" + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LogUtils.d(TAG, "网页加载完成..." + url);
            mWebSettings.setBlockNetworkImage(false);
            // 网页加载完毕后，将其js方法注入到网页中
            mWebView.loadUrl("javascript:(" + readJS() + ")()");

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            LogUtils.d(TAG, "加载的资源:" + url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.d(TAG, "拦截到URL信息为:" + url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

}
