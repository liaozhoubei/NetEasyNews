package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.common.Api;

/**
 * Created by liaozhoubei on 2016/12/30.
 */

public class PicProtocol extends BaseProtocol<ArrayList<PicListBean>> {

    private String tid;

    public PicProtocol(String tid) {
        this.tid = tid;
    }

    @Override
    public String getTid() {
        return tid;
    }

    @Override
    public String getParams() {
        return Api.endPicture;
    }

    /**
     *
     * @param params    需要传入频道参数和index起始参数
     * @return
     */
    @Override
    public String buildURL(String... params) {
        String url = Api.PictureUrl + getTid() + getAllParams(params) +  getParams();
        LogUtils.d("PicProtocol", url);
        return url;
    }

    @Override
    public ArrayList<PicListBean> parseData(String result, String tid) {
        LogUtils.d("图片返回的数据", "parseData: " + result);
        ArrayList<PicListBean> PicListBeans = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PicListBean picListbean = gson.fromJson(jsonObject.toString(), PicListBean.class);
                LogUtils.d("图片返回的数据", "parseData: " + picListbean);
                PicListBeans.add(picListbean);
            }
            return PicListBeans;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
