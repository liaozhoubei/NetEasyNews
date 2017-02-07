package cn.bproject.neteasynews.bean;

import java.util.List;

/**
 * Created by Bei on 2016/12/27.
 * 获取网易新闻频道的bean类
 * 频道URL： http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
 * 去除一些无法使用及已经使用在图片、视频中的频道，将获取挑选后的json数据放置在assets目录中
 * channel.txt不包含一下栏目：
 * 视频T1457068979049
 * 段子T1419316284722
 * 图片T1419316384474
 * 本地T1419316531256
 * 热点T1427878984398
 * 网易号T1449126525962
 * 美女T1456112189138
 * 萌宠T1456112438822
 * ···
 */

public class Channel {

    private List<TListBean> tList;

    public List<TListBean> getTList() {
        return tList;
    }

    public void setTList(List<TListBean> tList) {
        this.tList = tList;
    }

    public static class TListBean {
        /**
         * template : normal1
         * topicid : 000181S1
         * hasCover : false
         * alias : The Truth
         * subnum : 超过1000万
         * recommendOrder : 0
         * isNew : 0
         * hashead : 1
         * img : http://img2.cache.netease.com/m/newsapp/banner/zhenhua.png
         * isHot : 0
         * hasIcon : true
         * cid : C1348654575297
         * recommend : 0
         * headLine : false
         * hasAD : 1
         * color :
         * bannerOrder : 105
         * tname : 独家
         * ename : zhenhua
         * showType : comment
         * special : 0
         * tid : T1370583240249
         * ad_type : 0
         * weburl : http://www.163.com/
         * tag : hot
         * tagDate : 2015-04-16 14:53:09.0
         */

        private String template;
        private String topicid;
        private String alias;
        private String cid;
        private String tname;
        private String ename;
        private String tid;


        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTopicid() {
            return topicid;
        }

        public void setTopicid(String topicid) {
            this.topicid = topicid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
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

    }
}
