package cn.bproject.neteasynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cn.bproject.neteasynews.bean.NewsDetailBean;
import cn.bproject.neteasynews.bean.NewsListNormalBean;
import cn.bproject.neteasynews.bean.PicListBean;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.http.BaseProtocol;
import cn.bproject.neteasynews.http.NewsDetailProtocol;
import cn.bproject.neteasynews.http.NewsProtocol;
import cn.bproject.neteasynews.http.PicProtocol;
import cn.bproject.neteasynews.http.VideoProtocol;

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
        ArrayList<NewsListNormalBean> content = newsProtocol.getData(0);
        for (NewsListNormalBean bean : content) {
        Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean);
        }
    }

    @Test
    public void getNewsDetailDataFromServer() {
        // 测试新闻详情
        NewsDetailProtocol newsDetailProtocol = new NewsDetailProtocol("C8T2G5QL0511A99L");
        NewsDetailBean content = newsDetailProtocol.getDetailData();
//        for (PicListBean bean : arrayList) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + content);
//        }
    }

    @Test
    public void getVideoDataFromServer() {
        // 测试视频列表
        VideoProtocol newsProtocol = new VideoProtocol();
        ArrayList<VideoBean> vidwoBeens = newsProtocol.getData(0);
        for (VideoBean bean : vidwoBeens
                ) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
        }
    }

    @Test
    public void getPicListDataFromServer() {
        // 测试视频列表
        PicProtocol picProtocol = new PicProtocol("0031");

        ArrayList<PicListBean> arrayList = picProtocol.getData(BaseProtocol.PIC_TYPE, Api.RecommendPicture, 0);
        for (PicListBean bean : arrayList) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getSetname());
        }
    }
}
