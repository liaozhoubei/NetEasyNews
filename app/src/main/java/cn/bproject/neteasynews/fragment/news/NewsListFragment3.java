package cn.bproject.neteasynews.fragment.news;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.fragment.NewsFragment;
import cn.bproject.neteasynews.widget.LoadingPage;

/**
 * Created by Bei on 2016/12/25.
 * 测试错误网页
 */

public class NewsListFragment3 extends BaseFragment {

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
        Log.d(TAG, "onLoad: NewsListFragment3重新加载了");
        return check("123");
    }
}
