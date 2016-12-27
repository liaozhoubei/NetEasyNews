package cn.bproject.neteasynews.http;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bproject.neteasynews.bean.NewsListNormalBean;

import static android.content.ContentValues.TAG;

/**
 * 首页网络数据解析
 * 
 * @author Kevin
 * @date 2015-10-28
 */
public class NewsProtocol extends BaseProtocol<ArrayList<NewsListNormalBean>> {

	private ArrayList<String> pictures;

	@Override
	public String getKey() {
		return "home";
	}

	@Override
	public String getParams() {
		return "";// 如果没有参数,就传空串,不要传null
	}
	/**
	 * 解析新闻列表json数据
	 * @param json  新闻json数据
	 * @param id    频道id
	 * @return
	 */
	@Override
	public ArrayList<NewsListNormalBean> parseData(String json, String id) {
		// Gson, JsonObject
		// 使用JsonObject解析方式: 如果遇到{},就是JsonObject;如果遇到[], 就是JsonArray


		JSONObject jsonObject = null;
		ArrayList<NewsListNormalBean> newsListNormalBeans = new ArrayList<>();
		try {
			jsonObject = new JSONObject(json);
			JSONArray array = jsonObject.getJSONArray(id);
			Gson gson = new Gson();
			for (int i = 0; i < array.length(); i++) {
				String js= array.get(i).toString();
				NewsListNormalBean newsListNormalBean = gson.fromJson(js, NewsListNormalBean.class);

				newsListNormalBeans.add(newsListNormalBean);
			}
			return newsListNormalBeans;
		} catch (JSONException e) {
			e.printStackTrace();
			Log.d(TAG, "parseJson: 数据解析错误");

		}

		return null;
	}

	public ArrayList<String> getPictureList() {
		return pictures;
	}

}
