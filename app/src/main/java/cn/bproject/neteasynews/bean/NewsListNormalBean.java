package cn.bproject.neteasynews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 * 普通情况下的新闻列表页
 * 注意：
 * imgextra为额外图片列表，有些新闻有，有些没有
 * hasAD：如果值为 1，则imgsrc是横屏大图
 */

public class NewsListNormalBean {

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
    private String postid;
    // 网页链接地址
    private String url_3w;
    private int votecount;
    // 回帖人数
    private int replyCount;
    private String skipID;
    private String ltitle;
    // 文章描述
    private String digest;
    private String skipType;
    // 手机版URL地址
    private String url;
    private String specialID;
    // 新闻文档ID
    private String docid;
    // 新闻标题
    private String title;
    // 新闻来源
    private String source;
    // 优先级
    private int priority;
    private String lmodify;
    private String boardid;
    // 副标题
    private String subtitle;
    // 图片链接
    private String imgsrc;
    // 发布时间
    private String ptime;
    // 是否banner新闻
    private int hasAD;
    // 新闻是否为图片新闻
    private String photosetID;

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    // 额外图片
    private List<ImgextraBean> imgextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/416fb909a7be4ef0994a049dc72d798820161226090753.png
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        @Override
        public String toString() {
            return "ImgextraBean{" +
                    "imgsrc='" + imgsrc + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsListNormalBean{" +
                "postid='" + postid + '\'' +
                ", url_3w='" + url_3w + '\'' +
                ", votecount=" + votecount +
                ", replyCount=" + replyCount +
                ", skipID='" + skipID + '\'' +
                ", ltitle='" + ltitle + '\'' +
                ", digest='" + digest + '\'' +
                ", skipType='" + skipType + '\'' +
                ", url='" + url + '\'' +
                ", specialID='" + specialID + '\'' +
                ", docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", priority=" + priority +
                ", lmodify='" + lmodify + '\'' +
                ", boardid='" + boardid + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", ptime='" + ptime + '\'' +
                ", hasAD=" + hasAD +
                ", imgextra=" + imgextra +
                '}';
    }
}
