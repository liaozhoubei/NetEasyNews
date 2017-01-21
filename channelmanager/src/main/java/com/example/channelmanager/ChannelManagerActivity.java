package com.example.channelmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChannelManagerActivity extends AppCompatActivity  implements ChannelAdapter.ChannelItemClickListener {
    private RecyclerView mRecyclerView;
    private ChannelAdapter mRecyclerAdapter;
    private String[] myStrs = new String[]{"热门", "关注", "技术", "科技", "商业", "互联网", "涨知识", "时尚"};
    private String[] recStrs = new String[]{"设计", "天文", "美食", "星座", "历史", "消费维权", "体育", "明星八卦"};
    private List<ChannelBean> mMyChannelList;
    private List<ChannelBean> mRecChannelList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_manager);
        context = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.id_tab_recycler_view);
        GridLayoutManager gridLayout = new GridLayoutManager(context, 4);
        gridLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                boolean isHeader = mRecyclerAdapter.getItemViewType(position) == IChannelType.TYPE_MY_CHANNEL_HEADER ||
                        mRecyclerAdapter.getItemViewType(position) == IChannelType.TYPE_REC_CHANNEL_HEADER;
                return isHeader ? 4 : 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayout);
        mRecyclerView.addItemDecoration(new GridItemDecoration(APPConst.ITEM_SPACE));
        initData();
        mRecyclerAdapter = new ChannelAdapter(context, mRecyclerView, mMyChannelList, mRecChannelList, 1, 1);
        mRecyclerAdapter.setChannelItemClickListener(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void initData() {
        mMyChannelList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ChannelBean channelBean = new ChannelBean();
            channelBean.setTabName(myStrs[i]);
            channelBean.setTabType(i == 0 ? 0 : i == 1 ? 1 : 2);
            mMyChannelList.add(channelBean);
        }
        mRecChannelList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ChannelBean channelBean = new ChannelBean();
            channelBean.setTabName(recStrs[i]);
            channelBean.setTabType(2);
            mRecChannelList.add(channelBean);
        }
    }

    @Override
    public void onChannelItemClick(List<ChannelBean> list, int position) {

    }
}
