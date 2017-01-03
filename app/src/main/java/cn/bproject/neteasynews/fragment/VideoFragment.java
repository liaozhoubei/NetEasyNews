package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.VideoDetailActivity;
import cn.bproject.neteasynews.adapter.VideoListAdapter;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.http.VideoProtocol;

/**
 * Created by Administrator on 2016/12/24.
 * 视频模块
 */

public class VideoFragment extends Fragment implements DefineView{
    private final String TAG = VideoFragment.class.getSimpleName();

    private View mView;
    private PullToRefreshListView mListView;
    private ArrayList<VideoBean> mVideoBeanList;
    private VideoListAdapter mVideoListAdapter;
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private VideoProtocol mVideoProtocol;
    private int mStartIndex = 0;
    private boolean isPullRefresh;
    private List<VideoBean> newlist;   // 上拉刷新后获得的数据
    private final String VID = "VID";

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
        mListView = (PullToRefreshListView) mView.findViewById(R.id.listView_news_list);
    }

    @Override
    public void initValidata() {
        // 创建线程池
        mThreadPool = ThreadManager.getThreadPool();
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
                mVideoProtocol = new VideoProtocol();
//                String url = Api.host + Api.SpecialColumn2 + getTid() + Api.SpecialendUrl + getAllParams(params) + getParams();
                mVideoBeanList = mVideoProtocol.getData( Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId);
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
//                        LogUtils.d(TAG, ": 解析id" + tid);
                        if(mVideoBeanList != null){
                            bindData();
                        }

                    }
                });
            }
        });

    }

    @Override
    public void initListener() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        mVideoProtocol = new VideoProtocol();
                        newlist = mVideoProtocol.getData(Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId);
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
//                mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;

                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        mVideoProtocol = new VideoProtocol();
                        newlist = mVideoProtocol.getData(Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId);
                        isPullRefresh = false;
                        DataChange();
                    }
                });
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
//                intent.putExtra(VID, mVideoBeanList.get((int) l).getVid());
                intent.putExtra("VIDEO", mVideoBeanList.get(i));
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void bindData() {
        mVideoListAdapter = new VideoListAdapter(getActivity(), mVideoBeanList);
        mListView.setAdapter(mVideoListAdapter);
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
                mListView.onRefreshComplete();
            }
        });
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh){
            // 是下拉刷新
            newlist.addAll(mVideoBeanList);
            mVideoBeanList.removeAll(mVideoBeanList);
            mVideoBeanList.addAll(newlist);
            mVideoListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mVideoBeanList.addAll(newlist);
            mVideoListAdapter.notifyDataSetChanged();
        }
    }
}
