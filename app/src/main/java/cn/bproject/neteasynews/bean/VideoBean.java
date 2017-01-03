package cn.bproject.neteasynews.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bei on 2016/12/28.
 * 视频列表单个条目与视频详情页的数据基本相同
 * 不同点在于视频列表的m3u8_url和mp4_url的视频链接采用的是https协议，而视频详情页采用的是http协议
 */

public class VideoBean implements Parcelable {

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

    private String cover;
    private int length;
    private String m3u8_url;
    private String m3u8Hd_url;
    private String mp4_url;
    private String mp4Hd_url;
    private int playCount;
    private String ptime;
    private String replyid;
    private String title;
    private String topicDesc;
    private String topicName;
    private String topicSid;
    private String vid;
    //    private VideoTopicBean videoTopic;
    private String videosource;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicSid() {
        return topicSid;
    }

    public void setTopicSid(String topicSid) {
        this.topicSid = topicSid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

//    public VideoTopicBean getVideoTopic() {
//        return videoTopic;
//    }
//
//    public void setVideoTopic(VideoTopicBean videoTopic) {
//        this.videoTopic = videoTopic;
//    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cover);
        dest.writeInt(length);
        dest.writeString(m3u8_url);
        dest.writeString(m3u8Hd_url);
        dest.writeString(mp4_url);
        dest.writeString(mp4Hd_url);
        dest.writeInt(playCount);
        dest.writeString(ptime);
        dest.writeString(replyid);
        dest.writeString(title);
        dest.writeString(topicDesc);
        dest.writeString(topicName);
        dest.writeString(topicSid);
        dest.writeString(vid);
        dest.writeString(videosource);
    }

    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {

        @Override
        public VideoBean createFromParcel(Parcel source) {
            VideoBean videoBean = new VideoBean();
            videoBean.cover = source.readString();
            videoBean.length = source.readInt();
            videoBean.m3u8_url = source.readString();
            videoBean.m3u8Hd_url = source.readString();
            videoBean.mp4_url = source.readString();
            videoBean.mp4Hd_url = source.readString();
            videoBean.playCount = source.readInt();
            videoBean.replyid = source.readString();
            videoBean.title = source.readString();
            videoBean.topicDesc = source.readString();
            videoBean.topicName = source.readString();
            videoBean.vid = source.readString();
            videoBean.videosource = source.readString();

            return videoBean;
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

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

    @Override
    public String toString() {
        return "VideoBean{" +
                "cover='" + cover + '\'' +
                ", length=" + length +
                ", m3u8_url='" + m3u8_url + '\'' +
                ", m3u8Hd_url='" + m3u8Hd_url + '\'' +
                ", mp4_url='" + mp4_url + '\'' +
                ", mp4Hd_url='" + mp4Hd_url + '\'' +
                ", playCount=" + playCount +
                ", ptime='" + ptime + '\'' +
                ", replyid='" + replyid + '\'' +
                ", title='" + title + '\'' +
                ", topicDesc='" + topicDesc + '\'' +
                ", topicName='" + topicName + '\'' +
                ", topicSid='" + topicSid + '\'' +
                ", vid='" + vid + '\'' +
                ", videosource='" + videosource + '\'' +
                '}';
    }
}
