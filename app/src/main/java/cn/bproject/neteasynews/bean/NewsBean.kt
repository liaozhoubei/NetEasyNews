package cn.bproject.neteasynews.bean

/**
 * Created by Bei on 2016/12/25.
 *
 */

class NewsBean {
    var title: String? = null // 分类Tab名称
    //    private String href; // 分类点击地址
    private var tid: String? = null // 分类类型
    var column: String? = null  //  图片的分类

    constructor() : super() {}

    constructor(title: String, column: String, tid: String) {
        this.title = title
        this.column = column
        this.tid = tid
    }


    fun getTid(): String? {
        return tid
    }

    fun setTid(data_type: String) {
        this.tid = tid
    }
}
