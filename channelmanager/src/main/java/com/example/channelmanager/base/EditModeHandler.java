package com.example.channelmanager.base;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.example.channelmanager.adapter.ChannelAdapter;

/**
 * Created by 钟光新 on 2016/10/15 0015.
 */

public abstract class EditModeHandler {
    public void startEditMode(RecyclerView mRecyclerView) {
    }

    public void cancelEditMode(RecyclerView mRecyclerView) {
    }

    public void clickMyChannel(RecyclerView mRecyclerView, ChannelAdapter.ChannelViewHolder holder) {
    }

    public void clickLongMyChannel(RecyclerView mRecyclerView, ChannelAdapter.ChannelViewHolder holder) {
    }

    public void touchMyChannel(MotionEvent motionEvent, ChannelAdapter.ChannelViewHolder holder) {
    }

    public void clickRecChannel(RecyclerView mRecyclerView, ChannelAdapter.ChannelViewHolder holder) {
    }
}
