package cn.bproject.neteasynews.http;

import android.text.TextUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import cn.bproject.neteasynews.Utils.IOUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.StringUtils;

/**
 * Created by liaozhoubei on 2017/1/6.
 * HttpClient链接封装
 */
@SuppressWarnings("deprecated")
public class HttpHelper {

    private static final String TAG = HttpHelper.class.getSimpleName();

    /**
     * get请求，获取返回字符串内容
     */
    public static void get(String url, HttpCallbackListener httpCallbackListener) {
        HttpGet httpGet = new HttpGet(url);
        execute(url, httpGet, httpCallbackListener);
    }

    /**
     * post请求，获取返回字符串内容
     */
    public static void post(String url, byte[] bytes, HttpCallbackListener httpCallbackListener) {
        HttpPost httpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
        httpPost.setEntity(byteArrayEntity);
        execute(url, httpPost, httpCallbackListener);
    }

    /**
     * 下载
     */
    public static void download(String url, HttpCallbackListener httpCallbackListener) {
        HttpGet httpGet = new HttpGet(url);
        execute(url, httpGet, httpCallbackListener);
    }

    /**
     * 执行网络访问
     */
    private static void execute(String url, HttpRequestBase requestBase, HttpCallbackListener httpCallbackListener) {
        boolean isHttps = url.startsWith("https://");//判断是否需要采用https
        AbstractHttpClient httpClient = HttpClientFactory.create(isHttps);
        HttpContext httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        HttpRequestRetryHandler retryHandler = httpClient.getHttpRequestRetryHandler();//获取重试机制
        int retryCount = 0;
        boolean retry = true;
        while (retry) {
            try {
                HttpResponse response = httpClient.execute(requestBase, httpContext);//访问网络
                int stateCode  = response.getStatusLine().getStatusCode();
//                LogUtils.e(TAG, "http状态码：" + stateCode);
                if (response != null) {
                    if (stateCode == HttpURLConnection.HTTP_OK){
                        HttpResult httpResult = new HttpResult(response, httpClient, requestBase);
                        String result = httpResult.getString();
                        if (!TextUtils.isEmpty(result)){
                            httpCallbackListener.onSuccess(result);
                            return;
                        } else {
                            throw new RuntimeException("数据为空");
                        }
                    } else {
                        throw new RuntimeException(HttpRequestCode.ReturnCode(stateCode));
                    }
                }
            } catch (Exception e) {
                IOException ioException = new IOException(e.getMessage());
                retry = retryHandler.retryRequest(ioException, ++retryCount, httpContext);//把错误异常交给重试机制，以判断是否需要采取从事
                LogUtils.e(TAG, "重复次数：" + retryCount + "   :"+ e);
                if (!retry){
                    httpCallbackListener.onError(e);
                }
            }
        }
    }

    /**
     * http的返回结果的封装，可以直接从中获取返回的字符串或者流
     */
    public static class HttpResult {
        private HttpResponse mResponse;
        private InputStream mIn;
        private String mStr;
        private HttpClient mHttpClient;
        private HttpRequestBase mRequestBase;

        public HttpResult(HttpResponse response, HttpClient httpClient, HttpRequestBase requestBase) {
            mResponse = response;
            mHttpClient = httpClient;
            mRequestBase = requestBase;
        }

        public int getCode() {
            StatusLine status = mResponse.getStatusLine();
            return status.getStatusCode();
        }


        /**
         * 从结果中获取字符串，一旦获取，会自动关流，并且把字符串保存，方便下次获取
         */
        public String getString() {
            if (!StringUtils.isEmpty(mStr)) {
                return mStr;
            }
            InputStream inputStream = getInputStream();
            ByteArrayOutputStream out = null;
            if (inputStream != null) {
                try {
                    out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    byte[] data = out.toByteArray();
                    mStr = new String(data, "utf-8");
                } catch (Exception e) {
                    LogUtils.e(TAG, e);
                } finally {
                    IOUtils.close(out);
                    close();
                }
            }
            return mStr;
        }

        /**
         * 获取流，需要使用完毕后调用close方法关闭网络连接
         */
        public InputStream getInputStream() {
            if (mIn == null && getCode() < 300) {
                HttpEntity entity = mResponse.getEntity();
                try {
                    mIn = entity.getContent();
                } catch (Exception e) {
                    LogUtils.e(TAG, e);
                }
            }
            return mIn;
        }

        /**
         * 关闭网络连接
         */
        public void close() {
            if (mRequestBase != null) {
                mRequestBase.abort();
            }
            IOUtils.close(mIn);
            if (mHttpClient != null) {
                mHttpClient.getConnectionManager().closeExpiredConnections();
            }
        }
    }
}
