package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import cn.bproject.neteasynews.Utils.ToastUtils;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.activity.VideoDetailActivity;
import cn.bproject.neteasynews.adapter.VideoListAdapter;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;
import cn.bproject.neteasynews.widget.ClassicRefreshHeaderView;
import cn.bproject.neteasynews.widget.DividerGridItemDecoration;
import cn.bproject.neteasynews.widget.LoadMoreFooterView;
import cn.bproject.neteasynews.widget.LoadingPage;

import static cn.bproject.neteasynews.R.id.iRecyclerView;

/**
 * Created by Administrator on 2016/12/24.
 * 视频模块
 */

public class VideoFragment extends BaseFragment {
    private final String TAG = VideoFragment.class.getSimpleName();

    private View mView;
    private ArrayList<VideoBean> mVideoBeanList;
    private IRecyclerView mIRecyclerView;
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private int mStartIndex = 10;
    private boolean isPullRefresh;
    private List<VideoBean> newlist;   // 上拉刷新后获得的数据
    private final String VID = "VID";
    private final String MP4URL = "MP4URL";
    private LoadMoreFooterView mLoadMoreFooterView;
    private VideoListAdapter mVideoListAdapter;
    private LoadingPage mLoadingPage;
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
                    newlist = DataParse.VideoList(result);
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
    private String mUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video, null);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void initView() {

        Toolbar myToolbar = initToolbar(mView, R.id.my_toolbar, R.id.toolbar_title, R.string.video_home);
        mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(iRecyclerView);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mIRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
        // 创建线程池
        mThreadPool = UIUtils.getThreadPool();
        getNewsFromCache();
    }

    /**
     * 从缓存中读取并解析显示数据
     */
    private void getNewsFromCache() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mUrl = Api.host + Api.SpecialColumn2 + Api.specialVideoId + Api.SpecialendUrl + mStartIndex + Api.devId;
                String cache = LocalCacheUtils.getLocalCache(mUrl);
                if (!TextUtils.isEmpty(cache)) {
                    mVideoBeanList = DataParse.VideoList(cache);
                    if (mVideoBeanList != null) {
                        isShowCache = true;
                        LogUtils.d(TAG, "读取缓存成功");
                        Message message = mHandler.obtainMessage();
                        message.what = HANDLER_SHOW_NEWS;
                        mHandler.sendMessage(message);
                    } else {
                        isShowCache = false;
                    }
                }
                if (!isLastNews(Api.specialVideoId) || TextUtils.isEmpty(cache)) {
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
        if (!isConnectState) {
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    isConnectState = true;
                    mUrl = Api.host + Api.SpecialColumn2 + "T1457068979049" + "&subtab=Video_Recom" +Api.SpecialendUrl + mStartIndex + Api.devId;
                    //        http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&size=10&offset=0&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            mVideoBeanList = DataParse.VideoList(result);
                            if (mVideoBeanList != null) {

                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_NEWS;
                                mHandler.sendMessage(message);
                                saveCache(mUrl, result);
                                saveUpdateTime(Api.specialVideoId, System.currentTimeMillis());
                            } else {
                                showEmptyPage();
                            }
                            isConnectState = false;
                        }

                        @Override
                        public void onError(Exception e) {
                            LogUtils.e(TAG, e.toString());
                            sendErrorMessage(HANDLER_SHOW_ERROR, e.toString());
                            isConnectState = false;
                        }
                    });

                }
            });
        }

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
                if (mLoadMoreFooterView.canLoadMore() && mVideoListAdapter.getItemCount() > 0) {
                    PullUpToRefresh();
                }
            }
        });

    }

    // 下拉刷新
    public void DownToRefresh() {
        if (!isConnectState) {
            isConnectState = true;
            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId;
                    HttpHelper.get(url, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            LogUtils.d(TAG, "下拉刷新onSuccess: " + result);
                            isPullRefresh = true;
                            if (result != null) {
                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                                message.obj = result;
                                mHandler.sendMessage(message);
                                saveUpdateTime(Api.SpecialColumn2, System.currentTimeMillis());
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            LogUtils.e(TAG, e.toString());
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
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    mUrl = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            isPullRefresh = false;
                            if (result != null) {
                                Message message = mHandler.obtainMessage();
                                message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                                message.obj = result;
                                mHandler.sendMessage(message);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            LogUtils.e(TAG, e.toString());
                            sendErrorMessage(HANDLER_SHOW_REFRESH_LOADMORE_ERRO, e.toString());
                        }
                    });
                }
            });
        }
    }


    @Override
    public void bindData() {
        mVideoListAdapter = new VideoListAdapter(getActivity(), mVideoBeanList);
        mIRecyclerView.setIAdapter(mVideoListAdapter);
        mVideoListAdapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o, View v) {
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                intent.putExtra(VID, mVideoBeanList.get(position).getVid());
                intent.putExtra(MP4URL, mVideoBeanList.get(position).getMp4_url());
                getActivity().startActivity(intent);
            }
        });
    }

    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        isPullRefreshView();
        ToastUtils.showShort("数据已更新");
        // 收起刷新视图
        mIRecyclerView.setRefreshing(false);
        showNewsPage();
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            if (mVideoBeanList != null) {
                // 是下拉刷新
                Log.d(TAG, "isPullRefreshView: " + mVideoBeanList.toString());
                newlist.addAll(mVideoBeanList);
                mVideoBeanList.removeAll(mVideoBeanList);
                mVideoBeanList.clear();
                mVideoBeanList.addAll(newlist);
            }
        } else {
            if (newlist == null) {
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
            } else {
                // 上拉刷新
                mVideoBeanList.addAll(newlist);
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
            }
        }
        mVideoListAdapter.notifyDataSetChanged();
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
