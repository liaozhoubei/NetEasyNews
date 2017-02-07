package com.example.channelmanager.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.channelmanager.adapter.ChannelAdapter;
import com.example.channelmanager.ProjectChannelBean;


/**
 * Created by goach on 2016/9/28.
 */

public interface IChannelType {
    //我的频道头部部分
    int TYPE_MY_CHANNEL_HEADER = 0;
    //我的频道部分
    int TYPE_MY_CHANNEL = 1;
    //推荐头部部分
    int TYPE_REC_CHANNEL_HEADER = 2;
    //推荐部分
    int TYPE_REC_CHANNEL = 3;

    ChannelAdapter.ChannelViewHolder createViewHolder(LayoutInflater mInflater, ViewGroup parent);

    void bindViewHolder(ChannelAdapter.ChannelViewHolder holder, int position, ProjectChannelBean data);
}
