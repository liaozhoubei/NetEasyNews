package com.example.channelmanager;

/**
 * Created by 钟光新 on 2016/10/16 0016.
 */

public class APPConst {
    private APPConst(){}

    // 设置ChannelManager频道管理中的每个item的间隔
    public static final int ITEM_SPACE = 5;

    // 0和1均表示ChannelManager频道管理中的tab不可可编辑
    // 其中tab的type为0时，字体会显示红色， 为1时会显示灰色
    public static final int ITEM_DEFAULT = 0;
    // 1均表示ChannelManager频道管理中的tab不可可编辑
    public static final int ITEM_UNEDIT = 1;
    // 表示ChannelManager频道管理中的tab可编辑
    public static final int ITEM_EDIT = 2;
}
