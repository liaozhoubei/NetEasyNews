package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DensityUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
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
    //    private PullToRefreshListView mListView;
    private ArrayList<VideoBean> mVideoBeanList;
    //    private VideoListAdapter mVideoListAdapter;
    private IRecyclerView mIRecyclerView;
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private int mStartIndex = 0;
    private boolean isPullRefresh;
    private List<VideoBean> newlist;   // 上拉刷新后获得的数据
    private final String VID = "VID";
    private LoadMoreFooterView mLoadMoreFooterView;
    private VideoListAdapter mVideoListAdapter;
    private LoadingPage mLoadingPage;


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
        requestData();
    }

    public void requestData() {
//        mUrl = Api.CommonUrl + Api.toutiaoId + "/" + mStartIndex + Api.endUrl;
//        Log.d(TAG, "mUrl地址为: " + mUrl);
//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mVideoBeanList = DataParse.VideoList(result);
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mVideoBeanList != null) {
                                    bindData();
                                    showNewsPage();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        });

    }

    @Override
    public void initListener() {
//        mIRecyclerView.setLoadMoreEnabled(true);
//        mIRecyclerView.setRefreshEnabled(true);
        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId;
                        HttpHelper.get(url, new HttpCallbackListener() {
                            @Override
                            public void onSuccess(String result) {
                                Log.d(TAG, "下拉刷新onSuccess: " + result);
                                isPullRefresh = true;
                                newlist = DataParse.VideoList(result);
                                DataChange();
                            }

                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                                mIRecyclerView.setRefreshing(false);
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mVideoListAdapter.getItemCount() > 0) {
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);

                    mStartIndex += 20;
                    mThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
                            HttpHelper.get(url, new HttpCallbackListener() {
                                @Override
                                public void onSuccess(String result) {
                                    isPullRefresh = false;
                                    newlist = DataParse.VideoList(result);
                                    DataChange();
                                }

                                @Override
                                public void onError(Exception e) {
                                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                    showErroPage();
                                }
                            });
                        }
                    });
                }
            }
        });

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
//                intent.putExtra("VIDEO", mVideoBeanList.get(i));
                getActivity().startActivity(intent);
            }
        });

    }

    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
//                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                isPullRefreshView();
                Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
                // 收起刷新视图
                mIRecyclerView.setRefreshing(false);
                showNewsPage();
            }
        });
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            if (mVideoBeanList != null){
                // 是下拉刷新
                Log.d(TAG, "isPullRefreshView: " +  mVideoBeanList.toString());
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
