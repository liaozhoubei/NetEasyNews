package cn.bproject.neteasynews.bean

/**
 * Created by Administrator on 2016/12/26.
 * 图片新闻详情页实体类
 *
 * http://c.m.163.com/photo/api/set/0009/13897.json
 */

class ImageDetailBean {


    /**
     * autoid :
     * boardid : photoview_bbs
     * clientadurl :
     * commenturl : http://comment.tech.163.com/photoview_bbs/PHOT0DI900090AI2.html
     * cover : http://img4.cache.netease.com/photo/0009/2016-12-22/C8RQJVLT0AI20009.jpg
     * createdate : 2016-12-22 01:19:34
     * creator : 白鑫
     * datatime : 2016-12-24 12:33:55
     * desc : 一周外媒动物图片精选：会走路的鲨鱼
     * imgsum : 16
     * neteasecode :
     * photos : []
     * postid : PHOT0DI900090AI2
     * relatedids : []
     * reporter :
     * scover : http://img4.cache.netease.com/photo/0009/2016-12-22/s_C8RQJVLT0AI20009.jpg
     * series :
     * setname : 一周外媒动物图片精选：会走路的鲨鱼
     * settag : 动物
     * source : 网易科技
     * tcover : http://img4.cache.netease.com/photo/0009/2016-12-22/t_C8RQJVLT0AI20009.jpg
     * url : http://tech.163.com/photoview/0AI20009/13897.html
     */
    // 封面
    var cover: String? = null
    // 创建日期
    var createdate: String? = null
    // 创建者
    var creator: String? = null
    var datatime: String? = null
    // 图片文字描述
    var desc: String? = null
    // 图片总数
    var imgsum: String? = null
    // 图片文字id
    var postid: String? = null
    // 文章来源
    var source: String? = null
    // 文章的url地址
    var url: String? = null

    var setname: String? = null
    // 图片集合信息
    var photos: List<PhotosBean>? = null

    class PhotosBean {
        /**
         * cimgurl : http://img3.cache.netease.com/photo/0009/2016-12-18/c_C8JSL7H10AI20009.jpg
         * imgtitle : 一周外媒动物图片精选
         * imgurl : http://img4.cache.netease.com/photo/0009/2016-12-18/C8JSL7H10AI20009.jpg
         * newsurl : #
         * note : （来源：网易科技）
         * photohtml : http://tech.163.com/photoview/0AI20009/13897.html#p=C8JSL7H10AI20009
         * photoid : C8JSL7H10AI20009
         * simgurl : http://img4.cache.netease.com/photo/0009/2016-12-18/s_C8JSL7H10AI20009.jpg
         * squareimgurl : http://img3.cache.netease.com/photo/0009/2016-12-18/400x400_C8JSL7H10AI20009.jpg
         * timgurl : http://img3.cache.netease.com/photo/0009/2016-12-18/t_C8JSL7H10AI20009.jpg
         */
        // 图片标题，不一定有
        var imgtitle: String? = null
        // 当前图片的URL地址
        var imgurl: String? = null
        // 新闻url，不移动有
        var newsurl: String? = null
        // 图片描述或者来源
        var note: String? = null
        // 单张图片的url地址（网页版）
        var photohtml: String? = null
        // 图集的文章id
        var photoid: String? = null
    }
}
