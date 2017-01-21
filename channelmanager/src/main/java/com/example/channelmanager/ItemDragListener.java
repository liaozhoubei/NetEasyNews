package com.example.channelmanager;

/**
 * Created by 钟光新 on 2016/9/10 0010.
 */
public interface ItemDragListener {
    void onItemMove(int fromPosition, int toPosition);

    void onItemSwiped(int position);
}
