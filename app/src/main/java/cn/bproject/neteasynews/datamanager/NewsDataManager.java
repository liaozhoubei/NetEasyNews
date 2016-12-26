package cn.bproject.neteasynews.datamanager;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.bean.NewsListNormalBean;

/**
 * Created by Bei on 2016/12/27.
 */

public class NewsDataManager {
    private static final String TAG = NewsDataManager.class.getSimpleName();

    /**
     * 从网络中获取信息
     * @return  返回获取的json字符串
     */
//    public static List<NewsListNormalBean> getDataFromInternet(String url, final String pindao){
//
//        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.d(TAG, "onError: getDataFromInternet请求失败" + e);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//
////                Log.d(TAG, "onResponse: 数组来了" + json[0]);
//                parseJson(response.toString(), pindao);
//            }
//        });
//        return newsListNormalBeen[0];
//    }

    /**
     * 解析新闻列表json数据
     * @param json  新闻json数据
     * @param id    频道id
     * @return
     */
    public static List<NewsListNormalBean> parseJson(String json, String id){
        JSONObject jsonObject = null;
        List<NewsListNormalBean> newsListNormalBeans = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray(id);
            Gson gson = new Gson();
            for (int i = 0; i < array.length(); i++) {
                String js= array.get(i).toString();
                NewsListNormalBean newsListNormalBean = gson.fromJson(js, NewsListNormalBean.class);
//                System.out.println(newsListNormalBean  );
//                String title = newsListNormalBean.getTitle();
//
//                int hasAD = newsListNormalBean.getHasAD();
//                System.out.println(title + "   :" );
                newsListNormalBeans.add(newsListNormalBean);
            }
            return newsListNormalBeans;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "parseJson: 数据解析错误");

        }

        return null;
    }

}
