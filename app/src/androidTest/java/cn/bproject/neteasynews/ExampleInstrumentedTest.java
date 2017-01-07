package cn.bproject.neteasynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cn.bproject.neteasynews.bean.ImageDetailBean;
import cn.bproject.neteasynews.bean.NewsDetailBean;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.bproject.neteasynews", appContext.getPackageName());
    }

    @Test
    public void getNewsListDataFromServer() {
        String url = Api.CommonUrl + Api.yaowenspecialId + "/" + 0 + Api.endUrl;
        // 测试新闻详情
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                ArrayList<NewsListNormalBean> content = DataParse.NewsList(result, Api.yaowenspecialId);
                for (NewsListNormalBean bean : content) {
                    Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean);
                }
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });

    }

    @Test
    public void getNewsDetailDataFromServer() {
        String url = "http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html";
        // 测试新闻详情
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                NewsDetailBean content = DataParse.NewsDetail(result, "C8T2G5QL0511A99L");
                Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + content);
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });

    }

    @Test
    public void getVideoDataFromServer1() {
        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 21 + Api.devId;
        // 测试视频列表
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                ArrayList<VideoBean> vidwoBeens = DataParse.VideoList(result);
                for (VideoBean bean : vidwoBeens) {
                    Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
                }
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });

    }


    @Test
    public void getVideoDetailDataFromServer() {
        String url = "http://c.m.163.com/nc/video/detail/VC8TVUN5N.html";
        // 测试视频列表
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                VideoBean vidwoBeen = DataParse.VideoDetail(result);
//
                Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + vidwoBeen.getTitle());
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });


    }

    @Test
    public void getPicListDataFromServer() {
        String url = "http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json";
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                ArrayList<PicListBean> arrayList = DataParse.PicList(result);
                for (PicListBean bean : arrayList) {
                    Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getSetname());
                }
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });

    }

    @Test
    public void getPicDetailDataFromServer() {
        String url = "http://c.m.163.com/photo/api/set/0031/13897.json";
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
                ImageDetailBean imageDetailBean = DataParse.ImageDetail(result);
                Log.d(TAG, "getPicDetailDataFromServer: " + imageDetailBean.getCover());
            }

            @Override
            public void onError(String result, Exception e) {
                Log.e(TAG, "onError: " + result + "    " + e );
            }
        });

    }
}
