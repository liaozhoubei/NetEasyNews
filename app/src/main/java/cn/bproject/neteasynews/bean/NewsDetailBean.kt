package cn.bproject.neteasynews.bean

/**
 * Created by Administrator on 2016/12/26.
 *
 * 先获得文字的docid，然后通过json解析，获取jsonObject，转化为String，在通过Gson解析
 * 示例：
 * docid ： C9741113000181BR
 *
 * JSONObject jsonObject = new JSONObject(json);
 * String detail = jsonObject.getJSONObject("C9741113000181BR").toString();
 * Gson gson = new Gson();
 * NewsDetailBean newsDetailBean = gson.fromJson(detail, NewsDetailBean.class);
 *
 */

class NewsDetailBean {
    var body: String? = null
    var replyCount: Int = 0
    var shareLink: String? = null
    var digest: String? = null

    var docid: String? = null
    var isPicnews: Boolean = false
    var title: String? = null
    var tid: String? = null
    var template: String? = null


    var source: String? = null

    var voicecomment: String? = null
    var ptime: String? = null
    var users: List<*>? = null
    var ydbaike: List<*>? = null
    var link: List<LinkBean>? = null
    var img: List<ImgBean>? = null
    var votes: List<VotesBean>? = null
    var topiclist_news: List<TopiclistNewsBean>? = null
    var topiclist: List<*>? = null
    var video: List<VideoBean>? = null
    var boboList: List<*>? = null


    class LinkBean {
        /**
         * ref :
         * title : 你的小目标达成了吗？
         * imgsrc : http://cms-bucket.nosdn.127.net/4bba72ca129c45b2bea3038fe450ef6420161223202339.jpeg
         * digest : 距存够2W的小目标还差3W
         * type : linkCard
         * href : http://3g.163.com/news/16/1223/20/C90EQK7D000181BT.html
         */

        var ref: String? = null
        var title: String? = null
        var imgsrc: String? = null
        var digest: String? = null
        var type: String? = null
        var href: String? = null
    }

    class ImgBean {
        /**
         * ref :
         * pixel : 690*692
         * alt :
         * src : http://cms-bucket.nosdn.127.net/c85eed9a823043249c9eeb99bca7f69e20161226084157.jpeg
         */

        var ref: String? = null
        var pixel: String? = null
        var alt: String? = null
        var src: String? = null
    }

    class VotesBean {
        /**
         * digest : 你们考过研吗？
         * date : 2016-12-26
         * url :
         * option_type : 0
         * date_type : 0
         * sumnum : 1109
         * docid :
         * ref :
         * voteid : 58378
         * voteitem : [{"id":"247999","num":451,"name":"考过研，我当时____。"},{"id":"248000","num":658,"name":"没考过，想想都挺累。"}]
         * imgsrc :
         * voteType : pkVote
         * question : 你们考过研吗？
         */

        var digest: String? = null
        var date: String? = null
        var url: String? = null
        var option_type: Int = 0
        var date_type: Int = 0
        var sumnum: Int = 0
        var docid: String? = null
        var ref: String? = null
        var voteid: String? = null
        var imgsrc: String? = null
        var voteType: String? = null
        var question: String? = null
        var voteitem: List<VoteitemBean>? = null

        class VoteitemBean {
            /**
             * id : 247999
             * num : 451
             * name : 考过研，我当时____。
             */

            var id: String? = null
            var num: Int = 0
            var name: String? = null
        }
    }

    class TopiclistNewsBean {
        /**
         * hasCover : true
         * subnum : 超过1000万
         * alias : FUNNY MOMENT
         * tname : 轻松一刻
         * ename : qingsongyike
         * tid : T1350383429665
         * cid : C1348654575297
         */

        var isHasCover: Boolean = false
        var subnum: String? = null
        var alias: String? = null
        var tname: String? = null
        var ename: String? = null
        var tid: String? = null
        var cid: String? = null
    }

    class VideoBean {
        /**
         * commentid : C84JV7AL00964ILB
         * topicid : 0096
         * broadcast : in
         * videosource : 其他
         * commentboard : mobile_bbs
         * appurl :
         * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/rbPOL1739-mobile.mp4
         * url_m3u8 : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
         * size :
         * ref :
         * cover : http://vimg2.ws.126.net/image/snapshot/2016/12/S/T/VC84JV4ST.jpg
         * url_mp4 : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
         * alt : 年终策划视频《小时代之狗血2017》
         * mp4_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
         * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/movie_index.m3u8
         * m3u8_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/movie_index.m3u8
         * vid : VC84JV7AL
         */

        var commentid: String? = null
        var topicid: String? = null
        var broadcast: String? = null
        var videosource: String? = null
        var commentboard: String? = null
        var appurl: String? = null
        var mp4Hd_url: String? = null
        var url_m3u8: String? = null
        var size: String? = null
        var ref: String? = null
        var cover: String? = null
        var url_mp4: String? = null
        var alt: String? = null
        var mp4_url: String? = null
        var m3u8Hd_url: String? = null
        var m3u8_url: String? = null
        var vid: String? = null
    }

    override fun toString(): String {
        return "NewsDetailBean{" +
                "body='" + body + '\''.toString() +
                '}'.toString()
    }
}
