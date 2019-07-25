package cn.bproject.neteasynews.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Bei on 2016/12/28.
 * 视频列表单个条目与视频详情页的数据基本相同
 * 不同点在于视频列表的m3u8_url和mp4_url的视频链接采用的是https协议，而视频详情页采用的是http协议
 */

class VideoBean() : Parcelable {

    /**
     * cover : http://vimg2.ws.126.net/image/snapshot/2016/12/L/K/VC8FOVBLK.jpg
     * description :
     * length : 206
     * m3u8_url : http://flv2.bn.netease.com/tvmrepo/2016/12/6/A/EC8F5GU6A/SD/movie_index.m3u8
     * mp4_url : http://flv2.bn.netease.com/tvmrepo/2016/12/6/A/EC8F5GU6A/SD/EC8F5GU6A-mobile.mp4
     * playCount : 103
     * playersize : 1
     * ptime : 2016-12-28 22:46:41.0
     * replyBoard : video_bbs
     * replyCount : 0
     * replyid : C8F5KVH9008535RB
     * sectiontitle : http://vimg3.ws.126.net/image/snapshot/2016/12/L/S/VC7T21ILS.jpg
     * title : 吃货福利！手把手教你做酸菜鱼 超极好吃
     * topicDesc : 关于知识,关于世界
     * topicImg : http://vimg3.ws.126.net/image/snapshot/2016/12/L/S/VC7T21ILS.jpg
     * topicName : 大开眼界
     * topicSid : VC7T21ILQ
     * vid : VC8F5KVH9
     * videoTopic : {"alias":"关于知识,关于世界","ename":"T1482313997359","tid":"T1482313997359","tname":"大开眼界"}
     * videosource : 新媒体
     */

    var cover: String? = null
    var length: Int = 0
    var m3u8_url: String? = null
    var m3u8Hd_url: String? = null
    var mp4_url: String? = null
    var mp4Hd_url: String? = null
    var playCount: Int = 0
    var ptime: String? = null
    var replyid: String? = null
    var title: String? = null
    var topicDesc: String? = null
    var topicName: String? = null
    var topicSid: String? = null
    var vid: String? = null
    //    private VideoTopicBean videoTopic;
    //    public VideoTopicBean getVideoTopic() {
    //        return videoTopic;
    //    }
    //
    //    public void setVideoTopic(VideoTopicBean videoTopic) {
    //        this.videoTopic = videoTopic;
    //    }

    var videosource: String? = null

    constructor(parcel: Parcel) : this() {
        cover = parcel.readString()
        length = parcel.readInt()
        m3u8_url = parcel.readString()
        m3u8Hd_url = parcel.readString()
        mp4_url = parcel.readString()
        mp4Hd_url = parcel.readString()
        playCount = parcel.readInt()
        ptime = parcel.readString()
        replyid = parcel.readString()
        title = parcel.readString()
        topicDesc = parcel.readString()
        topicName = parcel.readString()
        topicSid = parcel.readString()
        vid = parcel.readString()
        videosource = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(cover)
        dest.writeInt(length)
        dest.writeString(m3u8_url)
        dest.writeString(m3u8Hd_url)
        dest.writeString(mp4_url)
        dest.writeString(mp4Hd_url)
        dest.writeInt(playCount)
        dest.writeString(ptime)
        dest.writeString(replyid)
        dest.writeString(title)
        dest.writeString(topicDesc)
        dest.writeString(topicName)
        dest.writeString(topicSid)
        dest.writeString(vid)
        dest.writeString(videosource)
    }

    //    public static class VideoTopicBean {
    //        /**
    //         * alias : 关于知识,关于世界
    //         * ename : T1482313997359
    //         * tid : T1482313997359
    //         * tname : 大开眼界
    //         */
    //
    //        private String alias;
    //        private String ename;
    //        private String tid;
    //        private String tname;
    //
    //        public String getAlias() {
    //            return alias;
    //        }
    //
    //        public void setAlias(String alias) {
    //            this.alias = alias;
    //        }
    //
    //        public String getEname() {
    //            return ename;
    //        }
    //
    //        public void setEname(String ename) {
    //            this.ename = ename;
    //        }
    //
    //        public String getTid() {
    //            return tid;
    //        }
    //
    //        public void setTid(String tid) {
    //            this.tid = tid;
    //        }
    //
    //        public String getTname() {
    //            return tname;
    //        }
    //
    //        public void setTname(String tname) {
    //            this.tname = tname;
    //        }
    //    }

    override fun toString(): String {
        return "VideoBean{" +
                "cover='" + cover + '\''.toString() +
                ", length=" + length +
                ", m3u8_url='" + m3u8_url + '\''.toString() +
                ", m3u8Hd_url='" + m3u8Hd_url + '\''.toString() +
                ", mp4_url='" + mp4_url + '\''.toString() +
                ", mp4Hd_url='" + mp4Hd_url + '\''.toString() +
                ", playCount=" + playCount +
                ", ptime='" + ptime + '\''.toString() +
                ", replyid='" + replyid + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", topicDesc='" + topicDesc + '\''.toString() +
                ", topicName='" + topicName + '\''.toString() +
                ", topicSid='" + topicSid + '\''.toString() +
                ", vid='" + vid + '\''.toString() +
                ", videosource='" + videosource + '\''.toString() +
                '}'.toString()
    }

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<VideoBean> = object : Parcelable.Creator<VideoBean> {

            override fun createFromParcel(source: Parcel): VideoBean {
                val videoBean = VideoBean()
                videoBean.cover = source.readString()
                videoBean.length = source.readInt()
                videoBean.m3u8_url = source.readString()
                videoBean.m3u8Hd_url = source.readString()
                videoBean.mp4_url = source.readString()
                videoBean.mp4Hd_url = source.readString()
                videoBean.playCount = source.readInt()
                videoBean.replyid = source.readString()
                videoBean.title = source.readString()
                videoBean.topicDesc = source.readString()
                videoBean.topicName = source.readString()
                videoBean.vid = source.readString()
                videoBean.videosource = source.readString()

                return videoBean
            }

            override fun newArray(size: Int): Array<VideoBean?> {
                return arrayOfNulls(size)
            }
        }
    }

//    companion object CREATOR : Parcelable.Creator<VideoBean> {
//        override fun createFromParcel(parcel: Parcel): VideoBean {
//            return VideoBean(parcel)
//        }
//
//        override fun newArray(size: Int): Array<VideoBean?> {
//            return arrayOfNulls(size)
//        }
//    }

}
