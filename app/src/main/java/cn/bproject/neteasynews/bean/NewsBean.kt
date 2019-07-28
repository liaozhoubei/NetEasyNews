package cn.bproject.neteasynews.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Bei on 2016/12/25.
 *
 */

class NewsBean  : Serializable {
    @SerializedName("T1467284926140")
    private var newslist: List<NewsBean>? = null

    fun getNewslist(): List<NewsBean>? {
        return newslist
    }

    fun setNewslist(newslist: List<NewsBean>) {
        this.newslist = newslist
    }

    class NewsBean {
        /**
         * template : normal1
         * lmodify : 2019-07-28 08:21:35
         * source :
         * postid : EL5G6KCT0001899O
         * title : 没病到一定程度，千万别去创业
         * mtime : 2019-07-28 08:21:35
         * hasImg : 1
         * articleType : webview
         * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348647991705.jpg
         * digest : 点击继续查看使用安卓和iPhone最新版本客户端可获得更流畅体验，下载地址：安卓用户点这里iPhone用户点这里
         * boardid : news2_bbs
         * alias : yaowenspecial
         * hasAD : 1
         * imgsrc : http://cms-bucket.ws.126.net/2019/07/28/916372be964f44bfbf1ffc27b9505d19.png
         * ptime : 2019-07-28 07:21:22
         * daynum : 18105
         * hasHead : 1
         * imgType : 1
         * order : 1
         * editor : []
         * votecount : 4920
         * hasCover : false
         * docid : EL5G6KCT0001899O
         * tname : 要闻
         * url_3w : http://news.163.com/19/0728/07/EL5G6KCT0001899O.html
         * priority : 256
         * url : http://3g.163.com/news/19/0728/07/EL5G6KCT0001899O.html
         * ename : yaowenspecial
         * replyCount : 5312
         * ltitle : 没病到一定程度，千万别去创业
         * hasIcon : false
         * subtitle :
         * cid : C1348647991705
         * pixel : 412*270
         * skipID : S1564285264966
         * specialID : S1564285264966
         * skipType : special
         */

        var template: String? = null
        var lmodify: String? = null
        var source: String? = null
        var postid: String? = null
        var title: String? = null
        var mtime: String? = null
        var hasImg: Int = 0
        var articleType: String? = null
        var topic_background: String? = null
        var digest: String? = null
        var boardid: String? = null
        var alias: String? = null
        var hasAD: Int = 0
        var imgsrc: String? = null
        var ptime: String? = null
        var daynum: String? = null
        var hasHead: Int = 0
        var imgType: Int = 0
        var order: Int = 0
        var votecount: Int = 0
        var isHasCover: Boolean = false
        var docid: String? = null
        var tname: String? = null
        var url_3w: String? = null
        var priority: Int = 0
        var url: String? = null
        var ename: String? = null
        var replyCount: Int = 0
        var ltitle: String? = null
        var isHasIcon: Boolean = false
        var subtitle: String? = null
        var cid: String? = null
        var pixel: String? = null
        var skipID: String? = null
        var specialID: String? = null
        var skipType: String? = null
        var editor: List<*>? = null
    }
}
