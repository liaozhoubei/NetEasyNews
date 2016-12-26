package cn.bproject.neteasynews.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.adapter.NewsListAdapter;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.datamanager.NewsDataManager;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.fragment.NewsFragment;
import okhttp3.Call;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment implements DefineView {

    private final String TAG = NewsFragment.class.getSimpleName();
    private NewsListAdapter mNewsListAdapter;
    private View mView;
    private ListView mListView_news_list;
    private List<NewsListNormalBean> mNewsListNormalBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        String json = NewsDataManager.getDataFromInternet("http://c.m.163.com/nc/article/list/T1467284926140/0-20.html", "T1467284926140");
//        List<NewsListNormalBean> newsListNormalBeans = NewsDataManager.parseJson(json, "T1467284926140");
        Log.d(TAG, "LoadingPage: "  );
//        TextView textView = new TextView(getActivity());
//        textView.setText("NewsListFragment");
        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();

        return mView;
    }


    @Override
    public void initView() {
        mListView_news_list = (ListView) mView.findViewById(R.id.listView_news_list);
    }

    @Override
    public void initValidata() {
//
        requestData();
    }

    public void requestData(){
        OkHttpUtils.get().url("http://c.m.163.com/nc/article/list/T1467284926140/0-20.html").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d(TAG, "onError: getDataFromInternet请求失败" + e);
            }

            @Override
            public void onResponse(String response, int id) {


//                Log.d(TAG, "onResponse: 数组来了" + json[0]);
                mNewsListNormalBeanList = NewsDataManager.parseJson(response.toString(), "T1467284926140");
                bindData();
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        mNewsListAdapter = new NewsListAdapter(getActivity(), (ArrayList<NewsListNormalBean>) mNewsListNormalBeanList);
        mListView_news_list.setAdapter(mNewsListAdapter);
    }
}
