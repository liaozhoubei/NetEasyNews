package cn.bproject.neteasynews

import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun testGson(){
        var ss:String ="{" +
                "\"视频\": [{" +
                "\"prompt\": \"成功为您推荐10条新视频\"," +
                "\"replyCount\": 7," +
                "\"videosource\": \"大叔麻溜娱\"," +
                "\"title\": \"这才是美腿的正确打开方式，让魅力围绕在身边\"," +
                "\"playCount\": 33104," +
                "\"voteCount\": 10," +
                "\"description\": \"这才是美腿的正确打开方式，让魅力围绕在身边\"," +
                "\"replyid\": \"RK64LP8T050835RB\"," +
                "\"mp4_url\": \"http://flv3.bn.netease.com/videolib1/1907/27/10oo11qke/SD/10oo11qke-mobile.mp4\"," +
                "\"mp4Hd_url\": \"http://flv3.bn.netease.com/videolib1/1907/27/10oo11qke/HD/10oo11qke-mobile.mp4\"," +
                "\"m3u8_url\": \"http://flv0.bn.netease.com/videolib1/1907/27/10oo11qke/SD/movie_index.m3u8\"," +
                "\"m3u8Hd_url\": \"http://flv0.bn.netease.com/videolib1/1907/27/10oo11qke/HD/movie_index.m3u8\"," +
                "\"length\": 11," +
                "\"playersize\": 0," +
                "\"vid\": \"VRK64LP8T\"," +
                "\"ptime\": \"2019-07-27 16:50:08\"," +
                "\"replyBoard\": \"video_bbs\"," +
                "\"danmu\": 1," +
                "\"topicDesc\": \"自驾去西部吧，那里有大漠长河，戈壁高山、古城要塞、金戈铁马......如画美景和雄浑的历史扑面而来，让你应接不暇。\"," +
                "\"topicSid\": \"VCPQO5Q34\"," +
                "\"cover\": \"http://videoimg.ws.126.net/cover/20190727/HrQuw61Tq_cover.jpg\"," +
                "\"topicName\": \"大叔麻溜娱\"," +
                "\"videoTopic\": {" +
                "\"alias\": \"易闻·易见·易娱·易乐\"," +
                "\"tname\": \"大叔麻溜娱\"," +
                "\"ename\": \"T1501170466492\"," +
                "\"tid\": \"T1501170466492\"," +
                "\"topic_icons\": \"http://dingyue.ws.126.net/78UAdzfTAcnjc7vGhmBE9d5J0nVHi=zXUpzEcIw4SNoxG1553242175372.jpeg\"," +
                "\"followed\": false" +
                "}," +
                "\"program\": \"VCH_NEWUSER_BASE\"," +
                "\"sizeSD\": 526," +
                "\"sizeHD\": 657," +
                "\"sizeSHD\": 0," +
                "\"unlikeReason\": [" +
                "\"美女视频/1\"," +
                "\"美腿/2\"," +
                "\"萌妹子/2\"," +
                "\"来源:大叔麻溜娱/4\"," +
                "\"标题党/6\"," +
                "\"内容质量差/6\"," +
                "\"看过了/6\"," +
                "\"低俗重口/6\"" +
                "]," +
                "\"autoPlay\": 0," +
                "\"category\": \"美女/美女\"," +
                "\"accoutClassify\": 1," +
                "\"videoRatio\": 0.5625," +
                "\"covCLevel\": 4," +
                "\"cntCLevel\": 3," +
                "\"recomCount\": 0," +
                "\"shortV\": true," +
                "\"firstFrameImg\": \"http://videoimg.ws.126.net/cover/20190727/FBZGucdrf_1stframe.jpg\"," +
                "\"fullSizeImg\": \"http://videoimg.ws.126.net/cover/20190727/HrQuw61Tq_cover.jpg\"," +
                "\"reqId\": \"981ff43d-9019-4278-ac1d-85e711240ed2\"," +
                "\"videoHideTitle\": 0" +
                "}]" +
                "}";

        var gson:Gson = Gson();
        var vieo = gson.fromJson(ss,TestVieo::class.java);
        var bean:TestVieo.VideoBean = vieo.videolist?.get(0)!!;
        println("ExampleUnitTest.testGson ${bean.title}")
    }

    @Test
    fun testPic(){
        var ss:String = "{\"picList\":[{" +
                "\"postid\": \"PHOTKCJL000300AJ\"," +
                "\"desc\": \"2019年7月27日，北京，杨紫现身机场。她扎着丸子头少女感满屏，一身黑色look又酷又美，虽然戴着口罩仍能看出笑眼弯弯，是可爱软萌的“佟年”没错了。\"," +
                "\"pvnum\": \"\"," +
                "\"createdate\": \"2019-07-28 07:58:34\"," +
                "\"scover\": \"http://pic-bucket.ws.126.net/photo/0003/2019-07-28/EL5IB7SP00AJ0003NOS.jpg?imageView&thumbnail=100y75\"," +
                "\"setname\": \"杨紫扎俏皮丸子头全黑look又酷又美\"," +
                "\"cover\": \"http://pic-bucket.ws.126.net/photo/0003/2019-07-28/EL5IB7SP00AJ0003NOS.jpg\"," +
                "\"pics\": []," +
                "\"clientcover1\": \"\"," +
                "\"replynum\": \"9\"," +
                "\"topicname\": \"\"," +
                "\"setid\": \"668277\"," +
                "\"seturl\": \"http://ent.163.com/photoview/00AJ0003/668277.html\"," +
                "\"datetime\": \"2019-07-28 07:59:08\"," +
                "\"clientcover\": \"\"," +
                "\"imgsum\": \"4\"," +
                "\"tcover\": \"http://pic-bucket.ws.126.net/photo/0003/2019-07-28/EL5IB7SP00AJ0003NOS.jpg?imageView&thumbnail=160y120\"" +
                "}]}";
        var gson:Gson = Gson();
        var vieo = gson.fromJson(ss,TestPic::class.java);
        println("ExampleUnitTest.testGson ${vieo.picList[0].setname}")
    }
}
