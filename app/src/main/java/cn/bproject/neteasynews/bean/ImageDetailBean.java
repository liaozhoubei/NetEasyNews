package cn.bproject.neteasynews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 * 图片新闻详情页实体类
 *
 * http://c.m.163.com/photo/api/set/0009/13897.json
 */

public class ImageDetailBean {


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
    private String cover;
    // 创建日期
    private String createdate;
    // 创建者
    private String creator;
    private String datatime;
    // 图片文字描述
    private String desc;
    // 图片总数
    private String imgsum;
    // 图片文字id
    private String postid;
    // 文章来源
    private String source;
    // 文章的url地址
    private String url;

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    private String setname;
    // 图片集合信息
    private List<PhotosBean> photos;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgsum() {
        return imgsum;
    }

    public void setImgsum(String imgsum) {
        this.imgsum = imgsum;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public static class PhotosBean {
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
        private String imgtitle;
        // 当前图片的URL地址
        private String imgurl;
        // 新闻url，不移动有
        private String newsurl;
        // 图片描述或者来源
        private String note;
        // 单张图片的url地址（网页版）
        private String photohtml;
        // 图集的文章id
        private String photoid;

        public String getImgtitle() {
            return imgtitle;
        }

        public void setImgtitle(String imgtitle) {
            this.imgtitle = imgtitle;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getNewsurl() {
            return newsurl;
        }

        public void setNewsurl(String newsurl) {
            this.newsurl = newsurl;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPhotohtml() {
            return photohtml;
        }

        public void setPhotohtml(String photohtml) {
            this.photohtml = photohtml;
        }

        public String getPhotoid() {
            return photoid;
        }

        public void setPhotoid(String photoid) {
            this.photoid = photoid;
        }
    }
}
