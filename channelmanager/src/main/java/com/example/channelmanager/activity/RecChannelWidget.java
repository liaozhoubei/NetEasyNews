package com.example.channelmanager.activity;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.channelmanager.ProjectChannelBean;
import com.example.channelmanager.R;
import com.example.channelmanager.adapter.ChannelAdapter;
import com.example.channelmanager.base.EditModeHandler;
import com.example.channelmanager.base.IChannelType;


/**
 * Created by goach on 2016/9/28.
 * 更多频道控件
 */

public class RecChannelWidget implements IChannelType {
    private EditModeHandler editModeHandler;
    private RecyclerView mRecyclerView;

    public RecChannelWidget(EditModeHandler editModeHandler) {
        this.editModeHandler = editModeHandler;
    }

    @Override
    public ChannelAdapter.ChannelViewHolder createViewHolder(LayoutInflater mInflater, ViewGroup parent) {
        this.mRecyclerView = (RecyclerView) parent;
        return new RecChannelHeaderViewHolder(mInflater.inflate(R.layout.activity_channel_rec, parent, false));
    }

    @Override
    public void bindViewHolder(final ChannelAdapter.ChannelViewHolder holder, int position, ProjectChannelBean data) {
        RecChannelHeaderViewHolder recHolder = (RecChannelHeaderViewHolder) holder;
        recHolder.mChannelTitleTv.setText(data.getTname());
        int textSize = data.getTname().length() >= 4 ? 14 : 16;
        recHolder.mChannelTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        recHolder.mChannelTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editModeHandler != null) {
                    editModeHandler.clickRecChannel(mRecyclerView, holder);
                }
            }
        });
    }

    private class RecChannelHeaderViewHolder extends ChannelAdapter.ChannelViewHolder {
        private TextView mChannelTitleTv;

        private RecChannelHeaderViewHolder(View itemView) {
            super(itemView);
            mChannelTitleTv = (TextView) itemView.findViewById(R.id.id_channel_title);
        }
    }
}
