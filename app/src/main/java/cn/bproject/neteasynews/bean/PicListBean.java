package cn.bproject.neteasynews.bean;

import java.util.List;

/**
 * Created by liaozhoubei on 2016/12/30.
 */

public class PicListBean {

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
        private String desc;
        // 创建时间
        private String createdate;
        private String setname;
        private String cover;
        private String setid;
        private String seturl;
        // 发布时间
        private String datetime;
        // 图片总数
        private String imgsum;
        private List<String> pics;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getSetname() {
            return setname;
        }

        public void setSetname(String setname) {
            this.setname = setname;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSetid() {
            return setid;
        }

        public void setSetid(String setid) {
            this.setid = setid;
        }

        public String getSeturl() {
            return seturl;
        }

        public void setSeturl(String seturl) {
            this.seturl = seturl;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getImgsum() {
            return imgsum;
        }

        public void setImgsum(String imgsum) {
            this.imgsum = imgsum;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

    @Override
    public String toString() {
        return "PicListBean{" +
                "setname='" + setname + '\'' +
                ", cover='" + cover + '\'' +
                ", createdate='" + createdate + '\'' +
                '}';
    }


    //    }




}
