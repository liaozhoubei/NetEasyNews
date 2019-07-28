package cn.bproject.neteasynews

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class TestVieo : Serializable {
    @SerializedName("视频")
    var videolist: List<VideoBean>? = null

    class VideoBean {
        /**
         * prompt : 成功为您推荐10条新视频
         * replyCount : 7
         * videosource : 大叔麻溜娱
         * title : 这才是美腿的正确打开方式，让魅力围绕在身边
         * playCount : 33104
         * voteCount : 10
         * description : 这才是美腿的正确打开方式，让魅力围绕在身边
         * replyid : RK64LP8T050835RB
         * mp4_url : http://flv3.bn.netease.com/videolib1/1907/27/10oo11qke/SD/10oo11qke-mobile.mp4
         * mp4Hd_url : http://flv3.bn.netease.com/videolib1/1907/27/10oo11qke/HD/10oo11qke-mobile.mp4
         * m3u8_url : http://flv0.bn.netease.com/videolib1/1907/27/10oo11qke/SD/movie_index.m3u8
         * m3u8Hd_url : http://flv0.bn.netease.com/videolib1/1907/27/10oo11qke/HD/movie_index.m3u8
         * length : 11
         * playersize : 0
         * vid : VRK64LP8T
         * ptime : 2019-07-27 16:50:08
         * replyBoard : video_bbs
         * danmu : 1
         * topicDesc : 自驾去西部吧，那里有大漠长河，戈壁高山、古城要塞、金戈铁马......如画美景和雄浑的历史扑面而来，让你应接不暇。
         * topicSid : VCPQO5Q34
         * cover : http://videoimg.ws.126.net/cover/20190727/HrQuw61Tq_cover.jpg
         * topicName : 大叔麻溜娱
         * videoTopic : {"alias":"易闻·易见·易娱·易乐","tname":"大叔麻溜娱","ename":"T1501170466492","tid":"T1501170466492","topic_icons":"http://dingyue.ws.126.net/78UAdzfTAcnjc7vGhmBE9d5J0nVHi=zXUpzEcIw4SNoxG1553242175372.jpeg","followed":false}
         * program : VCH_NEWUSER_BASE
         * sizeSD : 526
         * sizeHD : 657
         * sizeSHD : 0
         * unlikeReason : ["美女视频/1","美腿/2","萌妹子/2","来源:大叔麻溜娱/4","标题党/6","内容质量差/6","看过了/6","低俗重口/6"]
         * autoPlay : 0
         * category : 美女/美女
         * accoutClassify : 1
         * videoRatio : 0.5625
         * covCLevel : 4
         * cntCLevel : 3
         * recomCount : 0
         * shortV : true
         * firstFrameImg : http://videoimg.ws.126.net/cover/20190727/FBZGucdrf_1stframe.jpg
         * fullSizeImg : http://videoimg.ws.126.net/cover/20190727/HrQuw61Tq_cover.jpg
         * reqId : 981ff43d-9019-4278-ac1d-85e711240ed2
         * videoHideTitle : 0
         */

        var prompt: String? = null
        var replyCount: Int = 0
        var videosource: String? = null
        var title: String? = null
        var playCount: Int = 0
        var voteCount: Int = 0
        var description: String? = null
        var replyid: String? = null
        var mp4_url: String? = null
        var mp4Hd_url: String? = null
        var m3u8_url: String? = null
        var m3u8Hd_url: String? = null
        var length: Int = 0
        var playersize: Int = 0
        var vid: String? = null
        var ptime: String? = null
        var replyBoard: String? = null
        var danmu: Int = 0
        var topicDesc: String? = null
        var topicSid: String? = null
        var cover: String? = null
        var topicName: String? = null
        var videoTopic: VideoTopicBean? = null
        var program: String? = null
        var sizeSD: Int = 0
        var sizeHD: Int = 0
        var sizeSHD: Int = 0
        var autoPlay: Int = 0
        var category: String? = null
        var accoutClassify: Int = 0
        var videoRatio: Double = 0.toDouble()
        var covCLevel: Int = 0
        var cntCLevel: Int = 0
        var recomCount: Int = 0
        var isShortV: Boolean = false
        var firstFrameImg: String? = null
        var fullSizeImg: String? = null
        var reqId: String? = null
        var videoHideTitle: Int = 0
        var unlikeReason: List<String>? = null

        class VideoTopicBean {
            /**
             * alias : 易闻·易见·易娱·易乐
             * tname : 大叔麻溜娱
             * ename : T1501170466492
             * tid : T1501170466492
             * topic_icons : http://dingyue.ws.126.net/78UAdzfTAcnjc7vGhmBE9d5J0nVHi=zXUpzEcIw4SNoxG1553242175372.jpeg
             * followed : false
             */

            var alias: String? = null
            var tname: String? = null
            var ename: String? = null
            var tid: String? = null
            var topic_icons: String? = null
            var isFollowed: Boolean = false
        }
    }
}
