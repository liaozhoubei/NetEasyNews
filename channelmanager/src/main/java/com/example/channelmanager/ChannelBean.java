package com.example.channelmanager;

/**
 * Created by goach on 2016/9/28.
 */

public class ChannelBean {
    // 设置该标签是否可编辑，如果出现在我的频道中，且值为1，则可在右上角显示删除按钮
    private int editStatus;

    private String tabName;
    // 标签类型，显示是我的频道还是更多频道
    private int tabType;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }

    public int getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(int editStatus) {
        this.editStatus = editStatus;
    }
}
