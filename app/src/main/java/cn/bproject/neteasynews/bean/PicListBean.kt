package cn.bproject.neteasynews.bean

/**
 * Created by liaozhoubei on 2016/12/30.
 */

class PicListBean {

    /**
     * desc : Hendo除了COS男版蜘蛛侠外更是挑战了一下原作中由蜘蛛侠女友温格所变身的女版蜘蛛，效果更是到位。女版蜘蛛侠会不会更厉害，更值得期待？
     * pvnum :
     * createdate : 2016-12-27 01:49:02
     * scover : http://img3.cache.netease.com/photo/0031/2016-12-27/s_C98O8VGL6LRK0031.jpg
     * setname : 女版蜘蛛侠会不会更厉害？值得期待！
     * cover : http://img4.cache.netease.com/photo/0031/2016-12-27/C98O8VGL6LRK0031.jpg
     * pics : ["http://img3.cache.netease.com/photo/0031/2016-12-27/C98O8UBL6LRK0031.jpg","http://img3.cache.netease.com/photo/0031/2016-12-27/C98O8UPE6LRK0031.jpg","http://img4.cache.netease.com/photo/0031/2016-12-27/C98O8UVI6LRK0031.jpg"]
     * clientcover1 :
     * replynum : 217
     * topicname :
     * setid : 91416
     * seturl : http://play.163.com/photoview/6LRK0031/91416.html
     * datetime : 2016-12-27 01:57:49
     * clientcover :
     * imgsum : 16
     * tcover : http://img4.cache.netease.com/photo/0031/2016-12-27/t_C98O8VGL6LRK0031.jpg
     */

    //    private List<PicList> mPicLists;
    //
    //    public List<PicList> getPicLists() {
    //        return mPicLists;
    //    }
    //
    //    public void setPicLists(List<PicList> picLists) {
    //        mPicLists = picLists;
    //    }
    //
    //    public class PicList{
    var desc: String? = null
    // 创建时间
    var createdate: String? = null
    var setname: String? = null
    var cover: String? = null
    var setid: String? = null
    var seturl: String? = null
    // 发布时间
    var datetime: String? = null
    // 图片总数
    var imgsum: String? = null
    var pics: List<String>? = null

    override fun toString(): String {
        return "PicListBean{" +
                "setname='" + setname + '\''.toString() +
                ", cover='" + cover + '\''.toString() +
                ", createdate='" + createdate + '\''.toString() +
                '}'.toString()
    }


    //    }


}
