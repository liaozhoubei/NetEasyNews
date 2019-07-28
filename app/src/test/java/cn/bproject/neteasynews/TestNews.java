package cn.bproject.neteasynews;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TestNews implements Serializable {
    @SerializedName("T1467284926140")
    private List<NewsBean> newslist;

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewsBean {
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

        private String template;
        private String lmodify;
        private String source;
        private String postid;
        private String title;
        private String mtime;
        private int hasImg;
        private String articleType;
        private String topic_background;
        private String digest;
        private String boardid;
        private String alias;
        private int hasAD;
        private String imgsrc;
        private String ptime;
        private String daynum;
        private int hasHead;
        private int imgType;
        private int order;
        private int votecount;
        private boolean hasCover;
        private String docid;
        private String tname;
        private String url_3w;
        private int priority;
        private String url;
        private String ename;
        private int replyCount;
        private String ltitle;
        private boolean hasIcon;
        private String subtitle;
        private String cid;
        private String pixel;
        private String skipID;
        private String specialID;
        private String skipType;
        private List<?> editor;

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getArticleType() {
            return articleType;
        }

        public void setArticleType(String articleType) {
            this.articleType = articleType;
        }

        public String getTopic_background() {
            return topic_background;
        }

        public void setTopic_background(String topic_background) {
            this.topic_background = topic_background;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
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

        public String getDaynum() {
            return daynum;
        }

        public void setDaynum(String daynum) {
            this.daynum = daynum;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getImgType() {
            return imgType;
        }

        public void setImgType(int imgType) {
            this.imgType = imgType;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getSpecialID() {
            return specialID;
        }

        public void setSpecialID(String specialID) {
            this.specialID = specialID;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public List<?> getEditor() {
            return editor;
        }

        public void setEditor(List<?> editor) {
            this.editor = editor;
        }
    }
}
