package cn.bproject.neteasynews.Utils;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.bean.NewsBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CategoryDataUtils {
    public static List<NewsBean> getCategoryBeans(){
        List<NewsBean>  beans=new ArrayList<>();
        beans.add(new NewsBean("要闻", "http://c.m.163.com/nc/article/list/T1467284926140/0-20.html","要闻"));
        beans.add(new NewsBean("科技","http://c.m.163.com/nc/article/list/T1348649580692/0-20.html","科技"));
        beans.add(new NewsBean("财经","http://c.m.163.com/nc/article/list/T1348648756099/0-20.html","财经"));
        beans.add(new NewsBean("时尚","http://c.m.163.com/nc/article/list/T1348650593803/0-20.html","时尚"));



//        财经：
//        http://c.m.163.com/nc/article/list/T1348648756099/0-20.html
//
//        科技：
//        http://c.m.163.com/nc/article/list/T1348649580692/0-20.html
//        时尚：
//        http://c.m.163.com/nc/article/list/T1348650593803/0-20.html




        return beans;
    }
}
