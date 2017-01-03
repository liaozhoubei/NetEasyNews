package cn.bproject.neteasynews.common;

/**
 * Created by Administrator on 2016/12/26.
 * 抓取网易新闻api
 *
 * URL 示例：
 * 栏目列表：
 * http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
 * 	其他类型的图片api：
 * 	http://c.m.163.com/photo/api/list/0096/54GI0096.json
**/

public class Api {



    public static final String host = "http://c.m.163.com/";

    // 普通栏目前缀, 示例： http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
    public static final String CommonUrl = host + "nc/article/list/";

    // 图片栏目的前缀， 示例： http://pic.news.163.com/photocenter/api/list/0031/6LRK0031,6LRI0031/0/20.json
    // 推荐图片：0031， 新闻图片： 0001 ， 明星图片：0003
    public static final String PictureUrl = "http://pic.news.163.com/photocenter/api/list/";

    // 特殊频道前缀：热点、网易号适用
    public static final String SpecialColumn1 = "recommend/getSubDocPic?";

    // 特殊频道前缀:	视频/段子、美女、萌宠适用
    public static final String SpecialColumn2 = "recommend/getChanListNews?channel=";
    // 文章详情前缀
    public static final String DetailUrl = host + "nc/article/";

    // 普通新闻栏目的结尾，注意要在前面添加新闻从那一条开始获取
    // 如 ：http://c.m.163.com/nc/article/list/T1467284926140/0-20.html，获取最新的20条新闻
    // http://c.m.163.com/nc/article/list/T1467284926140/20-20.html，获取从第20条开始的后面20条新闻
    public static final String endUrl = "-20.html";

    // 所有特殊频道的结尾，如：热点、网易号、段子、美女、萌宠
    public static final String SpecialendUrl = "&size=10&offset=";

    /**
     * 新闻文章详情页链接尾部
     * 示例：http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html
     * 其中postId ：C8T2G5QL0511A99L
     */
    public static final String endDetailUrl = "/full.html";

    /**
     * 图片新闻详情页连接
     * 链接示例： http://c.m.163.com/photo/api/set/0031/13897.json
     * 图片详情页：0031为图片频道（列表连接后面的0031），13897为图片新闻setid
     */
    public static final String PictureDetailUrl = host + "photo/api/set/";
    // 图片新闻详情页结尾
    public static final String endPictureDetailUrl = ".json";


    /**
     * 视频详情链接
     *  示例： http://c.m.163.com/nc/video/detail/VC5BA022H.html
     *  其中VC5BA022H是视频的vid
     */
    public static final String videoDetailUrl = host + "nc/video/detail/";
    // 视频详情链接尾部
    public static final String EndUrlVideoDetailUrl = ".html";


    // 独家
    public static final String zhenhuaId = "T1370583240249";

    // NBA
    public static final String NBAId = "T1348649145984";

    // 头条
    public static final String toutiaoId = "T1348647909107";

    // 社会
    public static final String shehuiId = "T1348648037603";

    // 历史
    public static final String lishiId = "T1368497029546";

    // 军事
    public static final String junshiId = "T1348648141035";

    // 哒哒趣闻
    public static final String dadaId = "T1444289532601";

    // 航空
    public static final String aviationId = "T1474271789612";

    // 要闻
    public static final String yaowenspecialId = "T1467284926140";

    // 娱乐
    public static final String yuleId = "T1348648517839";

    // 影视歌
    public static final String dianyingId = "T1348648650048";

    // 财经
    public static final String caijingId = "T1348648756099";

    // 股票
    public static final String stockId = "T1473054348939";

    // 彩票
    public static final String caipiaoId = "T1356600029035";

    // 体育
    public static final String tiyuId = "T1348649079062";

    // 中国足球
    public static final String zhongchaoId = "T1348649503389";

    // 国际足球
    public static final String zuqiuId = "T1348649176279";

    // CBA
    public static final String CBAId = "T1348649475931";

    // 跑步
    public static final String paobuId = "T1411113472760";

    // 科技
    public static final String kejiId = "T1348649580692";

    // 手机
    public static final String shoujiId = "T1348649654285";

    // 数码
    public static final String shumaId = "T1348649776727";

    // 智能
    public static final String zhinengId = "T1351233117091";

    // 轻松一刻
    public static final String qingsongyikeId = "T1350383429665";

    // 云课堂
    public static final String gongkaikeId = "T1421997195219";

    // 态度公开课
    public static final String attitudeopenId = "T1456394562871";

    // 汽车
    public static final String qicheId = "T1348654060988";

    // 房产
    public static final String fangchanId = "T1348654085632";

    // 家居
    public static final String jiajuId = "T1348654105308";

    // 游戏
    public static final String youxiId = "T1348654151579";

    // 旅游
    public static final String lvyouId = "T1348654204705";

    // 健康
    public static final String jiankangId = "T1414389941036";

    // 读书
    public static final String dushuId = "T1401272877187";

    // 酒香
    public static final String jiuxiangId = "T1385429690972";

    // 教育
    public static final String jiaoyuId = "T1348654225495";

    // 亲子
    public static final String qinziId = "T1397116135282";

    // 暴雪游戏
    public static final String baoxueId = "T1397016069906";

    // 漫画
    public static final String manhuaId = "T1444270454635";

    // 二次元
    public static final String erciyuanlanmuId = "T1481105123675";

    // 态度营销
    public static final String taiduyingxiaoId = "T1464592736048";

    // 时尚
    public static final String nvrenId = "T1348650593803";

    // 情感
    public static final String qingganId = "T1348650839000";

    // 政务
    public static final String zhengwuId = "T1414142214384";

    // 艺术
    public static final String ArtId = "T1441074311424";

    // 阳光法院
    public static final String yangguangfayuanId = "T1482470888760";

    // 跟贴
    public static final String specialCommentId = "T1419315959525";

    // 海外
    public static final String zongheId = "T1462426218632";


    // 本地   暂不使用
    public static final String specialLocalId = "T1419316531256";

    // 博客
    public static final String specialBlogId = "T1419386532423";

    // 论坛
    public static final String specialBBSId = "T1419386592923";


    // 直播
    public static final String specialLiveId = "T1433137697241";


    // 图片新闻尾部，需要在签名添加参数，可获得从某条新闻之后的20条新闻
    // 示例 ： http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json
    public static final String endPicture = "/20.json";
    // 图片
    public static final String specialPictureId = "T1419316384474";

    // 推荐图片：0031/6LRK0031,6LRI0031/    应使用瀑布流
    public static final String RecommendPictureId = "0031";
    public static final String RecommendPicture = "/6LRK0031,6LRI0031/";

    // 新闻图片：0001/00AP0001,3R710001,4T8E0001/    横向排版
    public static final String NewsPictureId = "0001";
    public static final String NewsPicture = "/00AP0001,3R710001,4T8E0001/";
    // 热点图片：0001/00AN0001,00AO0001/     横向排版
    public static final String HotPicture = "/00AN0001,00AO0001/";

    // 明星图片：0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/      瀑布流排版
    public static final String StarPictureId = "0003";
    public static final String StarPicture = "/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/";



    /**
     *   特殊格式新闻：
     *   下面几类连接基本一致：
     *   热点、网易号类的新闻连接形式差不多，但是网易号多了from参数
     *   使用getSubDocPic
     *
     *   段子、美女、萌宠连接基本一致
     *   使用	getChanListNews
     **/
    // 热点
    public static final String specialRedianId = "T1427878984398";
    // 热点url
    public static final String RedianUrl = host + SpecialColumn1 + SpecialendUrl;

    // 网易号
    public static final String specialSubId = "T1449126525962";
    // 网易号url
    public static final String WangYiHaoUrl = host + SpecialColumn1 + "from=netease_h" +SpecialendUrl;

    // 下列为特殊频道URL， 统一连接形式为：
    // http://c.m.163.com/recommend/getChanListNews?channel=T1419316284722&size=10&offset=
    public static final String specialURL = host + SpecialColumn2;

    // 视频
    // http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
    public static final String specialVideoId = "T1457068979049";
    // 手机开发id号，任意手机的开发Id号都可以
    public static final String devId = "&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D";

    // 段子
    public static final String specialJokeId = "T1419316284722";

    // 美女
    public static final String specialGirlId = "T1456112189138";

    // 萌宠
    public static final String specialAnimalId = "T1456112438822";

    // 热点视频 http://c.m.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public static final String Video = host + "nc/video/list/";
    public static final String VideoCenter = "/n/";
    public static final String videoEndUrl = "-20.html";
    // 热点视频
    public static final String VideoReDianId = "V9LG4B3A0";
    // 推荐视频
    // http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
    public static final String VideorecommendId = "T1457068979049";
    // 搞笑视屏 http://c.m.163.com/nc/video/list/VAP4BFE3U/y/10-10.html
    // 美女视频 http://c.m.163.com/nc/video/list/VAP4BG6DL/y/0-10.html
    // 新闻现场 http://c.m.163.com/nc/video/list/VAV3H6JSN/y/0-10.html
    // 萌物 http://c.m.163.com/nc/video/list/VAP4BFR16/y/0-10.html
    // 黑科技  http://c.m.163.com/nc/video/list/VBF8F2PKF/y/0-10.html
    // 二次元 http://c.m.163.com/nc/video/list/VBF8F1PSA/y/0-10.html
    // 涨姿势  http://c.m.163.com/nc/video/list/VBF8F3SGL/y/0-10.html
    //直播视
    public static final String VideoLive = "http://data.live.126.net/livechannel/previewlist.json";



}
