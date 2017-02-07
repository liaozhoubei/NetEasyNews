package cn.bproject.neteasynews.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.channelmanager.APPConst;
import com.example.channelmanager.adapter.ChannelAdapter;
import com.example.channelmanager.utils.GridItemDecoration;
import com.example.channelmanager.base.IChannelType;
import com.example.channelmanager.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.ListDataSave;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ChannelManagerActivity extends Activity implements ChannelAdapter.ChannelItemClickListener{

    private RecyclerView mRecyclerView;
    private ChannelAdapter mRecyclerAdapter;
    private List<ProjectChannelBean> mMyChannelList;
    private List<ProjectChannelBean> mRecChannelList;
    private Context context;
    private int tabposition;
    private ListDataSave listDataSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_manager);
        getIntentData();
        context = this;
        listDataSave = new ListDataSave(this, "channel");
        mRecyclerView = (RecyclerView) findViewById(com.example.channelmanager.R.id.id_tab_recycler_view);
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
        mRecyclerAdapter.doCancelEditMode(mRecyclerView);
    }

    private void getIntentData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tabposition = bundle.getInt("TABPOSITION");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mMyChannelList = new ArrayList<>();

        List<ProjectChannelBean> list = listDataSave.getDataList("myChannel", ProjectChannelBean.class);
        for (int i = 0; i < list.size(); i ++){
            ProjectChannelBean projectChannelBean = list.get(i);
            if (i == tabposition){
                projectChannelBean.setTabType(APPConst.ITEM_DEFAULT);
            } else {
                // 判断i是否为0或者1,如果为0设置标题为红色（当前浏览的tab标签），如果为1则设置type为1（不可编辑移动），不为1则type为2
                // type为2表示该标签可供编辑移动
                int type;
                if (i == 0  || i == 1){
                    type = 1;
                } else {
                    type = 2;
                }
                projectChannelBean.setTabType(type);
            }
            mMyChannelList.add(projectChannelBean);
        }

        mRecChannelList = new ArrayList<>();
        List<ProjectChannelBean> moreChannelList = listDataSave.getDataList("moreChannel", ProjectChannelBean.class);
        for (ProjectChannelBean projectChannelBean : moreChannelList) {
            mRecChannelList.add(projectChannelBean);
        }
    }

    @Override
    protected void onPause() {
        listDataSave.setDataList("myChannel", mMyChannelList);
        listDataSave.setDataList("moreChannel", mRecChannelList);

        super.onPause();
    }

    @Override
    public void finish() {
        mRecyclerAdapter.doCancelEditMode(mRecyclerView);
        for (int i = 0; i < mMyChannelList.size(); i ++) {
            ProjectChannelBean projectChannelBean = mMyChannelList.get(i);
            if (projectChannelBean.getTabType() == 0){
                tabposition = i;
            }
        }
        Intent intent = new Intent();
        intent.putExtra("NewTabPostion", tabposition);
        setResult(789, intent);

        super.finish();
    }



    @Override
    public void onChannelItemClick(List<ProjectChannelBean> list, int position) {

    }
}
