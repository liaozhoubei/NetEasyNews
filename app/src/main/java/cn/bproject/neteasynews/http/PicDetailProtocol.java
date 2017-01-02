package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.ImageDetailBean;
import cn.bproject.neteasynews.common.Api;

import static android.content.ContentValues.TAG;

/**
 * Created by liaozhoubei on 2016/12/28.
 * 新闻详情页链接
 * http://c.m.163.com/nc/article/C9C8QCUV0008856R/full.html
 */

public class PicDetailProtocol extends BaseProtocol<ImageDetailBean> {

    private String docid;

    public PicDetailProtocol(String docid) {
        this.docid = docid;
    }

    @Override
    public String getTid() {
        return docid;
    }

    @Override
    public String getParams() {
        return Api.endDetailUrl;
    }

    @Override
    public String buildURL(String url) {
        return url;
    }




    @Override
    public ImageDetailBean parseData(String result, String tid) {
        ImageDetailBean imageDetailBean = null;
        if (result != null) {
            Gson gson = new Gson();
            imageDetailBean = gson.fromJson(result, ImageDetailBean.class);
            LogUtils.d(TAG, "parseJson: 数据解析成功");
        } else {
            LogUtils.d(TAG, "parseData: 没有数据");
        }
        return imageDetailBean;
    }
}
