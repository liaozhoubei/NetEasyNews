package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;

import static android.content.ContentValues.TAG;

/**
 * Created by Bei on 2016/12/29.
 */

public class VideoProtocol extends BaseProtocol<ArrayList<VideoBean>> {

    @Override
    public String getTid() {
        return "T1457068979049";
    }

    @Override
    public String getParams() {
//        String connectUrl  = url + getTid() + "/" + index  + getParams();

        return Api.devId;
    }

    @Override
    public String buildURL(int index) {
        String url = Api.host + Api.SpecialColumn2 + getTid() + Api.SpecialendUrl + index + getParams();
        return url;
    }


    @Override
    public ArrayList<VideoBean> parseData(String result, String tid) {
        if (result != null) {
            JSONObject jsonObject = null;
            ArrayList<VideoBean> videoBeen = new ArrayList<>();
            try {
                jsonObject = new JSONObject(result);
                JSONArray array = jsonObject.getJSONArray("视频");
                Gson gson = new Gson();
                for (int i = 0; i < array.length(); i++) {
                    String js = array.get(i).toString();
                    VideoBean vieoBean = gson.fromJson(js, VideoBean.class);
                    videoBeen.add(vieoBean);
                }
                return videoBeen;
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
