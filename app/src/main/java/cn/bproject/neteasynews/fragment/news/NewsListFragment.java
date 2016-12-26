package cn.bproject.neteasynews.fragment.news;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import cn.bproject.neteasynews.adapter.NewsListAdapter;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.fragment.NewsFragment;
import cn.bproject.neteasynews.widget.LoadingPage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment implements DefineView {

    private final String TAG = NewsFragment.class.getSimpleName();
    private NewsListAdapter mNewsListAdapter;
    
    @Override
    public View onCreateSuccessView() {
        Log.d(TAG, "onCreateSuccessView: NewsListFragment创建了");
        TextView textView = new TextView(getActivity());
        textView.setText("NewsListFragment");
//        ListView listView = new ListView(getActivity());
//        ArrayList<NewsListNormalBean> list = new ArrayList<NewsListNormalBean>();
//        mNewsListAdapter = new NewsListAdapter(getActivity(), list);
        return textView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url("http://c.m.163.com/nc/article/list/T1467284926140/0-20.html");
        builder.method("GET", null);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 没有数据····");
                Log.d(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ss = response.body().string();
                String str = response.networkResponse().toString();
                Log.i("wangshu", "network---" + str);
                Log.d(TAG, "onResponse: " + str + ss);
            }
        });

        return check("hhh");
    }


    @Override
    public void initView() {

    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
