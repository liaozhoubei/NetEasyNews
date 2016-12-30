package cn.bproject.neteasynews.fragment.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.adapter.PicListAdapter;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.BaseProtocol;
import cn.bproject.neteasynews.http.PicProtocol;

/**
 * Created by liaozhoubei on 2016/12/29.
 */

public class PicListFragment  extends BaseFragment implements DefineView {
    private String tid; // 图片频道id，用于打开新闻详情页
    private String column;  //   图片的分类
    private View mView;
    private PullToRefreshListView mPullToRefreshListView;// 可上拉下拉刷新的listView

    private final String TAG = PicListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";
    private static final String KEY_COLUMN = "COLUMN";
    private PicListAdapter mPicListAdapter;   // ListView的Adapter
    private List<PicListBean> mPicListBeen;   // 启动时获得的数据
    private List<PicListBean> newlist;   // 上拉刷新后获得的数据
    private int mStartIndex = 0;    // 请求数据的起始参数
    private String mUrl;        // 请求网络的url
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private boolean isPullRefresh;
    private PicProtocol mPicProtocol;


    public static PicListFragment newInstance(String tid, String column){
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
        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        mPullToRefreshListView = (PullToRefreshListView) mView.findViewById(R.id.listView_news_list);


    }

    @Override
    public void initValidata() {
        mThreadPool = ThreadManager.getThreadPool();
        requestData();
        if(getArguments()!=null){
            //取出保存的频道TID
            tid = getArguments().getString(KEY_TID);
            column = getArguments().getString(KEY_COLUMN);
        }
    }

    public void requestData() {

//        http://pic.news.163.com/photocenter/api/list/0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003//0/20.json

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mPicProtocol = new PicProtocol(tid);
                mPicListBeen = mPicProtocol.getData(BaseProtocol.PIC_TYPE, column, mStartIndex);
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.d(TAG, ": 解析id" + tid);
                        if(mPicListBeen != null){
                            bindData();
                        }

                    }
                });
            }
        });

    }


    @Override
    public void initListener() {


    }

    @Override
    public void bindData() {
        mPicListAdapter = new PicListAdapter(getActivity(), (ArrayList<PicListBean>) mPicListBeen);
        mPullToRefreshListView.setAdapter(mPicListAdapter);
    }

}
