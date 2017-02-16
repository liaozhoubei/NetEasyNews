package cn.bproject.neteasynews.bean;

/**
 * Created by Bei on 2016/12/25.
 *
 */

public class NewsBean {
    private String title; // 分类Tab名称
//    private String href; // 分类点击地址
    private String tid; // 分类类型
    private String column;  //  图片的分类

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public NewsBean(){
        super();
    }

    public NewsBean(String title, String column, String tid) {
        this.title = title;
        this.column = column;
        this.tid = tid;
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

    public void setTid(String data_type) {
        this.tid = tid;
    }
}
