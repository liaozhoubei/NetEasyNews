package cn.bproject.neteasynews.bean

/**
 * Created by Administrator on 2016/12/26.
 * 普通情况下的新闻列表页
 * 注意：
 * imgextra为额外图片列表，有些新闻有，有些没有
 * hasAD：如果值为 1，则imgsrc是横屏大图
 */

class NewsListNormalBean {

    /**
     * postid : C96SHSNS000181KT
     * url_3w : http://news.163.com/16/1226/08/C96SHSNS000181KT.html
     * votecount : 10343
     * replyCount : 11416
     * skipID : S1482709380297
     * ltitle : 港媒:首批苏-35战机抵达沧州 正式交付解放军
     * digest : 【环球网军事12月26日报道】据香港《东方日报》网站12月26日报道，中国去年底与俄罗斯达成协议，将会向俄方购入24架苏35战机，25日再有新消息传出。网传消息
     * skipType : special
     * url : http://3g.163.com/news/16/1226/08/C96SHSNS000181KT.html
     * specialID : S1482709380297
     * docid : C96SHSNS000181KT
     * title : 港媒:首批苏-35战机抵达沧州 正式交付解放军
     * source : 参考消息网
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/416fb909a7be4ef0994a049dc72d798820161226090753.png"},{"imgsrc":"http://cms-bucket.nosdn.127.net/f6e9c067651a487c8bdf1e22e9580c6f20161226090753.png"}]
     * priority : 95
     * lmodify : 2016-12-26 09:12:44
     * boardid : news_junshi_bbs
     * subtitle :
     * imgsrc : http://cms-bucket.nosdn.127.net/740c55691b664c46a22e5d3216b1ece320161226090753.png
     * ptime : 2016-12-26 08:23:30
     */
    // 文章发布时的id
    var postid: String? = null
    // 网页链接地址
    var url_3w: String? = null
    var votecount: Int = 0
    // 回帖人数
    var replyCount: Int = 0
    var skipID: String? = null
    var ltitle: String? = null
    // 文章描述
    var digest: String? = null
    var skipType: String? = null
    // 手机版URL地址
    var url: String? = null
    var specialID: String? = null
    // 新闻文档ID
    var docid: String? = null
    // 新闻标题
    var title: String? = null
    // 新闻来源
    var source: String? = null
    // 优先级
    var priority: Int = 0
    var lmodify: String? = null
    var boardid: String? = null
    // 副标题
    var subtitle: String? = null
    // 图片链接
    var imgsrc: String? = null
    // 发布时间
    var ptime: String? = null
    // 是否banner新闻
    var hasAD: Int = 0
    // 新闻是否为图片新闻
    var photosetID: String? = null

    // 额外图片
    var imgextra: List<ImgextraBean>? = null

    class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/416fb909a7be4ef0994a049dc72d798820161226090753.png
         */

        var imgsrc: String? = null

        override fun toString(): String {
            return "ImgextraBean{" +
                    "imgsrc='" + imgsrc + '\''.toString() +
                    '}'.toString()
        }
    }

    override fun toString(): String {
        return "NewsListNormalBean{" +
                "postid='" + postid + '\''.toString() +
                ", url_3w='" + url_3w + '\''.toString() +
                ", votecount=" + votecount +
                ", replyCount=" + replyCount +
                ", skipID='" + skipID + '\''.toString() +
                ", ltitle='" + ltitle + '\''.toString() +
                ", digest='" + digest + '\''.toString() +
                ", skipType='" + skipType + '\''.toString() +
                ", url='" + url + '\''.toString() +
                ", specialID='" + specialID + '\''.toString() +
                ", docid='" + docid + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", source='" + source + '\''.toString() +
                ", priority=" + priority +
                ", lmodify='" + lmodify + '\''.toString() +
                ", boardid='" + boardid + '\''.toString() +
                ", subtitle='" + subtitle + '\''.toString() +
                ", imgsrc='" + imgsrc + '\''.toString() +
                ", ptime='" + ptime + '\''.toString() +
                ", hasAD=" + hasAD +
                ", imgextra=" + imgextra +
                '}'.toString()
    }
}
