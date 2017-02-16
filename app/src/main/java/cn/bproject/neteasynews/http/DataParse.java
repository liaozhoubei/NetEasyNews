package cn.bproject.neteasynews.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.ImageDetailBean;
import cn.bproject.neteasynews.bean.NewsDetailBean;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.bean.VideoBean;

import static android.content.ContentValues.TAG;

/**
 * Created by Bei on 2017/1/6.
 * 解析Json数据的工具类
 */

public class DataParse {

    // 新闻列表解析
    public static ArrayList<NewsListNormalBean> NewsList(String json, String id) {
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


    /**
     *  新闻详情页解析
     * @param result
     * @param tid
     * @return
     */
    public static NewsDetailBean NewsDetail(String result, String tid) {
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

    /**
     * 图片列表解析
     * @param result
     * @return
     */
    public static ArrayList<PicListBean> PicList(String result) {
        LogUtils.d("图片返回的数据", "parseData: " + result);
        ArrayList<PicListBean> PicListBeans = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PicListBean picListbean = gson.fromJson(jsonObject.toString(), PicListBean.class);
                PicListBeans.add(picListbean);
            }
            return PicListBeans;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片新闻详情页解析
     * @param result
     * @return
     */
    public static ImageDetailBean ImageDetail(String result) {
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

    /**
     * 视频列表解析
     * @param result
     * @return
     */
    public static ArrayList<VideoBean> VideoList(String result) {
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

    /**
     * 视频详情页解析
     * @param result
     * @return
     */
    public static VideoBean VideoDetail(String result) {
        VideoBean bean = null;
        if (result != null) {
            Gson gson = new Gson();
            bean = gson.fromJson(result, VideoBean.class);
            LogUtils.d(TAG, "parseData: " + bean);
        }
        return bean;
    }
}
