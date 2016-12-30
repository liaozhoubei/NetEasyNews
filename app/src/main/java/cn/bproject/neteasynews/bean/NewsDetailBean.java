package cn.bproject.neteasynews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 *
 * 先获得文字的docid，然后通过json解析，获取jsonObject，转化为String，在通过Gson解析
 * 示例：
 *  docid ： C9741113000181BR
 *
 * 		JSONObject jsonObject = new JSONObject(json);
        String detail = jsonObject.getJSONObject("C9741113000181BR").toString();
        Gson gson = new Gson();
        NewsDetailBean newsDetailBean = gson.fromJson(detail, NewsDetailBean.class);
 *
 */

public class NewsDetailBean {
        private String body;
        private int replyCount;
        private String shareLink;
        private String digest;

        private String docid;
        private boolean picnews;
        private String title;
        private String tid;
        private String template;


        private String source;

        private String voicecomment;
        private String ptime;
        private List<?> users;
        private List<?> ydbaike;
        private List<LinkBean> link;
        private List<ImgBean> img;
        private List<VotesBean> votes;
        private List<TopiclistNewsBean> topiclist_news;
        private List<?> topiclist;
        private List<VideoBean> video;
        private List<?> boboList;


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }



        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public boolean isPicnews() {
            return picnews;
        }

        public void setPicnews(boolean picnews) {
            this.picnews = picnews;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }



        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }



        public String getVoicecomment() {
            return voicecomment;
        }

        public void setVoicecomment(String voicecomment) {
            this.voicecomment = voicecomment;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<?> getUsers() {
            return users;
        }

        public void setUsers(List<?> users) {
            this.users = users;
        }

        public List<?> getYdbaike() {
            return ydbaike;
        }

        public void setYdbaike(List<?> ydbaike) {
            this.ydbaike = ydbaike;
        }

        public List<LinkBean> getLink() {
            return link;
        }

        public void setLink(List<LinkBean> link) {
            this.link = link;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public List<VotesBean> getVotes() {
            return votes;
        }

        public void setVotes(List<VotesBean> votes) {
            this.votes = votes;
        }

        public List<TopiclistNewsBean> getTopiclist_news() {
            return topiclist_news;
        }

        public void setTopiclist_news(List<TopiclistNewsBean> topiclist_news) {
            this.topiclist_news = topiclist_news;
        }

        public List<?> getTopiclist() {
            return topiclist;
        }

        public void setTopiclist(List<?> topiclist) {
            this.topiclist = topiclist;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }



        public List<?> getBoboList() {
            return boboList;
        }

        public void setBoboList(List<?> boboList) {
            this.boboList = boboList;
        }


        public static class LinkBean {
            /**
             * ref : <!--link0-->
             * title : 你的小目标达成了吗？
             * imgsrc : http://cms-bucket.nosdn.127.net/4bba72ca129c45b2bea3038fe450ef6420161223202339.jpeg
             * digest : 距存够2W的小目标还差3W
             * type : linkCard
             * href : http://3g.163.com/news/16/1223/20/C90EQK7D000181BT.html
             */

            private String ref;
            private String title;
            private String imgsrc;
            private String digest;
            private String type;
            private String href;

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class ImgBean {
            /**
             * ref : <!--IMG#0-->
             * pixel : 690*692
             * alt :
             * src : http://cms-bucket.nosdn.127.net/c85eed9a823043249c9eeb99bca7f69e20161226084157.jpeg
             */

            private String ref;
            private String pixel;
            private String alt;
            private String src;

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getPixel() {
                return pixel;
            }

            public void setPixel(String pixel) {
                this.pixel = pixel;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class VotesBean {
            /**
             * digest : 你们考过研吗？
             * date : 2016-12-26
             * url :
             * option_type : 0
             * date_type : 0
             * sumnum : 1109
             * docid :
             * ref : <!--@@PKVOTEID=58378-->
             * voteid : 58378
             * voteitem : [{"id":"247999","num":451,"name":"考过研，我当时____。"},{"id":"248000","num":658,"name":"没考过，想想都挺累。"}]
             * imgsrc :
             * voteType : pkVote
             * question : 你们考过研吗？
             */

            private String digest;
            private String date;
            private String url;
            private int option_type;
            private int date_type;
            private int sumnum;
            private String docid;
            private String ref;
            private String voteid;
            private String imgsrc;
            private String voteType;
            private String question;
            private List<VoteitemBean> voteitem;

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getOption_type() {
                return option_type;
            }

            public void setOption_type(int option_type) {
                this.option_type = option_type;
            }

            public int getDate_type() {
                return date_type;
            }

            public void setDate_type(int date_type) {
                this.date_type = date_type;
            }

            public int getSumnum() {
                return sumnum;
            }

            public void setSumnum(int sumnum) {
                this.sumnum = sumnum;
            }

            public String getDocid() {
                return docid;
            }

            public void setDocid(String docid) {
                this.docid = docid;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getVoteid() {
                return voteid;
            }

            public void setVoteid(String voteid) {
                this.voteid = voteid;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getVoteType() {
                return voteType;
            }

            public void setVoteType(String voteType) {
                this.voteType = voteType;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public List<VoteitemBean> getVoteitem() {
                return voteitem;
            }

            public void setVoteitem(List<VoteitemBean> voteitem) {
                this.voteitem = voteitem;
            }

            public static class VoteitemBean {
                /**
                 * id : 247999
                 * num : 451
                 * name : 考过研，我当时____。
                 */

                private String id;
                private int num;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class TopiclistNewsBean {
            /**
             * hasCover : true
             * subnum : 超过1000万
             * alias : FUNNY MOMENT
             * tname : 轻松一刻
             * ename : qingsongyike
             * tid : T1350383429665
             * cid : C1348654575297
             */

            private boolean hasCover;
            private String subnum;
            private String alias;
            private String tname;
            private String ename;
            private String tid;
            private String cid;

            public boolean isHasCover() {
                return hasCover;
            }

            public void setHasCover(boolean hasCover) {
                this.hasCover = hasCover;
            }

            public String getSubnum() {
                return subnum;
            }

            public void setSubnum(String subnum) {
                this.subnum = subnum;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }
        }

        public static class VideoBean {
            /**
             * commentid : C84JV7AL00964ILB
             * topicid : 0096
             * broadcast : in
             * videosource : 其他
             * commentboard : mobile_bbs
             * appurl :
             * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/rbPOL1739-mobile.mp4
             * url_m3u8 : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
             * size :
             * ref : <!--VIDEO#0-->
             * cover : http://vimg2.ws.126.net/image/snapshot/2016/12/S/T/VC84JV4ST.jpg
             * url_mp4 : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
             * alt : 年终策划视频《小时代之狗血2017》
             * mp4_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4
             * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/movie_index.m3u8
             * m3u8_url : http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/movie_index.m3u8
             * vid : VC84JV7AL
             */

            private String commentid;
            private String topicid;
            private String broadcast;
            private String videosource;
            private String commentboard;
            private String appurl;
            private String mp4Hd_url;
            private String url_m3u8;
            private String size;
            private String ref;
            private String cover;
            private String url_mp4;
            private String alt;
            private String mp4_url;
            private String m3u8Hd_url;
            private String m3u8_url;
            private String vid;

            public String getCommentid() {
                return commentid;
            }

            public void setCommentid(String commentid) {
                this.commentid = commentid;
            }

            public String getTopicid() {
                return topicid;
            }

            public void setTopicid(String topicid) {
                this.topicid = topicid;
            }

            public String getBroadcast() {
                return broadcast;
            }

            public void setBroadcast(String broadcast) {
                this.broadcast = broadcast;
            }

            public String getVideosource() {
                return videosource;
            }

            public void setVideosource(String videosource) {
                this.videosource = videosource;
            }

            public String getCommentboard() {
                return commentboard;
            }

            public void setCommentboard(String commentboard) {
                this.commentboard = commentboard;
            }

            public String getAppurl() {
                return appurl;
            }

            public void setAppurl(String appurl) {
                this.appurl = appurl;
            }

            public String getMp4Hd_url() {
                return mp4Hd_url;
            }

            public void setMp4Hd_url(String mp4Hd_url) {
                this.mp4Hd_url = mp4Hd_url;
            }

            public String getUrl_m3u8() {
                return url_m3u8;
            }

            public void setUrl_m3u8(String url_m3u8) {
                this.url_m3u8 = url_m3u8;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getUrl_mp4() {
                return url_mp4;
            }

            public void setUrl_mp4(String url_mp4) {
                this.url_mp4 = url_mp4;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getMp4_url() {
                return mp4_url;
            }

            public void setMp4_url(String mp4_url) {
                this.mp4_url = mp4_url;
            }

            public String getM3u8Hd_url() {
                return m3u8Hd_url;
            }

            public void setM3u8Hd_url(String m3u8Hd_url) {
                this.m3u8Hd_url = m3u8Hd_url;
            }

            public String getM3u8_url() {
                return m3u8_url;
            }

            public void setM3u8_url(String m3u8_url) {
                this.m3u8_url = m3u8_url;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }
        }

    @Override
    public String toString() {
        return "NewsDetailBean{" +
                "body='" + body + '\'' +
                '}';
    }
}
