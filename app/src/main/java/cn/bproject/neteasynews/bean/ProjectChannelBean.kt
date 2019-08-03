package cn.bproject.neteasynews.bean

/**
 * Created by Administrator on 2017/2/7.
 * 频道列表
 */

class ProjectChannelBean {

    var tList: List<TListBean>? = null

    class TListBean {
        /**
         * template : normal1
         * img : http://img2.cache.netease.com/m/newsapp/banner/zhenhua.png
         * recommendOrder : 0
         * color :
         * ad_type : 1
         * hasCover : false
         * tname : 独家
         * hashead : 1
         * recommend : 0
         * isNew : 0
         * tid : T1370583240249
         * headLine : false
         * special : 0
         * ename : zhenhua
         * topicid : 000181S1
         * hasIcon : true
         * bannerOrder : 105
         * repeatSeconds : 0
         * alias : The Truth
         * showType : comment
         * subnum : 0
         * hasAD : 1
         * isHot : 0
         * cid : C1348654575297
         * weburl : http://www.163.com/
         * tagDate : 2019-07-05 10:22:40.0
         * tag : new
         */

        var template: String? = null
        var img: String? = null
        var recommendOrder: Int = 0
        var color: String? = null
        var ad_type: Int = 0
        var isHasCover: Boolean = false
        var tname: String? = null
        var hashead: Int = 0
        var recommend: String? = null
        var isNew: Int = 0
        var tid: String? = null
        var isHeadLine: Boolean = false
        var special: Int = 0
        var ename: String? = null
        var topicid: String? = null
        var isHasIcon: Boolean = false
        var bannerOrder: Int = 0
        var repeatSeconds: Int = 0
        var alias: String? = null
        var showType: String? = null
        var subnum: String? = null
        var hasAD: Int = 0
        var isHot: Int = 0
        var cid: String? = null
        var weburl: String? = null
        var tagDate: String? = null
        var tag: String? = null

        var title: String? = null
        var column: String?= null
    }
}
