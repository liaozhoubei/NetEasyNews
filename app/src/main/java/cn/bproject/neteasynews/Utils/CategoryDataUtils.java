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
        beans.add(new NewsBean("头条","","T1348647909107"));
        beans.add(new NewsBean("要闻","","T1467284926140"));
        beans.add(new NewsBean("科技","","T1348649580692"));
        beans.add(new NewsBean("财经","","T1348648756099"));
        beans.add(new NewsBean("社会","","T1348648037603"));
        beans.add(new NewsBean("军事","","T1348648141035"));
        beans.add(new NewsBean("娱乐","","T1348648517839"));
        beans.add(new NewsBean("体育","","T1348649079062"));
        beans.add(new NewsBean("数码","","T1348649776727"));



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
