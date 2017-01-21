package com.example.channelmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by goach on 2016/9/28.
 * 推荐分类（文字界面）
 */

public class RecChannelHeaderWidget implements IChannelType {
    @Override
    public ChannelAdapter.ChannelViewHolder createViewHolder(LayoutInflater mInflater, ViewGroup parent) {
        return new MyChannelHeaderViewHolder(mInflater.inflate(R.layout.activity_channel_rec_header, parent, false));
    }

    @Override
    public void bindViewHolder(ChannelAdapter.ChannelViewHolder holder, int position, ChannelBean data) {

    }

    public class MyChannelHeaderViewHolder extends ChannelAdapter.ChannelViewHolder {

        public MyChannelHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
