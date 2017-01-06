package cn.bproject.neteasynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;

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
//        NewsProtocol newsProtocol = new NewsProtocol("T1467284926140");
//        String mStartIndex = "0";
//        String mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;
//        ArrayList<NewsListNormalBean> content = newsProtocol.getData(mUrl);
//        for (NewsListNormalBean bean : content) {
//            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean);
//        }
    }

    @Test
    public void getNewsDetailDataFromServer() {
        // 测试新闻详情
//        NewsDetailProtocol newsDetailProtocol = new NewsDetailProtocol("C8T2G5QL0511A99L");
//        NewsDetailBean content = newsDetailProtocol.getDetailData("http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html");
////        for (PicListBean bean : arrayList) {
//        Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + content);
////        }
    }

    @Test
    public void getVideoDataFromServer1(){
        // 测试视频列表
//        final VideoProtocol newsProtocol = new VideoProtocol();
////        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 21 + Api.devId;
//        String url = Api.host + "0" + Api.SpecialendUrl + 21 + Api.devId;
//        ArrayList<VideoBean> vidwoBeens = newsProtocol.getData(url);
//        for (VideoBean bean : vidwoBeens) {
//            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
//        }
    }

    @Test
    public void getVideoDataFromServer() {
        // 测试视频列表
        String url =  Api.host + "0" + Api.SpecialendUrl + 21 + Api.devId;
//        CacheUtils baseProtocol1 = new CacheUtils();
        HttpHelper.get(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String result) {
//                ArrayList<VideoBean> vidwoBeens = newsProtocol.parseData(result, "T1457068979049");
//                for (VideoBean bean : vidwoBeens) {
//                    Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
//                }
            }

            @Override
            public void onError(String str, Exception E) {
                Log.e("ExampleInstrumentedTest", "getDataFromServer: title :  " + str);
            }
        });

//        ArrayList<VideoBean> vidwoBeens = newsProtocol.getData(url);
//        for (VideoBean bean : vidwoBeens) {
//            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
//        }

    }

    @Test
    public void getVideoDetailDataFromServer() {
        // 测试视频列表
//        VideoDetailProtocol newsProtocol = new VideoDetailProtocol();
//
//        VideoBean vidwoBeen = newsProtocol.getDetailData("http://c.m.163.com/nc/video/detail/VC8TVUN5N.html");
//
//        Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + vidwoBeen.getTitle());

    }

    @Test
    public void getPicListDataFromServer() {

//        PicProtocol picProtocol = new PicProtocol("0001");
//
//        ArrayList<PicListBean> arrayList = picProtocol.getData("http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json");
//        for (PicListBean bean : arrayList) {
//            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getSetname());
//        }
    }

    @Test
    public void getPicDetailDataFromServer() {
//        PicDetailProtocol picDetailProtocol = new PicDetailProtocol("0031");
//        ImageDetailBean imageDetailBean = picDetailProtocol.getData("http://c.m.163.com/photo/api/set/0031/13897.json");
////        imageDetailBean.getCover();
//        Log.d(TAG, "getPicDetailDataFromServer: " + imageDetailBean.getCover());
    }
}
