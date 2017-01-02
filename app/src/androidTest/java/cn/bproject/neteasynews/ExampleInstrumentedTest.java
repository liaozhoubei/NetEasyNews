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
import cn.bproject.neteasynews.http.NewsDetailProtocol;
import cn.bproject.neteasynews.http.NewsProtocol;
import cn.bproject.neteasynews.http.PicDetailProtocol;
import cn.bproject.neteasynews.http.PicProtocol;
import cn.bproject.neteasynews.http.VideoProtocol;

import static android.content.ContentValues.TAG;
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
        // 测试新闻详情
        NewsProtocol newsProtocol = new NewsProtocol("T1467284926140");
        String mStartIndex = "0";
        String mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;
        ArrayList<NewsListNormalBean> content = newsProtocol.getData(mUrl);
        for (NewsListNormalBean bean : content) {
        Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean);
        }
    }

    @Test
    public void getNewsDetailDataFromServer() {
        // 测试新闻详情
        NewsDetailProtocol newsDetailProtocol = new NewsDetailProtocol("C8T2G5QL0511A99L");
        NewsDetailBean content = newsDetailProtocol.getDetailData("http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html");
//        for (PicListBean bean : arrayList) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + content);
//        }
    }

    @Test
    public void getVideoDataFromServer() {
        // 测试视频列表
        VideoProtocol newsProtocol = new VideoProtocol();
        ArrayList<VideoBean> vidwoBeens = newsProtocol.getData( Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId);
        for (VideoBean bean : vidwoBeens
                ) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
        }
    }

    @Test
    public void getPicListDataFromServer() {

        PicProtocol picProtocol = new PicProtocol("0001");

        ArrayList<PicListBean> arrayList = picProtocol.getData("http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json");
        for (PicListBean bean : arrayList) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getSetname());
        }
    }

    @Test
    public void getPicDetailDataFromServer(){
        PicDetailProtocol picDetailProtocol = new PicDetailProtocol("0031");
        ImageDetailBean imageDetailBean = picDetailProtocol.getData("http://c.m.163.com/photo/api/set/0031/13897.json");
//        imageDetailBean.getCover();
        Log.d(TAG, "getPicDetailDataFromServer: " + imageDetailBean.getCover());
    }
}
