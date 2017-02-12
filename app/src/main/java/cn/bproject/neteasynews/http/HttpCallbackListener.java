package cn.bproject.neteasynews.http;

/**
 * Created by liaozhoubei on 2017/1/6.
 */

public interface HttpCallbackListener {
    void onSuccess(String result);
    void onError(Exception e);
}
