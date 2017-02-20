package cn.bproject.neteasynews.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.MyApplication;
import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DensityUtils;
import cn.bproject.neteasynews.Utils.LocalCacheUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.NetWorkUtil;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.ToastUtils;
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
import cn.bproject.neteasynews.widget.DividerGridItemDecoration;
import cn.bproject.neteasynews.widget.LoadMoreFooterView;
import cn.bproject.neteasynews.widget.LoadingPage;

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
    private boolean isPullRefresh;  // 判断当前是下拉刷新还是上拉刷新
    private boolean isShowCache = false; // 是否有缓存数据被展示

    private boolean isConnectState = false;  // 判断当前是否在联网刷新, false表示当前没有联网刷新

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
                    ToastUtils.showShort(error);
                    // 如果有缓存内容就不展示错误页面
                    if (!isShowCache) {
                        showErroPage();
                    }
                    break;
                case HANDLER_SHOW_REFRESH_LOADMORE:
                    result = (String) message.obj;
                    newlist = DataParse.NewsList(result, tid);
                    DataChange();
                    isConnectState = false;
                    break;
                case HANDLER_SHOW_REFRESH_LOADMORE_ERRO:
                    error = (String) message.obj;
                    ToastUtils.showShort(error);
                    mIRecyclerView.setRefreshing(false);
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                    isConnectState = false;
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
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
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
                if (!isLastNews(tid) || TextUtils.isEmpty(cache)) {
                    // 先判断当前缓存时间是否超过3个小时，超过则联网刷新
                    if (NetWorkUtil.isNetworkConnected(getActivity())) {
                        // 有网络的情况下请求网络数据
                        requestData();
                    } else {
                        sendErrorMessage(HANDLER_SHOW_ERROR, "没有网络");
                    }
                }

            }
        });
    }

    public void requestData() {

//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html
        if (!isConnectState) {
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    isConnectState = true;
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            mNewsListNormalBeanList = DataParse.NewsList(result, tid);

                            if (mNewsListNormalBeanList != null) {
                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_NEWS;
                                mHandler.sendMessage(message);
                                saveUpdateTime(tid, System.currentTimeMillis());
                                saveCache(mUrl, result);
                            }
                            isConnectState = false;
                        }

                        @Override
                        public void onError(Exception e) {
                            // 展示错误页面并尝试重新发出请求
                            LogUtils.e(TAG, "requestData" + e.toString());
                            sendErrorMessage(HANDLER_SHOW_ERROR, e.toString());
                            isConnectState = false;
                        }
                    });
                }
            });
        }

    }


    @Override
    public void bindData() {
        mNewsListAdapter = new NewsListAdapter(MyApplication.getContext(), (ArrayList<NewsListNormalBean>) mNewsListNormalBeanList);
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

    }

    @Override
    public void initListener() {

        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                DownToRefresh();
            }
        });

        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mNewsListAdapter.getItemCount() > 0) {
                    PullUpToRefresh();
                }
            }
        });
    }

    // 下拉刷新
    public void DownToRefresh() {
        if (!isConnectState) {
            mUrl = Api.CommonUrl + tid + "/" + 0 + Api.endUrl;
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    isConnectState = true;
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            if (result != null) {
                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                                message.obj = result;
                                mHandler.sendMessage(message);
                                saveUpdateTime(tid, System.currentTimeMillis());
                                saveCache(mUrl, result);
                            }
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

    // 上拉刷新
    public void PullUpToRefresh() {
        if (!isConnectState) {
            isConnectState = true;
            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            mStartIndex += 20;
            mUrl = Api.CommonUrl + tid + "/" + mStartIndex + Api.endUrl;
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            LogUtils.d(TAG, "setOnLoadMoreListener: " + result);
                            isPullRefresh = false;
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


    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        if (newlist != null) {
            isPullRefreshView();
            ToastUtils.showShort("数据已更新");
        } else {
            ToastUtils.showShort("数据请求失败");
        }
        mIRecyclerView.setRefreshing(false);
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的数据加载方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            // 是下拉刷新，目前无法刷新到新数据
            newlist.addAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.removeAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.addAll(newlist);
            mNewsListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mNewsListNormalBeanList.addAll(newlist);
            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        }
        mNewsListAdapter.notifyDataSetChanged();
    }


    public void sendErrorMessage(int what, String e) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = e;
        mHandler.sendMessage(message);
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
