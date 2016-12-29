package cn.bproject.neteasynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cn.bproject.neteasynews.bean.VideoBean;
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
    public void getDataFromServer(){

        VideoProtocol newsProtocol = new VideoProtocol();
        ArrayList<VideoBean> vidwoBeens=  newsProtocol.getData();
        for (VideoBean bean: vidwoBeens
             ) {
            Log.d("ExampleInstrumentedTest", "getDataFromServer: title :  " + bean.getTitle());
        }
    }
}
