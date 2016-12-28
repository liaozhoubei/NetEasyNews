package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.NewsDetailBean;
import cn.bproject.neteasynews.common.Api;

import static android.content.ContentValues.TAG;

/**
 * Created by liaozhoubei on 2016/12/28.
 * 新闻详情页链接
 * http://c.m.163.com/nc/article/C9C8QCUV0008856R/full.html
 */

public class NewsDetailProtocol extends BaseProtocol<NewsDetailBean>{

    private String docid;

    public NewsDetailProtocol(String docid) {
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
    public String buildURL(int index) {
        String url = Api.DetailUrl + getTid() + Api.endDetailUrl;
        return url;
    }

    @Override
    public NewsDetailBean parseData(String result, String tid) {
        if (result != null) {
            JSONObject jsonObject = null;
            NewsDetailBean newsDetailBean;
            try {
                jsonObject = new JSONObject(result);
                String detail = jsonObject.getJSONObject(tid).toString();
                Gson gson = new Gson();
                newsDetailBean = gson.fromJson(detail, NewsDetailBean.class);
                return newsDetailBean;
            } catch (JSONException e) {
                e.printStackTrace();
                LogUtils.d(TAG, "parseJson: 数据解析错误");
            }
        } else {
            LogUtils.d(TAG, "parseData: 没有数据");
        }
        return null;
    }
}
