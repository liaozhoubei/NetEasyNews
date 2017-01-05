package cn.bproject.neteasynews.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.NewsDetailActivity;
import cn.bproject.neteasynews.PicDetailActivity;
import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.adapter.NewsListAdapter;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.NewsProtocol;

import static android.content.Context.WINDOW_SERVICE;
import static cn.bproject.neteasynews.R.id.listView_news_list;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment implements DefineView {

    private final String TAG = NewsListFragment.class.getSimpleName();
    private static final String KEY = "TID";
    private NewsListAdapter mNewsListAdapter;   // ListView的Adapter
    private View mView;     // 布局视图
    private PullToRefreshListView mListView_news_list;      // 可上拉下拉刷新的listView
    private List<NewsListNormalBean> mNewsListNormalBeanList;   // 启动时获得的数据
    private List<NewsListNormalBean> newlist;   // 上拉刷新后获得的数据
    private int mStartIndex = 0;    // 请求数据的起始参数
    private String mUrl;        // 请求网络的url
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private boolean isPullRefresh;
    private NewsProtocol mNewsProtocol;
    private String tid; // 栏目频道id
    private FrameLayout mFramelayout_news_list;
    private LinearLayout mLoading;
    private LinearLayout mEmpty;
    private LinearLayout mError;
    private Button mBtn_retry;


    /**
     * 从外部往Fragment中传参数的方法
     * @param tid   频道id
     * @return
     */
    public static NewsListFragment newInstance(String tid) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, tid);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 获取屏幕的宽度
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
        // windowManager.getDefaultDisplay().getWidth();
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        long width = outMetrics.widthPixels;
        long height = outMetrics.heightPixels;
        LogUtils.d(TAG, "width: " + width + "     height: " + height);

        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
    }


    @Override
    public void initView() {
        mListView_news_list = (PullToRefreshListView) mView.findViewById(listView_news_list);

        mFramelayout_news_list = (FrameLayout) mView.findViewById(R.id.framelayout_news_list);
        mLoading = (LinearLayout) mView.findViewById(R.id.loading);
        mEmpty = (LinearLayout) mView.findViewById(R.id.empty);
        mError = (LinearLayout) mView.findViewById(R.id.error);
        // 点击重试按键
        mBtn_retry = (Button) mView.findViewById(R.id.btn_retry);
    }

    @Override
    public void initValidata() {
        if(getArguments()!=null){
            //取出保存的频道TID
            tid = getArguments().getString("TID");
        }
        showLoadingPage();
        // 创建线程池
        mThreadPool = ThreadManager.getThreadPool();
        requestData();


    }

    public void requestData() {
        mUrl = Api.CommonUrl + tid + "/" + mStartIndex + Api.endUrl;
//        Log.d(TAG, "mUrl地址为: " + mUrl);
//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                CreateNewsProtocol();
                mNewsListNormalBeanList = mNewsProtocol.getData(mUrl);
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.d(TAG, ": 解析id" + tid);
                        if(mNewsListNormalBeanList != null){
                            showNewsPage();
                            bindData();
                        } else {
                            showEmptyPage();
                        }

                    }
                });
            }
        });

    }

    @Override
    public void initListener() {
        // 设置是否支持上拉和下拉
        mListView_news_list.setMode(PullToRefreshBase.Mode.BOTH);
        mListView_news_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");
                mUrl = Api.CommonUrl + tid + "/" + 0 + Api.endUrl;
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        CreateNewsProtocol();
                        newlist = mNewsProtocol.getData(mUrl);
                        isPullRefresh = true;
                        DataChange();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtils.d(TAG, "onPullUpToRefresh: 上拉刷新了");
                mStartIndex += 20;

                LogUtils.d(TAG, "mStartIndex: " + mStartIndex);
                mUrl = Api.CommonUrl + tid + "/" + mStartIndex + Api.endUrl;

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        CreateNewsProtocol();
                        newlist = mNewsProtocol.getData(mUrl);
                        isPullRefresh = false;
                        DataChange();
                    }
                });

            }
        });

        mListView_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get((int) l);
                String photosetID = newsListNormalBean.getPhotosetID();
                Intent intent;
                if (photosetID != null) {
                    intent = new Intent(getActivity(), PicDetailActivity.class);
                    String[] str = photosetID.split("\\|");
                    //  图片新闻文章所属的类目id
                    String tid = str[0].substring(4);
                    // 图片新闻的文章id好
                    String setid = str[1];
                    intent.putExtra("TID", tid);
                    intent.putExtra("SETID", setid);
                    LogUtils.d(TAG, "onItemClick: photosetID:"  + photosetID);
                } else {
                    intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra("DOCID", newsListNormalBean.getDocid());

                }
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void bindData() {
        mNewsListAdapter = new NewsListAdapter(getActivity(), (ArrayList<NewsListNormalBean>) mNewsListNormalBeanList);
        mListView_news_list.setAdapter(mNewsListAdapter);
    }

    private void CreateNewsProtocol(){
        if(mNewsProtocol == null){
            mNewsProtocol = new NewsProtocol(tid);
        }
    }

    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (newlist != null) {
                    isPullRefreshView();
                    Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                //上拉加载和关闭刷新时间太短会导致mListView.onRefreshComplete()无效的情况，只需要延迟一秒即可：
                mListView_news_list.onRefreshComplete();
            }
        });
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh){
            // 是下拉刷新
            newlist.addAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.removeAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.addAll(newlist);
            mNewsListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mNewsListNormalBeanList.addAll(newlist);
            mNewsListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 如果有新闻就展示新闻页面
     */
    private void showNewsPage() {

        mListView_news_list.setVisibility(View.VISIBLE);
        mFramelayout_news_list.setVisibility(View.GONE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
    }

    /**
     * 展示加载页面
     */
    private void showLoadingPage() {
        mListView_news_list.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);

    }

    /**
     * 如果没有网络就展示空消息页面
     */
    private void showEmptyPage() {
        mListView_news_list.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
        mError.setVisibility(View.GONE);

    }

    private void showErroPage() {
        mListView_news_list.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.VISIBLE);

    }

}
