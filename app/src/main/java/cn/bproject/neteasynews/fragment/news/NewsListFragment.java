package cn.bproject.neteasynews.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DensityUtils;
import cn.bproject.neteasynews.Utils.LocalCacheUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.NetWorkUtil;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.activity.NewsDetailActivity;
import cn.bproject.neteasynews.activity.PicDetailActivity;
import cn.bproject.neteasynews.adapter.NewsListAdapter;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;
import cn.bproject.neteasynews.widget.ClassicRefreshHeaderView;
import cn.bproject.neteasynews.widget.LoadMoreFooterView;
import cn.bproject.neteasynews.widget.LoadingPage;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment {

    private final String TAG = NewsListFragment.class.getSimpleName();
    private static final String KEY = "TID";
    private String mUrl;        // 请求网络的url
    private String tid; // 栏目频道id

    private View mView;     // 布局视图
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private NewsListAdapter mNewsListAdapter;
    private LoadingPage mLoadingPage;

    private List<NewsListNormalBean> mNewsListNormalBeanList;   // 启动时获得的数据
    private List<NewsListNormalBean> newlist;   // 上拉刷新后获得的数据

    private int mStartIndex = 0;    // 请求数据的起始参数
    public ThreadManager.ThreadPool mThreadPool; // 线程池
    private boolean isPullRefresh;
    private boolean isShowCache = false; // 是否有缓存数据被展示

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int what = message.what;
            String result;
            String error;
            switch (what) {
                case HANDLER_SHOW_NEWS:
                    bindData();
                    showNewsPage();
                    break;
                case HANDLER_SHOW_ERROR:
                    error = (String) message.obj;
                    if (!TextUtils.isEmpty(error) && getActivity() == null) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                    // 如果有缓存内容就不展示错误页面
                    if (!isShowCache) {
                        showErroPage();
                    }
                    break;
                case HANDLER_SHOW_REFRESH_LOADMORE:
                    result = (String) message.obj;
                    newlist = DataParse.NewsList(result, tid);
                    DataChange();
                    break;
                case HANDLER_SHOW_REFRESH_LOADMORE_ERRO:
                    error = (String) message.obj;
                    if (!TextUtils.isEmpty(error) && getActivity() == null) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                    mIRecyclerView.setRefreshing(false);
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                    break;
            }
            return false;
        }
    });


    /**
     * 从外部往Fragment中传参数的方法
     *
     * @param tid 频道id
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

        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();
        initListener();
        LogUtils.d(TAG, "调用了onCreateView" + tid);
        return mView;
    }


    @Override
    public void initView() {
        mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);

        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        // we can set view
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
        showLoadingPage();
    }

    @Override
    public void initValidata() {
        if (getArguments() != null) {
            //取出保存的频道TID
            tid = getArguments().getString("TID");
        }
//        newlist = new ArrayList<NewsListNormalBean>();
        // 创建线程池
        mThreadPool = ThreadManager.getThreadPool();

        mUrl = Api.CommonUrl + tid + "/" + mStartIndex + Api.endUrl;

        getNewsFromCache();
    }

    /**
     * 从缓存中读取并解析显示数据
     */
    private void getNewsFromCache() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String cache = LocalCacheUtils.getLocalCache(mUrl);
                if (!TextUtils.isEmpty(cache)) {
                    mNewsListNormalBeanList = DataParse.NewsList(cache, tid);
                    if (mNewsListNormalBeanList != null) {
                        LogUtils.d(TAG, "读取缓存成功");
                        isShowCache = true;
                        Message message = mHandler.obtainMessage();
                        message.what = HANDLER_SHOW_NEWS;
                        mHandler.sendMessage(message);
                    } else {
                        isShowCache = false;
                    }
                }
                if (NetWorkUtil.isNetworkConnected(getActivity())) {
                    // 有网络的情况下请求网络数据
                    requestData();
                } else {
                    sendErrorMessage(HANDLER_SHOW_ERROR, "没有网络");
                }
            }
        });
    }

    public void requestData() {

//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                HttpHelper.get(mUrl, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mNewsListNormalBeanList = DataParse.NewsList(result, tid);

                        if (mNewsListNormalBeanList != null) {
                            Message message = mHandler.obtainMessage();
                            message.what = HANDLER_SHOW_NEWS;
                            mHandler.sendMessage(message);
                            saveCache(mUrl, result);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        // 展示错误页面并尝试重新发出请求
                        LogUtils.e(TAG, "requestData" + e.toString());
                        sendErrorMessage(HANDLER_SHOW_ERROR, e.toString());
                    }
                });

            }
        });

    }


    @Override
    public void initListener() {

        mIRecyclerView.setLoadMoreEnabled(true);
        mIRecyclerView.setRefreshEnabled(true);

        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUrl = Api.CommonUrl + tid + "/" + 0 + Api.endUrl;
                isPullRefresh = true;
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpHelper.get(mUrl, new HttpCallbackListener() {
                            @Override
                            public void onSuccess(String result) {
                                isPullRefresh = true;
                                // 无法刷新到新内容
                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                                message.obj = result;
                                mHandler.sendMessage(message);
                            }

                            @Override
                            public void onError(Exception e) {
                                LogUtils.e(TAG, "requestData" + e.toString());
                                sendErrorMessage(HANDLER_SHOW_REFRESH_LOADMORE_ERRO, e.toString());
                            }
                        });
                    }
                });
            }
        });

        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mNewsListAdapter.getItemCount() > 0) {
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                    isPullRefresh = false;
                    mStartIndex += 20;
                    mUrl = Api.CommonUrl + tid + "/" + mStartIndex + Api.endUrl;
                    mThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpHelper.get(mUrl, new HttpCallbackListener() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.d(TAG, "setOnLoadMoreListener: " + result);
                                    isPullRefresh = false;
                                    saveCache(mUrl, result);
                                    Message message = mHandler.obtainMessage();
                                    message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                                    message.obj = result;
                                    mHandler.sendMessage(message);
                                }

                                @Override
                                public void onError(Exception e) {
                                    LogUtils.e(TAG, "requestData" + e.toString());
                                    sendErrorMessage(HANDLER_SHOW_REFRESH_LOADMORE_ERRO, e.toString());
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void sendErrorMessage(int what, String e) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = e;
        mHandler.sendMessage(message);
    }

    @Override
    public void bindData() {
        if (mNewsListAdapter == null) {

            mNewsListAdapter = new NewsListAdapter(getActivity(), (ArrayList<NewsListNormalBean>) mNewsListNormalBeanList);
            mIRecyclerView.setIAdapter(mNewsListAdapter);
            // 设置Item点击跳转事件
            mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get(position);
                    String photosetID = newsListNormalBean.getPhotosetID();
                    Intent intent;
                    if (photosetID != null) {
                        intent = new Intent(getActivity(), PicDetailActivity.class);
                        String[] str = photosetID.split("\\|");
                        //  图片新闻文章所属的类目id
                        String tid = str[0].substring(4);
                        // 图片新闻的文章id号
                        String setid = str[1];
                        intent.putExtra("TID", tid);
                        intent.putExtra("SETID", setid);
                        LogUtils.d(TAG, "onItemClick: photosetID:" + photosetID);
                    } else {
                        intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra("DOCID", newsListNormalBean.getDocid());

                    }
                    //论坛、读书、漫画、态度公开课、云课堂 等栏目进入新闻详情页未处理
                    getActivity().startActivity(intent);
                }
            });
        } else {
            mNewsListAdapter.notifyDataSetChanged();
            mIRecyclerView.setIAdapter(mNewsListAdapter);
        }

    }


    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        if (newlist != null) {
            isPullRefreshView();
            if (getActivity() != null) {
                Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (getActivity() != null) {
                Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
            }
            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        }
        mIRecyclerView.setRefreshing(false);
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的数据加载方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            // 是下拉刷新，目前无法刷新到新数据
//            newlist.addAll(mNewsListNormalBeanList);
//            mNewsListNormalBeanList.removeAll(mNewsListNormalBeanList);
//            mNewsListNormalBeanList.addAll(newlist);
//            mNewsListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mNewsListNormalBeanList.addAll(newlist);
        }
        mNewsListAdapter.notifyDataSetChanged();
    }

    /**
     * 如果有新闻就展示新闻页面
     */
    private void showNewsPage() {
        mIRecyclerView.setVisibility(View.VISIBLE);
        mLoadingPage.setSuccessView();

    }

    /**
     * 展示加载页面
     */
    private void showLoadingPage() {
        mIRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingPage.setLoadingView();
    }

    /**
     * 如果没有网络就展示空消息页面
     */
    private void showEmptyPage() {
        mIRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingPage.setEmptyView();
    }

    private void showErroPage() {
        mIRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingPage.setErrorView();
        mLoadingPage.setLoadingClickListener(new LoadingPage.LoadingClickListener() {
            @Override
            public void clickListener() {
                requestData();
            }
        });
    }

}
