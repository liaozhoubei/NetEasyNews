package cn.bproject.neteasynews.util;


import cn.bproject.neteasynews.bean.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CategoryDataUtils {
    public static List<ProjectChannelBean.TListBean> getChannelCategoryBeans(){
        List<ProjectChannelBean.TListBean> beans=new ArrayList<>();
//        ProjectChannelBean.TListBean toutiao = new ProjectChannelBean.TListBean();
//        toutiao.setTid( "T1348647909107");
//        toutiao.setTname("头条");
//        beans.add(toutiao);
        ProjectChannelBean.TListBean yaowen = new ProjectChannelBean.TListBean();
        yaowen.setTid( "T1467284926140");
        yaowen.setTname("要闻");
        beans.add(yaowen);

        ProjectChannelBean.TListBean keji = new ProjectChannelBean.TListBean();
        keji.setTid( "T1348649580692");
        keji.setTname("科技");
        beans.add(keji);

        ProjectChannelBean.TListBean caijing = new ProjectChannelBean.TListBean();
        caijing.setTid( "T1348648756099");
        caijing.setTname("财经");
        beans.add(caijing);

//        ProjectChannelBean.TListBean shehui = new ProjectChannelBean.TListBean();
//        shehui.setTid( "T1348654085632");
//        shehui.setTname("房产");
//        beans.add(shehui);
        ProjectChannelBean.TListBean junshi = new ProjectChannelBean.TListBean();
        junshi.setTid( "T1348648141035");
        junshi.setTname("军事");
        beans.add(junshi);
        ProjectChannelBean.TListBean yule = new ProjectChannelBean.TListBean();
        yule.setTid( "T1348648517839");
        yule.setTname("娱乐");
        beans.add(yule);
        ProjectChannelBean.TListBean tiyu = new ProjectChannelBean.TListBean();
        tiyu.setTid( "T1348649079062");
        tiyu.setTname("体育");
        beans.add(tiyu);
        ProjectChannelBean.TListBean shuma = new ProjectChannelBean.TListBean();
        shuma.setTid( "T1348649776727");
        shuma.setTname("数码");
        beans.add(shuma);


        return beans;
    }

    public static List<ProjectChannelBean.TListBean> getPicCategoryBeans(){
        List<ProjectChannelBean.TListBean> beans=new ArrayList<>();
        // GirdView排版
        ProjectChannelBean.TListBean recommed = new ProjectChannelBean.TListBean();
        recommed.setTname("推荐");
        recommed.setTid("6LRK0031,6LRI0031");
        recommed.setColumn("0031");
        beans.add(recommed);
        // ListView排版
        ProjectChannelBean.TListBean news = new ProjectChannelBean.TListBean();
        news.setTname("新闻");
        news.setTid("00AP0001,3R710001,4T8E0001");
        news.setColumn("0001");
        beans.add(news);
        //  ListView排版
        ProjectChannelBean.TListBean hot = new ProjectChannelBean.TListBean();
        hot.setTname("热点");
        hot.setTid("00AN0001,00AO0001");
        hot.setColumn("0001");
        beans.add(hot);
        // GirdView排版
        ProjectChannelBean.TListBean star = new ProjectChannelBean.TListBean();
        star.setTname("明星");
        star.setTid("00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003");
        star.setColumn("0003");
        beans.add(star);


//        // 图片新闻尾部，需要在签名添加参数，可获得从某条新闻之后的20条新闻
//        // 示例 ： http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json
//        public static final String endPicture = "/20.json";
//        // 图片
//        public static final String specialPictureId = "T1419316384474";
//        // 推荐图片：0031/6LRK0031,6LRI0031/    应使用瀑布流
//        public static final int RecommendPictureId = 0031;
//        public static final String RecommendPicture = PictureUrl + RecommendPictureId + "/6LRK0031,6LRI0031/";
//        // 新闻图片：0001/00AP0001,3R710001,4T8E0001/    横向排版
//        public static final int NewsPictureId = 0001;
//        public static final String NewsPicture = PictureUrl + NewsPictureId + "/00AP0001,3R710001,4T8E0001/";
//        // 热点图片：0001/00AN0001,00AO0001/     横向排版
//        public static final String HotPicture = PictureUrl + NewsPictureId +  "0001/00AN0001,00AO0001/";
//        // 明星图片：0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/      瀑布流排版
//        public static final int StarPictureId = 0003;
//        public static final String StarPicture = PictureUrl + StarPictureId + "/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/";

        return beans;
    }


}
