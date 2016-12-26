package cn.bproject.neteasynews.common;

/**
 * Created by Administrator on 2016/12/26.
 * 抓取网易新闻api
 *
 * URL 示例：
 * 栏目列表：
 http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
 新闻：
 尾部：0为开始的新闻，10为一页显示多少条新闻
 头条：
 http://c.m.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&offset=0&size=10
 要闻：
 http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
 体育：
 http://c.m.163.com/dlist/article/dynamic?from=T1348649079062&offset=0&size=10&fn=1&&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
 财经：
 http://c.m.163.com/nc/article/list/T1348648756099/0-20.html
 科技：
 http://c.m.163.com/nc/article/list/T1348649580692/0-20.html
 时尚：
 http://c.m.163.com/nc/article/list/T1348650593803/0-20.html
 直播：
 http://data.live.126.net/livechannel/previewlist.json
 段子
 http://c.m.163.com/recommend/getChanListNews?channel=T1419316284722&size=10&offset=0&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
 独家：
 http://c.m.163.com/nc/article/list/T1370583240249/0-20.html
 政务：
 http://c.m.163.com/nc/article/list/T1414142214384/0-20.html
 哒哒趣闻：
 http://c.m.163.com/nc/article/list/T1444289532601/0-20.html
 二次元：
 http://c.m.163.com/nc/article/list/T1481105123675/0-20.html
 图片：
 http://c.m.163.com/photo/api/list/0096/54GI0096.json
 美女：
 http://c.m.163.com/recommend/getChanListNews?channel=T1456112189138&size=10&offset=0&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
 视频：
 http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20

 图片新闻详情：
 http://c.m.163.com/photo/api/set/0009/13897.json
 新闻详情：
 docid为网易文章
 链接格式：http://c.m.163.com/nc/article/   + docid + /full.html
 如下：
 http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html

 城市列表：
 http://m.163.com/special/newsclient/cities.html
 *
 *
 */

public class Api {
    public static final String host = "http://c.m.163.com/";
    /**
     * 特殊频道使用，如：推荐视频、美女图片
     */
    public static final String Recommend = host + "recommend/getChanListNews?channel=";
    // 特殊频道的结尾
    public static final String RecommendEndUrl = "&size=10&offset=";

    // 普通新闻栏目的结尾，注意要在签名添加新闻从那一条开始获取
    // 如 ：http://c.m.163.com/nc/article/list/T1467284926140/0-20.html，获取最新的20条新闻
    // http://c.m.163.com/nc/article/list/T1467284926140/20-20.html，获取从第20条开始的后面20条新闻
    public static final String endUrl = "-20.html";


    /**
     * 新闻文章详情页链接尾部
     * 示例：http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html
     * 其中postId ：C8T2G5QL0511A99L
      */
    public static final String endDetailUrl = "/full.html";
    // 新闻详情
    public static final String NewDetail = host + "/nc/article/";
    /**
     * 头条新闻
     * http://c.m.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&offset=0&size=10
     */
    public static final String TopUrl = host + "nc/article/headline/";
    public static final String TopId = "T1348647909107";
    // 普通栏目前缀
    public static final String CommonUrl = host + "nc/article/list/";
    // 要闻ID
    // 链接：http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
    public static final String YaoWenId = "T1467284926140";

    // 政务ID
    public static final String ZhengWuId = "T1414142214384";
    // 独家
    public static final String DuJiaWuId = "T1370583240249";
    // 足球
    public static final String FootId = "T1399700447917";
    // 娱乐
    public static final String YuLeId = "T1348648517839";
    // 体育
    public static final String TiYuId = "T1348649079062";
    // 财经
    public static final String CaiJingId = "T1348648756099";
    // 科技
    public static final String KeJiId = "T1348649580692";
    // 电影
    public static final String DianYingId = "T1348648650048";
    // 汽车
    public static final String QiChiId = "T1348654060988";
    // 笑话
    public static final String XiaoHuaId = "T1350383429665";
    // 游戏
    public static final String YouXiId = "T1348654151579";
    // 时尚
    public static final String ShiShangId = "T1348650593803";
    // 情感
    public static final String QingGanId = "T1348650839000";
    // 精选
    public static final String JingXuanId = "T1370583240249";
    // 电台
    public static final String DianTaiId = "T1379038288239";
    // nba
    public static final String NBAId = "T1348649145984";
    // 数码
    public static final String ShuMaId = "T1348649776727";
    // 数码
    public static final String YiDongId = "T1351233117091";
    // 彩票
    public static final String CaiPiaoId = "T1356600029035";
    // 教育
    public static final String JiaoYuId = "T1348654225495";
    // 论坛
    public static final String LunTanId = "T1349837670307";
    // 旅游
    public static final String LvYouId = "T1348654204705";
    // 手机
    public static final String ShouJiId = "T1348649654285";
    // 博客
    public static final String BoKeId = "T1349837698345";
    // 社会
    public static final String SheHuiId = "T1348648037603";
    // 家居
    public static final String JiaJuId = "T1348654105308";
    // 暴雪游戏
    public static final String BaoXueId = "T1397016069906";
    // 亲子
    public static final String QinZiId = "T1397116135282";
    // CBA
    public static final String CBAId = "T1348649475931";
    // 消息
    public static final String MsgId = "T1371543208049";

    // 哒哒趣闻
    public static final String DaDaId = "T1444289532601";
    // 二次元
    public static final String ErCiYuanId = "T1481105123675";

    public static final String FangChanId = "5YyX5Lqs";

    // 美 图
    // http://c.m.163.com/recommend/getChanRecomNews?channel=T1456112189138&size=0&offset=10
    public static final String MeiNvId = "T1456112189138";
    public static final String TuPianMeiNv = Recommend +  MeiNvId + "&size=10&offset=0";

    //图片
    //public static final String TuPian = "http://c.m.163.com/photo/api/list/0096/54GI0096.json";


    // 初始图集
    public static final String TuJiInit = host
            + "photo/api/list/0096/54GI0096.json";// 42358.json

    public static final String TuJi = host
            + "photo/api/morelist/0096/54GI0096/";// 42358.json

    // 图集end
    public static final String TuJiEnd = ".json";


    //

    // 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public static final String Video = host + "nc/video/list/";
    public static final String VideoCenter = "/n/";
    public static final String videoEndUrl = "-20.html";
    // 热点视频
    public static final String VideoReDianId = "V9LG4B3A0";
    // 推荐视频
    // http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20
    public static final String VideorecommendId = "T1457068979049";
    //直播视频
    public static final String VideoLive = "http://data.live.126.net/livechannel/previewlist.json";


    // 以下视频为老旧视频，15年视频源
    // 娱乐视频
    public static final String VideoYuLeId = "V9LG4CHOR";
    // 搞笑视频
    public static final String VideoGaoXiaoId = "V9LG4E6VR";
    // 精品视频
    public static final String VideoJingPinId = "00850FRB";


}
