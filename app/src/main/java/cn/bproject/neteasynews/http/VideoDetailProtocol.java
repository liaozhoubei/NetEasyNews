package cn.bproject.neteasynews.http;

import android.util.Log;

import com.google.gson.Gson;

import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;

/**
 * Created by Bei on 2016/12/29.
 */

public class VideoDetailProtocol extends BaseProtocol<VideoBean> {
    private final String TAG = VideoDetailProtocol.class.getSimpleName();

    @Override
    public String getTid() {
        return "";
    }

    @Override
    public String getParams() {
//       http://c.m.163.com/nc/video/detail/VC8TVUN5N.html

        return Api.devId;
    }

    @Override
    public String buildURL(String url) {
        return url;
    }

    @Override
    public VideoBean parseData(String result, String tid) {
        VideoBean bean = null;
        if (result != null) {
            Gson gson = new Gson();
            bean = gson.fromJson(result, VideoBean.class);
            Log.d(TAG, "parseData: " + bean);
        }
        return bean;
    }


}
