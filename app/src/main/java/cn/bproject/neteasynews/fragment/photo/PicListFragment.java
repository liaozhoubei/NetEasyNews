package cn.bproject.neteasynews.fragment.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DensityUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
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

    // 图片新闻的id，推荐和热点都为0001， 新闻和明星是0031，他们是按照column区分的
    private final String isListView = "0001";   // 使用ListView的标志
    private FrameLayout mFramelayout_news_list;
    private LinearLayout mLoading;
    private LinearLayout mEmpty;
    private LinearLayout mError;
    private Button mBtn_retry;
    private IRecyclerView mIRecyclerView;
    private PicListAdapter mAdapter;
    private LoadMoreFooterView mLoadMoreFooterView;


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
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);
//        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        // we can set view
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);



//        mIRecyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                mIRecyclerView.setRefreshing(true);
//            }
//        });


        mFramelayout_news_list = (FrameLayout) mView.findViewById(R.id.framelayout_news_list);
        mLoading = (LinearLayout) mView.findViewById(R.id.loading);
        mEmpty = (LinearLayout) mView.findViewById(R.id.empty);
        mError = (LinearLayout) mView.findViewById(R.id.error);
        // 点击重试按键
        mBtn_retry = (Button) mView.findViewById(R.id.btn_retry);
    }

    @Override
    public void initValidata() {
        if (getArguments() != null) {
            //取出保存的频道TID
            tid = getArguments().getString(KEY_TID);
            column = getArguments().getString(KEY_COLUMN);
        }
        showLoadingPage();
        mThreadPool = ThreadManager.getThreadPool();
        requestData();

    }

    public void requestData() {

//        http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003//0/20.json

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                mUrl = Api.PictureUrl + tid + column + mStartIndex + Api.endPicture;
                HttpHelper.get(mUrl, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mPicListBeens = DataParse.PicList(result);
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mPicListBeens != null) {
                                    showNewsPage();
                                    bindData();
                                } else {
                                    showEmptyPage();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(String result, Exception e) {

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
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mIRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIRecyclerView.setRefreshing(false);
                    }
                },2000);
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mAdapter.getItemCount() > 0) {
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                    PullUpToRefresh();
                }

            }
        });


    }

    @Override
    public void bindData() {

        mAdapter = new PicListAdapter(getActivity(), (ArrayList<PicListBean>) mPicListBeens);

        mIRecyclerView.setIAdapter(mAdapter);
        mIRecyclerView.setRefreshing(false);
        LogUtils.d(TAG, ": 解析id:" + tid + ":" + mPicListBeens.toString());

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

    // 下拉刷新
    public void DownToRefresh() {
        LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");
        // 图片模块下拉刷新返回的是当前数据
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                mUrl = Api.PictureUrl + tid + column + 0 + Api.endPicture;
                HttpHelper.get(mUrl, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        newlist = DataParse.PicList(result);
                        isPullRefresh = true;
                        DataChange();
                    }

                    @Override
                    public void onError(String result, Exception e) {

                    }
                });
            }
        });
    }

    // 上拉刷新
    public void PullUpToRefresh() {
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
                        if (TextUtils.isEmpty(result)){
                            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                        } else {
                            newlist = DataParse.PicList(result);

//                            isPullRefresh = true;
                            DataChange();
                        }

                    }

                    @Override
                    public void onError(String result, Exception e) {
                        mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
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
                if (newlist != null) {
                    isPullRefreshView();
                    Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mIRecyclerView.setRefreshing(false);

            }
        });
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

        } else {
            // 上拉刷新
            mPicListBeens.addAll(newlist);

        }
    }




    /**
     * 如果有新闻就展示新闻页面
     */
    private void showNewsPage() {

        mIRecyclerView.setVisibility(View.VISIBLE);
        mFramelayout_news_list.setVisibility(View.GONE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
    }

    /**
     * 展示加载页面
     */
    private void showLoadingPage() {

        mIRecyclerView.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);

    }

    /**
     * 如果没有网络就展示空消息页面
     */
    private void showEmptyPage() {

        mIRecyclerView.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
        mError.setVisibility(View.GONE);

    }

    private void showErroPage() {

        mIRecyclerView.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.VISIBLE);

    }


}
