package com.example.channelmanager;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by goach on 2016/9/28.
 * 我的频道控件
 */

public class MyChannelWidget implements IChannelType {
    private RecyclerView mRecyclerView;
    private EditModeHandler editModeHandler;

    public MyChannelWidget(EditModeHandler editModeHandler) {
        this.editModeHandler = editModeHandler;
    }

    @Override
    public ChannelAdapter.ChannelViewHolder createViewHolder(LayoutInflater mInflater, ViewGroup parent) {
        mRecyclerView = (RecyclerView) parent;
        return new MyChannelHeaderViewHolder(mInflater.inflate(R.layout.activity_channel_my, parent, false));
    }

    @Override
    public void bindViewHolder(final ChannelAdapter.ChannelViewHolder holder, final int position, final ChannelBean data) {
        final MyChannelHeaderViewHolder myHolder = (MyChannelHeaderViewHolder) holder;
        myHolder.mChannelTitleTv.setText(data.getTabName());
        // 设置文字大小，通过判断tab中的文字长度，如果有4或者4个字以上则为16sp大小
        int textSize = data.getTabName().length() >= 4 ? 14 : 16;
        myHolder.mChannelTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        // 设置文字背景颜色
        myHolder.mChannelTitleTv.setBackgroundResource(data.getTabType() == 0 || data.getTabType() == 1 ?
                R.drawable.channel_fixed_bg_shape : R.drawable.channel_my_bg_shape);
        // 根据tab状态设置文字颜色
        myHolder.mChannelTitleTv.setTextColor(data.getTabType() == 0 ? Color.RED :
                data.getTabType() == 1 ? Color.parseColor("#666666") : Color.parseColor("#333333"));
        // 设置右上角删除按钮是否可见
        myHolder.mDeleteIv.setVisibility(data.getEditStatus() == 1 ? View.VISIBLE : View.INVISIBLE);
        myHolder.mChannelTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editModeHandler != null && data.getTabType() == 2) {
                    editModeHandler.clickMyChannel(mRecyclerView, holder);
                }
            }
        });
        myHolder.mChannelTitleTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (editModeHandler != null && data.getTabType() == 2) {
                    editModeHandler.touchMyChannel(motionEvent, holder);
                }
                return false;
            }
        });
        myHolder.mChannelTitleTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (editModeHandler != null && data.getTabType() == 2) {
                    editModeHandler.clickLongMyChannel(mRecyclerView, holder);
                }
                return true;
            }
        });
    }

    public class MyChannelHeaderViewHolder extends ChannelAdapter.ChannelViewHolder {
        private TextView mChannelTitleTv;
        private ImageView mDeleteIv;

        private MyChannelHeaderViewHolder(View itemView) {
            super(itemView);
            mChannelTitleTv = (TextView) itemView.findViewById(R.id.id_channel_title);
            mDeleteIv = (ImageView) itemView.findViewById(R.id.id_delete_icon);
        }
    }
}
