package cn.bproject.neteasynews.fragment.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshGridView;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.PicDetailActivity;
import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.adapter.PicListAdapter;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.PicProtocol;

/**
 * Created by liaozhoubei on 2016/12/29.
 */

public class PicListFragment extends BaseFragment implements DefineView , AdapterView.OnItemClickListener{
    private String tid; // 图片频道id，用于打开新闻详情页
    private String column;  //   图片的分类

    private View mView;
    private PullToRefreshListView mPullToRefreshListView;// 可上拉下拉刷新的listView

    private final String TAG = PicListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";  //频道id
    private static final String KEY_COLUMN = "COLUMN";
    private static final String SETID = "SETID";  // 图集id
    private PicListAdapter mPicListAdapter;   // ListView的Adapter
    private List<PicListBean> mPicListBeens;   // 启动时获得的数据
    private List<PicListBean> newlist;   // 上拉刷新后获得的数据
    private int mStartIndex = 0;    // 请求数据的起始参数
    private String mUrl;        // 请求网络的url
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private boolean isPullRefresh;
    private PicProtocol mPicProtocol;
    private PullToRefreshGridView mPull_refresh_grid;
    private GridView mGridView;
    // 图片新闻的id，推荐和热点都为0001， 新闻和明星是0031，他们是按照column区分的
    private final String isListView = "0001";   // 使用ListView的标志
    private FrameLayout mFramelayout_news_list;
    private LinearLayout mLoading;
    private LinearLayout mEmpty;
    private LinearLayout mError;
    private Button mBtn_retry;


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
        mPullToRefreshListView = (PullToRefreshListView) mView.findViewById(R.id.listView_news_list);
        mPull_refresh_grid = (PullToRefreshGridView) mView.findViewById(R.id.pull_refresh_grid);
        mGridView = mPull_refresh_grid.getRefreshableView();

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
                mPicProtocol = new PicProtocol(tid);
                // Api.PictureUrl + getTid() + getAllParams(params) +  getParams();
//                mPicListBeens = mPicProtocol.getData(BaseProtocol.PIC_TYPE, column, mStartIndex);
                mPicListBeens = mPicProtocol.getData(Api.PictureUrl + tid + column + mStartIndex + Api.endPicture);
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
        });

    }


    @Override
    public void initListener() {
        // ListView上拉和下拉刷新

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                
                Toast.makeText(getActivity(), "已经是最新数据了！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PullUpToRefresh();
            }
        });
        // GridView上拉和下拉刷新，与ListView逻辑一样
        mPull_refresh_grid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                Toast.makeText(getActivity(), "已经是最新数据了！", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                PullUpToRefresh();
            }
        });

        mPullToRefreshListView.setOnItemClickListener(this);
        mGridView.setOnItemClickListener(this);
//        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                mPicListAdapter.getItem(i);
//            }
//        });
    }

    @Override
    public void bindData() {
        mPicListAdapter = new PicListAdapter(getActivity(), (ArrayList<PicListBean>) mPicListBeens);
        LogUtils.d(TAG, ": 解析id:" + tid + ":" + mPicListBeens.toString());
        if (tid.equals(isListView)) {
            mPullToRefreshListView.setAdapter(mPicListAdapter);
        } else {
            mGridView.setAdapter(mPicListAdapter);
        }
    }

    // 下拉刷新
    public void DownToRefresh(){
        LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");
        // 图片模块下拉刷新返回的是当前数据
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                CreateNewsProtocol();
                newlist = mPicProtocol.getData(Api.PictureUrl + tid + column + 0 + Api.endPicture);
//                newlist = mPicProtocol.getData(BaseProtocol.PIC_TYPE, column, 0);
                isPullRefresh = true;
                DataChange();
            }
        });
    }

    // 上拉刷新
    public void PullUpToRefresh(){
        mStartIndex += 21;

        LogUtils.d(TAG, "mStartIndex: " + mStartIndex);
//                mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                CreateNewsProtocol();
                newlist = mPicProtocol.getData(Api.PictureUrl + tid + column + 0 + Api.endPicture);
//                newlist = mPicProtocol.getData(BaseProtocol.PIC_TYPE, column, mStartIndex);
                isPullRefresh = false;
                DataChange();
            }
        });
    }

    private void CreateNewsProtocol(){
        if(mPicProtocol == null){
            mPicProtocol = new PicProtocol(tid);
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
                if (tid.equals(isListView)) {
                    mPullToRefreshListView.onRefreshComplete();
                } else {
                    mPull_refresh_grid.onRefreshComplete();
                }
            }
        });
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh){
            // 是下拉刷新
            newlist.addAll(mPicListBeens);
            mPicListBeens.removeAll(mPicListBeens);
            mPicListBeens.addAll(newlist);
            mPicListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mPicListBeens.addAll(newlist);
            mPicListAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        PicListBean picListBean = (PicListBean) mPicListAdapter.getItem(i);
        String id = picListBean.getSetid();
//        String imgSum = picListBean.getImgsum();
        Intent intent = new Intent(getActivity(), PicDetailActivity.class);
        intent.putExtra(KEY_TID, tid);
        intent.putExtra(SETID, id);
        getActivity().startActivity(intent);
    }

    /**
     * 如果有新闻就展示新闻页面
     */
    private void showNewsPage() {
        if (tid.equals(isListView)) {
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mPull_refresh_grid.setVisibility(View.GONE);
        } else {
            mPullToRefreshListView.setVisibility(View.GONE);
            mPull_refresh_grid.setVisibility(View.VISIBLE);
        }
        mFramelayout_news_list.setVisibility(View.GONE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
    }

    /**
     * 展示加载页面
     */
    private void showLoadingPage() {
        mPullToRefreshListView.setVisibility(View.GONE);
        mPull_refresh_grid.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);

    }

    /**
     * 如果没有网络就展示空消息页面
     */
    private void showEmptyPage() {
        mPullToRefreshListView.setVisibility(View.GONE);
        mPull_refresh_grid.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
        mError.setVisibility(View.GONE);

    }

    private void showErroPage() {
        mPullToRefreshListView.setVisibility(View.GONE);
        mPull_refresh_grid.setVisibility(View.GONE);
        mFramelayout_news_list.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mError.setVisibility(View.VISIBLE);

    }
}
