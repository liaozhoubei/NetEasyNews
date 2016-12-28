package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.common.Api;

import static android.content.ContentValues.TAG;

/**
 * 普通新闻列表数据的获取以及解析方式
 */
public class NewsProtocol extends BaseProtocol<ArrayList<NewsListNormalBean>> {

    private String tid;

    public NewsProtocol(String tid) {
        this.tid = tid;
    }

    @Override
    public String getTid() {
        return tid;
    }

    @Override
    public String getParams() {
        return Api.endUrl;// 如果没有参数,就传空串,不要传null
    }

    @Override
    public String buildURL(int index) {

        String url = Api.CommonUrl + getTid() + "/" + index + getParams();
        return url;
    }

    /**
     * 解析新闻列表json数据
     *
     * @param json 新闻json数据
     * @param id   频道id
     * @return
     */
    @Override
    public ArrayList<NewsListNormalBean> parseData(String json, String id) {
        // Gson, JsonObject
        // 使用JsonObject解析方式: 如果遇到{},就是JsonObject;如果遇到[], 就是JsonArray
        if (json != null) {
            JSONObject jsonObject = null;
            ArrayList<NewsListNormalBean> newsListNormalBeans = new ArrayList<>();
            try {
                jsonObject = new JSONObject(json);
                JSONArray array = jsonObject.getJSONArray(id);
                Gson gson = new Gson();
                for (int i = 0; i < array.length(); i++) {
                    String js = array.get(i).toString();
                    NewsListNormalBean newsListNormalBean = gson.fromJson(js, NewsListNormalBean.class);
                    newsListNormalBeans.add(newsListNormalBean);
                }
                return newsListNormalBeans;
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
