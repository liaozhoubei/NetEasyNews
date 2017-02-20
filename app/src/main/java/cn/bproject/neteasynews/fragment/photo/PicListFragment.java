package cn.bproject.neteasynews.fragment.photo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import cn.bproject.neteasynews.activity.PicDetailActivity;
import cn.bproject.neteasynews.adapter.PicListAdapter;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;
import cn.bproject.neteasynews.widget.ClassicRefreshHeaderView;
import cn.bproject.neteasynews.widget.DividerGridItemDecoration;
import cn.bproject.neteasynews.widget.LoadMoreFooterView;
import cn.bproject.neteasynews.widget.LoadingPage;

/**
 * Created by liaozhoubei on 2016/12/29.
 */

public class PicListFragment extends BaseFragment implements DefineView {
    private String tid; // 图片频道id，用于打开新闻详情页
    private String column;  //   图片的分类

    private View mView;

    private final String TAG = PicListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";  //频道id
    private static final String KEY_COLUMN = "COLUMN";
    private static final String SETID = "SETID";  // 图集id
    private List<PicListBean> mPicListBeens;   // 启动时获得的数据
    private List<PicListBean> newlist;   // 上拉刷新后获得的数据
    private int mStartIndex = 0;    // 请求数据的起始参数
    private String mUrl;        // 请求网络的url
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private boolean isPullRefresh;
    private boolean isShowCache = false; // 是否有缓存数据被展示
    private boolean isConnectState = false;  // 判断当前是否在联网刷新, false表示当前没有联网刷新

    // 图片新闻的id，推荐和热点都为0001， 新闻和明星是0031，他们是按照column区分的
    private final String isListView = "0001";   // 使用ListView的标志
    private IRecyclerView mIRecyclerView;
    private PicListAdapter mAdapter;
    private LoadMoreFooterView mLoadMoreFooterView;
    private LoadingPage mLoadingPage;

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
                    newlist = DataParse.PicList(result);
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


    public static PicListFragment newInstance(String tid, String column) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TID, tid);
        bundle.putSerializable(KEY_COLUMN, column);
        PicListFragment fragment = new PicListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pic_list, container, false);

        initView();
        initValidata();
        initListener();
        return mView;
    }


    @Override
    public void initView() {

        mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);
        mIRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
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
            tid = getArguments().getString(KEY_TID);
            column = getArguments().getString(KEY_COLUMN);
        }
        mThreadPool = ThreadManager.getThreadPool();

        mUrl = Api.PictureUrl + tid + column + mStartIndex + Api.endPicture;
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
                    mPicListBeens = DataParse.PicList(cache);
                    if (mPicListBeens != null) {
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

//        http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003//0/20.json
        if (!isConnectState) {
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    mUrl = Api.PictureUrl + tid + column + mStartIndex + Api.endPicture;
                    HttpHelper.get(mUrl, new HttpCallbackListener() {
                        @Override
                        public void onSuccess(String result) {
                            mPicListBeens = DataParse.PicList(result);
                            if (mPicListBeens != null) {
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
        mAdapter = new PicListAdapter(MyApplication.getContext(), (ArrayList<PicListBean>) mPicListBeens);
        mIRecyclerView.setIAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o, View v) {
                String id = mPicListBeens.get(position).getSetid();
                Intent intent = new Intent(getActivity(), PicDetailActivity.class);
                intent.putExtra(KEY_TID, tid);
                intent.putExtra(SETID, id);
                getActivity().startActivity(intent);
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
                if (!isConnectState) {
                    DownToRefresh();
                }
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mAdapter.getItemCount() > 0) {
                    if (!isConnectState) {
                        mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                        PullUpToRefresh();
                    }
                }

            }
        });
    }

    // 下拉刷新
    public void DownToRefresh() {
        isConnectState = true;
        LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");
        // 图片模块下拉刷新返回的是当前数据
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                mUrl = Api.PictureUrl + tid + column + 0 + Api.endPicture;
                HttpHelper.get(mUrl, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        isPullRefresh = true;
                        // 无法刷新到新内容
                        Message message = mHandler.obtainMessage();
                        message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                        message.obj = result;
                        mHandler.sendMessage(message);
                        saveCache(mUrl, result);
                    }

                    @Override
                    public void onError(Exception e) {
                        sendErrorMessage(HANDLER_SHOW_REFRESH_LOADMORE_ERRO, e.toString());
                    }
                });
            }
        });
    }

    // 上拉刷新
    public void PullUpToRefresh() {
        isConnectState = true;
        mStartIndex += 21;
        LogUtils.d(TAG, "mStartIndex: " + mStartIndex);
//                mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                mUrl = Api.PictureUrl + tid + column + mStartIndex + Api.endPicture;
                HttpHelper.get(mUrl, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        isPullRefresh = false;
                        Message message = mHandler.obtainMessage();
                        message.what = HANDLER_SHOW_REFRESH_LOADMORE;
                        message.obj = result;
                        mHandler.sendMessage(message);

                    }

                    @Override
                    public void onError(Exception e) {
                        sendErrorMessage(HANDLER_SHOW_REFRESH_LOADMORE_ERRO, e.toString());
                    }
                });
            }
        });
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
        mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        mIRecyclerView.setRefreshing(false);
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {

            // 是下拉刷新
            newlist.addAll(mPicListBeens);
            mPicListBeens.removeAll(mPicListBeens);
            mPicListBeens.addAll(newlist);
            saveUpdateTime(tid, System.currentTimeMillis());
        } else {
            // 上拉刷新
            mPicListBeens.addAll(newlist);
        }
        mAdapter.notifyDataSetChanged();
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
