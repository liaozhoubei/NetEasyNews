package cn.bproject.neteasynews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.widget.LoadingPage;

/**
 * Created by Bei on 2016/12/25.
 * 测试空网络链接
 */

public class NewsListFragment2 extends BaseFragment{

    private final String TAG = NewsFragment.class.getSimpleName();
    
    @Override
    public View onCreateSuccessView() {
        Log.d(TAG, "onCreateSuccessView: NewsListFragment创建了");
        TextView textView = new TextView(getActivity());
        textView.setText("NewsListFragment");
        return textView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        Log.d(TAG, "onLoad: NewsListFragment2重新加载了");
        return check(null);
    }
}
