package cn.bproject.neteasynews.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.adapter.NewsListAdapter;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.datamanager.NewsDataManager;
import cn.bproject.neteasynews.fragment.BaseFragment;
import okhttp3.Call;

import static android.content.Context.WINDOW_SERVICE;
import static cn.bproject.neteasynews.R.id.listView_news_list;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment implements DefineView {

    private final String TAG = NewsListFragment.class.getSimpleName();
    private NewsListAdapter mNewsListAdapter;
    private View mView;
    private PullToRefreshListView mListView_news_list;
    private List<NewsListNormalBean> mNewsListNormalBeanList;
    private List<NewsListNormalBean> newlist;
    private int mStartIndex = 0;
    private String mUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        String json = NewsDataManager.getDataFromInternet("http://c.m.163.com/nc/article/list/T1467284926140/0-20.html", "T1467284926140");
//        List<NewsListNormalBean> newsListNormalBeans = NewsDataManager.parseJson(json, "T1467284926140");
        Log.d(TAG, "LoadingPage: ");

        // 获取屏幕的宽度
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
        // windowManager.getDefaultDisplay().getWidth();
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        long width = outMetrics.widthPixels;
        long height = outMetrics.heightPixels;
        Log.d(TAG, "width: " + width+"     height: " + height);


        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
    }


    @Override
    public void initView() {
        mListView_news_list = (PullToRefreshListView) mView.findViewById(listView_news_list);

    }

    @Override
    public void initValidata() {
        requestData();
    }

    public void requestData() {
        mUrl = Api.CommonUrl + Api.toutiaoId + "/" + mStartIndex + Api.endUrl;
        Log.d(TAG, "mUrl地址为: " + mUrl);
//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html
//        getJson(mUrl);
        useHttpClient(mUrl);
//        OkHttpUtils.get().url(mUrl).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.d(TAG, "onError: getDataFromInternet请求失败" + e);
//                Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
//                mListView_news_list.onRefreshComplete();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//                mNewsListNormalBeanList = NewsDataManager.parseJson(response.toString(), "T1467284926140");
//                bindData();
//                mListView_news_list.onRefreshComplete();
//                Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void initListener() {
        // 设置是否支持上拉和下拉
        mListView_news_list.setMode(PullToRefreshBase.Mode.BOTH);
        mListView_news_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.d(TAG, "onPullDownToRefresh: 下拉刷新了");
                requestData();
                mNewsListAdapter.notifyDataSetChanged();
                //上拉加载和关闭刷新时间太短会导致mListView.onRefreshComplete()无效的情况，只需要延迟一秒即可：
                Log.d(TAG, "onPullDownToRefresh: 下拉刷新结束");


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.d(TAG, "onPullUpToRefresh: 上拉刷新了");
                mStartIndex += 10;

                Log.d(TAG, "mStartIndex: " + mStartIndex);
                mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;

                OkHttpUtils.get().url(mUrl).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onError: getDataFromInternet请求失败" + e);
                        Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
                        mListView_news_list.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        newlist = NewsDataManager.parseJson(response.toString(), "T1467284926140");
                        if (newlist != null){
                            mNewsListNormalBeanList.addAll(newlist);
                        }
                        mListView_news_list.onRefreshComplete();
                        Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public void bindData() {

        mNewsListAdapter = new NewsListAdapter(getActivity(), (ArrayList<NewsListNormalBean>) mNewsListNormalBeanList);
        mListView_news_list.setAdapter(mNewsListAdapter);


    }

    private static String useHttpClient(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = null;
                HttpClient httpCLient = new DefaultHttpClient();

                // 创建get请求实例
                HttpGet httpget = new HttpGet(url);

                System.out.println("executing request "+httpget.getURI());

                try
                {

                    // 客户端执行get请求 返回响应实体
                    HttpResponse response = httpCLient.execute(httpget);

                    // 服务器响应状态行
                    System.out.println(response.getStatusLine());

                    Header[] heads = response.getAllHeaders();
                    // 打印所有响应头
                    for(Header h:heads){
                        System.out.println(h.getName()+":"+h.getValue());
                    }

                    // 获取响应消息实体
                    HttpEntity entity = response.getEntity();

                    System.out.println("------------------------------------");



                    if(entity != null){
                        str = EntityUtils.toString(entity);
                        //响应内容
                        System.out.println(str);
                        Log.d("NewsListFragment", "HttpClient: 获取到的内容是" + str);
                        System.out.println("----------------------------------------");
                        // 响应内容长度
                        System.out.println("响应内容长度："+entity.getContentLength());
                    }

                } catch (ClientProtocolException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }finally{
                    httpCLient.getConnectionManager().shutdown();
                }
            }
        }).start();

        return null;
    }

    private static String getJson(final String dizhi) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                HttpURLConnection conn = null;
                String json = null;
                StringBuffer sb = null;
                try {
                    url = new URL(dizhi);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = conn.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(inputStream);
                        byte[] b = new byte[1024];
                        int len = 0;
                        sb = new StringBuffer();
                        while ((len = bis.read(b))!= -1){
                            sb.append(new String(b, 0, len));
                        }
                    }
                    json = sb.toString();

//                    mNewsListNormalBeanList = NewsDataManager.parseJson(json.toString(), "T1467284926140");

//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            bindData();
//                            mListView_news_list.onRefreshComplete();
//                            Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Log.d("NewsListFragment", "getJson: 的链接" + json);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    json = e.toString();
                    Log.d("NewsListFragment", "getJson: 出问题了" + json);
                } catch (ProtocolException e) {
                    e.printStackTrace();
                    json = e.toString();
                    Log.d("NewsListFragment", "getJson: 出问题了" + json);
                } catch (IOException e) {
                    e.printStackTrace();
                    json = e.toString();
                    Log.d("NewsListFragment", "getJson: 出问题了" + json);
                } finally {
                    Log.d("NewsListFragment", "getJson: 结束了");
                }
            }
        }).start();
//        return json;
        return null;
    }
}
