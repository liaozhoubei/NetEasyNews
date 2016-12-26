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

        /**
         * body : <p>　　此刻正在看一刻的易友朋友们注意了，请尽快办理退房。</p><!--IMG#0--><p>　　（北北北北北北北baby）</p><p>　　昨儿是一年一度的圣诞佳节，各位吃饺子了吗？</p><!--IMG#1--><p>　　老话说得好：感恩节，好小子，感谢爹娘包饺子。 万圣不把饺子包，掉进油锅没人捞。 圣诞不端饺子碗，生下耶稣没人管。复活节不把饺子下，来年被钉十字架。</p><!--IMG#2--><!--IMG#3--><p>　　圣诞饺子吃一宿，来年雅思四个九。圣诞饺子当锦鲤，来年托福一百起。圣诞饺子自己做，四级六级高分过。圣诞饺子吃一锅，考研英语九十多。（谷大白话）</p><!--IMG#4--><p>　　今朝有饺今朝醉，明日剩的继续喂！</p><p>　　其实二狗觉得，除了清明节，其他节日都是情人节。</p><!--IMG#5--><p>　　我隐约感觉到，我未来的老公昨天正和他女朋友说，他们要永远在一起。</p><p>　　承认吧，你们的老婆当年肯定也说过这句话。</p><!--IMG#6--><p>　　新东方烹饪学校做了个小调查，在平安夜突袭大学自习室。结果您猜怎么着？50余所高校中，清华以32%的自习室上座率险胜北大28%，拔得头筹！计算机专业占比14.3%，领先各专业！</p><!--IMG#7--><!--IMG#8--><!--IMG#9--><!--IMG#10--><p>　　平安夜自习室人数计算机专业再创新高，且单身率遥遥领先！</p><!--IMG#11--><p>　　在调查中提及率最高的话是：希望明年平安夜可以带女朋友一起学习... ... ？</p><!--IMG#12--><p>　　那二狗只好祝各位年年有今日，岁岁有今朝了！</p><!--IMG#13--><p>　　二狗的师弟三狗昨晚不太高兴，因为要在图书馆复习而不愿出去玩的女神微信运动有两万多步。</p><!--IMG#14--><p>　　每当佳节来临，外面都会响起熟悉的歌声“金够败吗，金够败吗，仅够LV... ...”</p><!--IMG#15--><p>　　据外国同行报道，全球60%圣诞物品产自“小商品世界之都”义乌。盛夏的时候，义乌圣诞村的工人们就开始注塑、缝边、喷漆，马力全开投入生产。圣诞前夕，约1000个装满圣诞物品的集装箱从这里发往世界各地。</p><!--IMG#16--><!--IMG#17--><!--IMG#18--><p>　　所以，圣诞老人其实是义乌人？</p><!--IMG#19--><p>　　还记得义乌做的川普小红旗吗？其实，义乌是唯一掌控世界格局的城市。</p><!--IMG#20--><p>　　圣诞有喜乐，圣诞有悲歌。</p><p>　　12月25日，一架载有92人的俄国防部图-154飞机从索契起飞后从雷达上消失，后经查实，失联飞机已经失事。机上除军方人士外，还有前往叙利亚与军人们共庆新年的国防部亚历山大罗夫红旗歌舞团的数十名演员及团长。该歌舞团是俄罗斯最大军事艺术团体。</p><!--IMG#21--><p>　　去年，为了庆祝战胜德国法西斯和日本军国主义70周年，俄罗斯军队亚历山大罗夫模范歌舞团还曾来华演出。</p><!--IMG#22--><!--IMG#23--><p>　　25年前的12月25日，苏联解体，在坠毁的飞机上的正是苏联国歌作者亚历山大创办的亚历山大歌舞团。</p><!--IMG#24--><p>　　想起之前巴西足球俱乐部成员空难的新闻，这种事情真是太让人心痛。不仅是因为飞机上坐的是最优秀的表演艺术家，每一个生命因意外而消亡都很可惜。</p><!--IMG#25--><!--IMG#26--><!--IMG#27--><!--IMG#28--><p>　　（部分遇难人员生前照片）</p><p>　　俄罗斯今年的运势就像到了本命年一样，不管怎样，新年马上要到了，我的新年愿望是“世界和平”。</p><!--IMG#29--><p>　　有些人拼了命想活，有些人却自己作大死。</p><!--IMG#30--><p>　　情侣闹分手多正常的事？前两天，男子孙某因和相恋10年的女友苏女士闹分手，给女方留了20万的银行卡后，发短信通知苏女士看他的自杀“直播”。幸亏警察叔叔到达及时，当孙某把安眠药片倒向喉咙的一瞬间破门而入，打掉药片。</p><!--IMG#31--><p>　　苏女士今年44岁，孙某52岁。都52岁的大爷了，能不能像我一样成熟点儿？反正再过几年也是要一起跳广场舞的，何必呢！</p><!--IMG#32--><p>　　我猜当时的情景是这样的：“好，今天我们直播自杀，哦！谢谢王婶儿送的火箭，爱你么么哒～”然后就被破门而入的警察叔叔一把按在了地上:“你没有直播证，不能直播！”</p><!--IMG#33--><p>　　这个故事教育我们，恋爱要趁早，黄昏恋容易做傻事。</p><!--IMG#34--><p>　　母鸡对公鸡说：“亲爱的，我怀了你的孩子。”</p><p>　　公鸡不耐烦地甩出十块钱说：“这钱拿去找个靠谱的超市，买个打蛋器把孩子打了吧。”（噗尺）</p><!--IMG#35--><p>　　搞对象还得从娃娃抓起，外国一个11岁的小屁孩，每个周日的下午在地铁摆摊，做情感咨询，两美刀一次。</p><!--IMG#36--><!--IMG#37--><!--IMG#38--><p>　　是外国作业少系列？孩子缺钱，他们缺倾诉对象，好像也没毛病。</p><!--IMG#39--><p>　　我也要去摆个摊，小孟婚介所，五块钱介绍一个十块钱仨，货真价实童叟无欺。</p><p>　　而且孩子的世界跟成人不一样，看问题更简单也更纯洁，大道至简，有时候是大人把事情想复杂了。</p><!--IMG#40--><p>　　毕竟对于大人而言，自古真情留不住，不如发财靠得住。</p><p>　　人为了钱，真是啥蠢事都干得出来。昨天早上，男子罗某驾驶了一辆小型挖掘机，挖了4台银行柜员机，然而柜员机里并没有现金。罗某也被当场抓获。</p><!--IMG#41--><!--IMG#42--><p>　　好的现在问题来了，挖掘机技术究竟哪家强？中国山东找蓝翔，学到技术挖银行！</p><p>　　蓝翔表示对此次事件不负责：哪抢银行了？人家明明在建设银行！</p><!--IMG#43--><p>　　史上目标最大，动静最大，跑得最慢的抢劫工具，我也是第一次见。把挖掘机卖了都够花一阵子了吧？</p><!--IMG#44--><p>　　这智商啊，基本也就告别自行车了... ...</p><!--IMG#45--><p>　　一国两智，说的就是你们！</p><p>　　据台媒报道，台湾新竹市光复高中有学生在校庆活动上穿仿制的纳粹军装，高举纳粹旗帜游行，还有学生登上自制“战车”，摆出希特勒招牌致敬姿势。</p><!--IMG#46--><p>　　照片在网上传开后马上就炸锅了，校方出面回应了，说孩子们觉得好玩，只是不知道这种好玩会伤害别人。</p><p>　　高中生能无知成这样？体育老师教出来的历史课都不至于到这个水平。</p><!--IMG#47--><p>　　我现在很担心，这要是在国际新闻上直接被写成了Chinesestudents没强调Taiwan province，咱可丢不起这个人啊！</p><!--IMG#48--><p>　　人傻就得多读书，此话不假。这位75岁的大爷还在孜孜不倦努力学习，那些脑部有残疾的孩子们咋还这么不知进取？</p><p>　　75岁的白发爷爷邹伟敏，从2003年开始学习，从大专生成为了一名本科生，今年是他第四次考研，他说，“只要有机会还是要力求一搏。逆水行舟，不进则退，而倒退是没有出路的。”</p><!--IMG#49--><p>　　昨天晚上，今年的研究生考试正式结束，希望今年大爷能有个好成绩！</p><!--IMG#50--><p>　　考研有多累，考过才知道。你们考过研吗？</p><p><!--@@PKVOTEID=58378--></p><p>　　活到老学到老，人家大爷都这么拼，那些个一天到晚抱怨生活的人又有啥理由不努力呢？</p><!--IMG#51--><p>　　既然知道人生不公平，就更应该努力加油！</p><p>　　就拿阴阳师来说，这个纸片人已经很久没休息了，而另一个一直在玩，人家说啥了？（呼_懒死在床上）</p><!--IMG#52--><!--IMG#53--><p>　　这就反映了当代职场的普遍问题，另一个小纸片人负责发东西，没东西发的时候就在边上玩，显然是家里有背景！</p><!--IMG#54--><p>　　正所谓以梦为马，越骑越傻。不愿将就，装逼没够。执子之手，如同猪肘。故事和酒，床上全有。以地为床，越睡越凉。春风十里，吹不死你。心有猛虎，像二百五。何以解忧，唯有网购。嘘寒问暖，不如巨款。闲庭信步，忘穿秋裤。（假装在纽约）</p><!--IMG#55--><p>　　凡事都要看开，苦不苦想想长征两万五，累不累想想雷锋董存瑞！估计这两天墨镜王的心比雷锋还累。</p><p>　　王家卫不是监制了个电影叫“百度人”么，上映以后差评不少，神坛上的墨镜王坐不住了，发微博表示：天下之大，又何止南北，摆渡人，渡人渡己，我喜欢。一众明星也转发支持表示喜欢，史上最强水军，仿佛一个大型传销现场。</p><!--IMG#56--><!--IMG#57--><!--IMG#58--><!--IMG#59--><!--IMG#60--><p>　　然鹅，就在这个时候，演员王传君发了一条微博说“我不喜欢”。</p><!--IMG#61--><p>　　你们喜欢不喜欢？</p><p><!--@@PKVOTEID=58379--></p><p>　　果然是关谷神奇，今夜我们都是王传君！</p><!--IMG#62--><p>　　年度最佳戒酒公益广告出炉了，真的少喝酒，不信你看电影里那帮人。 （电影扒客）</p><!--IMG#63--><p>　　不过张嘉佳也确实惨，写书卖不过郭敬明就罢了，当导演居然都不如他。</p><!--IMG#64--><p>　　娱乐圈就没有消停的时候，因为不想看谢霆锋下面，粉丝们又不乐意了。</p><p>　　谢霆锋参加了公司在圣诞举办的拼盘演唱会，然而作为主角的他没有独唱一首歌，而是当起厨师煮了20分钟的粉丝。歌迷大失所望，表示想听谢霆锋唱歌，贴吧更是骂声一片。</p><!--IMG#65--><!--IMG#66--><p>　　你觉得这个事谢霆锋有错吗？</p><p><!--@@PKVOTEID=58380--></p><p>　　真是涮粉丝玩儿来了？</p><!--IMG#67--><p>　　歌迷走开！让饭迷朋友们买票！</p><!--IMG#68--><p>　　不提谢霆锋下面的事了，告诉大家一个好消息！</p><p>　　经过重拳出击的污染治理，取得了重大成效。专家预测：这几天将会是今年最后一次出现雾霾天气，今年再也不会有雾霾天了！鼓掌！</p><!--IMG#69--><p>　　很多年后，大家回想起2016年，各种肇始，初见端倪，百味杂陈，众说纷纭，唯一的共识：那绝不是最糟的一年。</p><!--IMG#70--><p>　　因为2017年，还是这样。</p><p><!--VIDEO#0--></p><p>　　今天的一刻就到这里，我是在2017年努力吃饭也长不到一米五的二狗，回见~</p><!--IMG#71--><p><!--REWARD#0--></p><!--IMG#72--><p>　　【年终了，你的小目标达成了吗？】</p><p>　　友情提示：年底案件高发，ATM取款注意遮挡，不要被陌生人看到余额，否则会被人笑话！</p><p>　　2016年马上就要过去了，你的小目标达成了吗？有什么方法可以给自己长长脸呢？请猛戳：</p><p><!--link0--></p><!--IMG#73--><p>　　你截到的第1张图，将代表你的2017哦~</p><!--IMG#74--><p>　　【上期答案up！】</p><!--IMG#75--><!--IMG#76--><p>　　【心理测试：面对爱情你是个怎样的人？】</p><p>　　如果有天晚上，本来已很疲倦的你，不知道为什么总是睡不着，你会用下列哪种方法来度过这个失眼夜呢？</p><p>　　1、打电话与别人聊天</p><p>　　2、在家中四处找事做</p><p>　　3、继续在床上辗转反侧</p><p>　　4、看书</p><p>　　5、多冲一次凉</p><p>　　答案在文章最后哦~</p><!--IMG#77--><p>　　夭寿啦！小怪兽打奥特曼啦！易友们快来吐槽！</p><!--IMG#78--><p>　　上期吐槽UP！</p><!--IMG#79--><p>　　你觉得哪个吐槽更传神呢？</p><p><!--@@PKVOTEID=58377--></p><!--IMG#80--><p>　　感觉你要小心了，明年你男朋友说的最多的话应该就是，我去年买了个包！</p><!--IMG#81--><p>　　厉害了我的土豪易友！</p><!--IMG#82--><!--IMG#83--><p>　　【心理测试答案】</p><p>　　1你做每一件事都只顾自己而已，绝不替人着想，独断独行的你，令爱人吃不消，改改你武断的性格，多征求伴侣对事情的看法，会令爱人觉得你更可爱。</p><p>　　2你那死不服输的性格将你在人群独立出来，恋爱来的时候，亦因为不肯对对方坦白而遭抛弃，你唯一的好处是你绝不拖拖拉拉没完没了，只要找到心仪的对象，不妨来个闪电结婚！</p><p>　　3在生活上有问题时，你只懂逃避，爱情方面也一样，因为你的拖拖拉拉令很多人爱到影响，和情人分手后仍会有藕断丝连的情况发生，想做就做，想爱就爱吧，机会溜走就不复返！</p><p>　　4你是个十分理智的人，但聪明反被聪明误，你对每件事的小心谨慎，在爱情路上，因怕爱伤而不敢用情太深，但这会令你的爱人有被忽视的感觉，对你的热情亦因此大减，其实做人有时迷糊一下，亦未尝不可。</p><p>　　5你有点神经质，但亦有点执着，别人很难适应你的处事作风，你的爱侣也一样，你一时对他风情万种，一时对他冷若冰霜，令他感到十分迷惑，即使对他一见钟情，你亦会给他飘忽不定的感觉而吓得溜之大吉，要改变现况，控制一下自己的情绪吧！</p><!--IMG#84--><!--IMG#85--><!--IMG#86--><p><!--link1--></p>
         * users : []
         * ydbaike : []
         * replyCount : 4868
         * link : [{"ref":"<!--link0-->","title":"你的小目标达成了吗？","imgsrc":"http://cms-bucket.nosdn.127.net/4bba72ca129c45b2bea3038fe450ef6420161223202339.jpeg","digest":"距存够2W的小目标还差3W","type":"linkCard","href":"http://3g.163.com/news/16/1223/20/C90EQK7D000181BT.html"},{"ref":"<!--link1-->","title":"主编问吧：女神来袭","imgsrc":"http://cms-bucket.nosdn.127.net/e9785e2c4e4c4a828af66c5266286dec20161009195516.png","digest":"搞笑、吐槽、舞蹈健身问题随便问！","type":"linkCard","href":"http://c.3g.163.com/nc/qa/newsapp/question.html?id=EX06769209422022087485"}]
         * img : [{"ref":"<!--IMG#0-->","pixel":"690*692","alt":"","src":"http://cms-bucket.nosdn.127.net/c85eed9a823043249c9eeb99bca7f69e20161226084157.jpeg"},{"ref":"<!--IMG#1-->","pixel":"356*200","alt":"","src":"http://cms-bucket.nosdn.127.net/2a6c9a6ff9dc47e3bd2292504a0dc55b20161226090530.gif"},{"ref":"<!--IMG#2-->","pixel":"640*1348","alt":"","src":"http://cms-bucket.nosdn.127.net/901e35f362e24edc8ec2ef14016c29c420161226083834.jpeg"},{"ref":"<!--IMG#3-->","pixel":"640*1559","alt":"","src":"http://cms-bucket.nosdn.127.net/dcc05ec1682c4e1cbdb92bb836fe2acf20161226083834.jpeg"},{"ref":"<!--IMG#4-->","pixel":"690*164","alt":"","src":"http://cms-bucket.nosdn.127.net/f07d2f549fee45fb95766a60e4e8515d20161226083932.jpeg"},{"ref":"<!--IMG#5-->","pixel":"440*440","alt":"","src":"http://cms-bucket.nosdn.127.net/4b26047769c44227bb92b991138ac90820161226084024.jpeg"},{"ref":"<!--IMG#6-->","pixel":"440*349","alt":"","src":"http://cms-bucket.nosdn.127.net/90422c7a9f784ce58c44efefe6076d7220161226084054.jpeg"},{"ref":"<!--IMG#7-->","pixel":"416*464","alt":"","src":"http://cms-bucket.nosdn.127.net/752482bc71a54e388093fd68723eca5220161226110823.jpeg"},{"ref":"<!--IMG#8-->","pixel":"374*396","alt":"","src":"http://cms-bucket.nosdn.127.net/2a4097e45f0542aab8bc0e3a049c935d20161226110823.jpeg"},{"ref":"<!--IMG#9-->","pixel":"387*267","alt":"","src":"http://cms-bucket.nosdn.127.net/cc9c64704c5c45998966d17764c632db20161226110824.jpeg"},{"ref":"<!--IMG#10-->","pixel":"383*273","alt":"","src":"http://cms-bucket.nosdn.127.net/0c9bbac51cbb44b7b867753133f8d2d220161226110823.jpeg"},{"ref":"<!--IMG#11-->","pixel":"200*200","alt":"","src":"http://cms-bucket.nosdn.127.net/b931bbebba3249da959b79ab9d0d71c920161226084420.gif"},{"ref":"<!--IMG#12-->","pixel":"600*335","alt":"","src":"http://cms-bucket.nosdn.127.net/0cb4212ac39f4e17a3c19bcb6066363b20161226091021.jpeg"},{"ref":"<!--IMG#13-->","pixel":"600*858","alt":"","src":"http://cms-bucket.nosdn.127.net/344d44f3aa12431fb7e73d58818956db20161226091111.jpeg"},{"ref":"<!--IMG#14-->","pixel":"600*400","alt":"","src":"http://cms-bucket.nosdn.127.net/130b9bcd2e2646548187f5f6234b0dfd20161226091039.jpeg"},{"ref":"<!--IMG#15-->","pixel":"600*505","alt":"","src":"http://cms-bucket.nosdn.127.net/83bb372e644e4d7cbfb8aa465d229ce920161226091147.jpeg"},{"ref":"<!--IMG#16-->","pixel":"440*293","alt":"","src":"http://cms-bucket.nosdn.127.net/396e892bcc42435c85ad37125ba4674a20161226091349.jpeg"},{"ref":"<!--IMG#17-->","pixel":"440*293","alt":"","src":"http://cms-bucket.nosdn.127.net/357f78776f544a7b83ba4ed950ddcfaa20161226091349.jpeg"},{"ref":"<!--IMG#18-->","pixel":"440*293","alt":"","src":"http://cms-bucket.nosdn.127.net/9497bf3c652e4e649efe9ec9e052539620161226091349.jpeg"},{"ref":"<!--IMG#19-->","pixel":"439*383","alt":"","src":"http://cms-bucket.nosdn.127.net/fbf973cf2b2f468ba3abbaf7d3bc9abc20161226090830.jpeg"},{"ref":"<!--IMG#20-->","pixel":"463*412","alt":"","src":"http://cms-bucket.nosdn.127.net/30eec5489eb7406c9f1985a58f6eb56a20161226091647.jpeg"},{"ref":"<!--IMG#21-->","pixel":"440*293","alt":"","src":"http://cms-bucket.nosdn.127.net/92889035b82a42719fb17891705cb38820161226091853.jpeg"},{"ref":"<!--IMG#22-->","pixel":"500*352","alt":"","src":"http://cms-bucket.nosdn.127.net/764609109b0748ab81ceb3ff0615aedf20161226084711.jpeg"},{"ref":"<!--IMG#23-->","pixel":"500*352","alt":"","src":"http://cms-bucket.nosdn.127.net/d3562ede77094973ac46b9c82b863bbe20161226084711.jpeg"},{"ref":"<!--IMG#24-->","pixel":"300*194","alt":"","src":"http://cms-bucket.nosdn.127.net/d65c451e44ee4cceaf0c302687c01d2420161226092103.jpeg"},{"ref":"<!--IMG#25-->","pixel":"440*660","alt":"","src":"http://cms-bucket.nosdn.127.net/4cb421d30aee449c8861b5f97345fb8e20161226091931.jpeg"},{"ref":"<!--IMG#26-->","pixel":"440*389","alt":"","src":"http://cms-bucket.nosdn.127.net/cd5b96155e4e4f6fab11419485f4eb3320161226091931.jpeg"},{"ref":"<!--IMG#27-->","pixel":"440*496","alt":"","src":"http://cms-bucket.nosdn.127.net/c6da6cfa20f2459db7f83221bb80775d20161226091931.jpeg"},{"ref":"<!--IMG#28-->","pixel":"440*547","alt":"","src":"http://cms-bucket.nosdn.127.net/86ff158e9bf04c6bb78e90c72508412020161226091931.jpeg"},{"ref":"<!--IMG#29-->","pixel":"423*300","alt":"","src":"http://cms-bucket.nosdn.127.net/37bcd8a53195414c8b0fd2860a2b979d20161226092233.jpeg"},{"ref":"<!--IMG#30-->","pixel":"440*521","alt":"","src":"http://cms-bucket.nosdn.127.net/4e656f6cec944d3c85d2ae5ab6ff6c8c20161226084752.jpeg"},{"ref":"<!--IMG#31-->","pixel":"440*233","alt":"","src":"http://cms-bucket.nosdn.127.net/3609c9759690429e84b957eed4e3c38a20161226092346.jpeg"},{"ref":"<!--IMG#32-->","pixel":"360*360","alt":"","src":"http://cms-bucket.nosdn.127.net/47adc518fc244af6856dcdce22594c9020161226084614.jpeg"},{"ref":"<!--IMG#33-->","pixel":"690*388","alt":"","src":"http://cms-bucket.nosdn.127.net/80e814fc8b3e4500ad2cd02177f21e1d20161226084847.png"},{"ref":"<!--IMG#34-->","pixel":"452*452","alt":"","src":"http://cms-bucket.nosdn.127.net/9e29b1b373ac4857b81dbdef07040b0c20161226084632.jpeg"},{"ref":"<!--IMG#35-->","pixel":"375*246","alt":"","src":"http://cms-bucket.nosdn.127.net/083e195ff69c483090c52a7840f789eb20161226092434.gif"},{"ref":"<!--IMG#36-->","pixel":"550*551","alt":"","src":"http://cms-bucket.nosdn.127.net/4e23c23415e94d158589105383d5002420161226084537.jpeg"},{"ref":"<!--IMG#37-->","pixel":"550*551","alt":"","src":"http://cms-bucket.nosdn.127.net/765c92f4799b463799358ab406406acf20161226084537.jpeg"},{"ref":"<!--IMG#38-->","pixel":"550*536","alt":"","src":"http://cms-bucket.nosdn.127.net/c49eef0d593f45a7a8754891ff2cf5bb20161226084537.jpeg"},{"ref":"<!--IMG#39-->","pixel":"450*555","alt":"","src":"http://cms-bucket.nosdn.127.net/6f95a4f920bb4a3a914ae19db825c57420161226062336.jpeg"},{"ref":"<!--IMG#40-->","pixel":"600*406","alt":"","src":"http://cms-bucket.nosdn.127.net/01577e34b26e4756b403e63c2a44692820161226092605.jpeg"},{"ref":"<!--IMG#41-->","pixel":"440*518","alt":"","src":"http://cms-bucket.nosdn.127.net/b5848c665a8f4f29bcf192618d6efba020161226092817.jpeg"},{"ref":"<!--IMG#42-->","pixel":"440*297","alt":"","src":"http://cms-bucket.nosdn.127.net/27b62ae66b4d4d5e86b204b07f23c07b20161226092817.jpeg"},{"ref":"<!--IMG#43-->","pixel":"440*440","alt":"","src":"http://cms-bucket.nosdn.127.net/de149d01006a4fed8c1dc3b856cc026e20161226093035.jpeg"},{"ref":"<!--IMG#44-->","pixel":"350*145","alt":"","src":"http://cms-bucket.nosdn.127.net/d18a5cb5d4fa4bc0b6cf3117b980f53820161226084329.jpeg"},{"ref":"<!--IMG#45-->","pixel":"440*440","alt":"","src":"http://cms-bucket.nosdn.127.net/66fca74cb01d4f1aa6f489f60adfa8a020161226092933.jpeg"},{"ref":"<!--IMG#46-->","pixel":"440*330","alt":"","src":"http://cms-bucket.nosdn.127.net/f700587e561c4667a68ddcc0748b7da920161226083741.jpeg"},{"ref":"<!--IMG#47-->","pixel":"290*163","alt":"","src":"http://cms-bucket.nosdn.127.net/54cb12dad79e43449a7604090de8d1e620161226093151.gif"},{"ref":"<!--IMG#48-->","pixel":"197*179","alt":"","src":"http://cms-bucket.nosdn.127.net/bdfa2429744741cf85899a93446e575120161226093236.jpeg"},{"ref":"<!--IMG#49-->","pixel":"440*1504","alt":"","src":"http://cms-bucket.nosdn.127.net/dce537b79c8c486fa9af05eb1dd6d79e20161226084254.jpeg"},{"ref":"<!--IMG#50-->","pixel":"350*196","alt":"","src":"http://cms-bucket.nosdn.127.net/d8ff49f9596345e8ac8a00311132c07020161226093331.gif"},{"ref":"<!--IMG#51-->","pixel":"600*413","alt":"","src":"http://cms-bucket.nosdn.127.net/d17d6c778bba43e9bf5e4e19f5a1151220161226093358.jpeg"},{"ref":"<!--IMG#52-->","pixel":"124*138","alt":"","src":"http://cms-bucket.nosdn.127.net/0afa19f3a2524b0f83254c38ff0b607320161226084503.jpeg"},{"ref":"<!--IMG#53-->","pixel":"191*173","alt":"","src":"http://cms-bucket.nosdn.127.net/5b5950bf4baa4169a32a73c52c2b880c20161226084503.jpeg"},{"ref":"<!--IMG#54-->","pixel":"320*240","alt":"","src":"http://cms-bucket.nosdn.127.net/956390e8d68142f5a2b49e20effcdb4120161226093424.gif"},{"ref":"<!--IMG#55-->","pixel":"320*240","alt":"","src":"http://cms-bucket.nosdn.127.net/e0d49d2b8ff84bfe8cd024449bdcea0920161226093446.gif"},{"ref":"<!--IMG#56-->","pixel":"690*1227","alt":"","src":"http://cms-bucket.nosdn.127.net/e0062035cbef454a815ce8bc7b22363920161226093714.jpeg"},{"ref":"<!--IMG#57-->","pixel":"690*1227","alt":"","src":"http://cms-bucket.nosdn.127.net/7f0990a993404c2280ffb72d00a7368020161226093714.jpeg"},{"ref":"<!--IMG#58-->","pixel":"690*1227","alt":"","src":"http://cms-bucket.nosdn.127.net/17eb93bc7b4947cca9e0ff51fa93025220161226093714.jpeg"},{"ref":"<!--IMG#59-->","pixel":"690*1227","alt":"","src":"http://cms-bucket.nosdn.127.net/607bef2049534c399ff278e9b2333d3c20161226093714.jpeg"},{"ref":"<!--IMG#60-->","pixel":"690*1227","alt":"","src":"http://cms-bucket.nosdn.127.net/7d3acc6bd7f54d849e05f6384f1d461220161226093714.jpeg"},{"ref":"<!--IMG#61-->","pixel":"588*131","alt":"","src":"http://cms-bucket.nosdn.127.net/aeda378fa5fc4f6a9f109157525f135420161226093741.jpeg"},{"ref":"<!--IMG#62-->","pixel":"389*220","alt":"","src":"http://cms-bucket.nosdn.127.net/0c3f61ebc1674aaf96203644eceaea3620161226093831.jpeg"},{"ref":"<!--IMG#63-->","pixel":"690*695","alt":"","src":"http://cms-bucket.nosdn.127.net/3983563d65954d17b46b2e2247ede0ce20161226084128.jpeg"},{"ref":"<!--IMG#64-->","pixel":"501*238","alt":"","src":"http://cms-bucket.nosdn.127.net/66698a95088149379cdcf034b17b3fb020161226094041.jpeg"},{"ref":"<!--IMG#65-->","pixel":"440*545","alt":"","src":"http://cms-bucket.nosdn.127.net/1d64ce2efe4344eca0d525583ad0e4b020161226094237.jpeg"},{"ref":"<!--IMG#66-->","pixel":"440*611","alt":"","src":"http://cms-bucket.nosdn.127.net/7df6cbdc1d694c06ae2f90bd4fd921f920161226094237.jpeg"},{"ref":"<!--IMG#67-->","pixel":"488*598","alt":"","src":"http://cms-bucket.nosdn.127.net/82e5d7c1128846d095f53eb33bcfa41620161226094326.jpeg"},{"ref":"<!--IMG#68-->","pixel":"136*119","alt":"","src":"http://cms-bucket.nosdn.127.net/9323cd0651ce42768c7a7b4f177de9e320161226094454.jpeg"},{"ref":"<!--IMG#69-->","pixel":"160*220","alt":"","src":"http://cms-bucket.nosdn.127.net/8b5c5cd409e74b399c32ed3daec1116320161226085316.gif"},{"ref":"<!--IMG#70-->","pixel":"460*458","alt":"","src":"http://cms-bucket.nosdn.127.net/f4423f43fb794675bae1978dce8e3bef20161226094546.gif"},{"ref":"<!--IMG#71-->","pixel":"540*960","alt":"","src":"http://cms-bucket.nosdn.127.net/9a125fde3c724a4a84605499d487cd0820161226095333.jpeg"},{"ref":"<!--IMG#72-->","pixel":"550*274","alt":"","src":"http://cms-bucket.nosdn.127.net/5e4a842be6a8474d84f942ed4af4124420161223153854.jpeg"},{"ref":"<!--IMG#73-->","pixel":"369*76","alt":"","src":"http://cms-bucket.nosdn.127.net/22c59e5af7c54469a399c51870f764b120161223153854.png"},{"ref":"<!--IMG#74-->","pixel":"600*600","alt":"","src":"http://cms-bucket.nosdn.127.net/12735d30f3b743d7b8fb393ed658359020161226082513.gif"},{"ref":"<!--IMG#75-->","pixel":"1000*826","alt":"","src":"http://cms-bucket.nosdn.127.net/5bfe812a150141a69a5f2928707993e420161226062336.jpeg"},{"ref":"<!--IMG#76-->","pixel":"640*1976","alt":"","src":"http://cms-bucket.nosdn.127.net/5456f83d6e5f42febeafe175632c049820161226062336.png"},{"ref":"<!--IMG#77-->","pixel":"369*76","alt":"","src":"http://cms-bucket.nosdn.127.net/e47e391730aa46fa95b2f782ea47236d20161223153854.png"},{"ref":"<!--IMG#78-->","pixel":"480*492","alt":"","src":"http://cms-bucket.nosdn.127.net/bf98adc2b00a490595fa4e8da2a8230a20161226082513.png"},{"ref":"<!--IMG#79-->","pixel":"614*1200","alt":"","src":"http://cms-bucket.nosdn.127.net/fe3b43f83b32498daf07d363b4c91f7020161226062336.jpeg"},{"ref":"<!--IMG#80-->","pixel":"369*76","alt":"","src":"http://cms-bucket.nosdn.127.net/783ab03b14f4493a85118fd6062f0a1d20161223154015.png"},{"ref":"<!--IMG#81-->","pixel":"640*806","alt":"","src":"http://cms-bucket.nosdn.127.net/d58861ca0bac4245822f1b91d111db4f20161226062335.png"},{"ref":"<!--IMG#82-->","pixel":"640*896","alt":"","src":"http://cms-bucket.nosdn.127.net/16c54ebc28d847ffafa30a65275cead720161226062336.png"},{"ref":"<!--IMG#83-->","pixel":"550*269","alt":"","src":"http://cms-bucket.nosdn.127.net/860c59b3e2e34846ab1106e622d9b7a720161223154015.jpeg"},{"ref":"<!--IMG#84-->","pixel":"550*1299","alt":"","src":"http://cms-bucket.nosdn.127.net/7b797921fc9848f883487c72031f93ee20161223154135.jpeg"},{"ref":"<!--IMG#85-->","pixel":"550*355","alt":"","src":"http://cms-bucket.nosdn.127.net/47f5ba7764bb4de487adb95ed9e5a1a020161223154134.jpeg"},{"ref":"<!--IMG#86-->","pixel":"550*392","alt":"","src":"http://cms-bucket.nosdn.127.net/42b14dc8f1e54b12becb501d1126fed320161223154134.jpeg"}]
         * votes : [{"digest":"你们考过研吗？","date":"2016-12-26","url":"","option_type":0,"date_type":0,"sumnum":1109,"docid":"","ref":"<!--@@PKVOTEID=58378-->","voteid":"58378","voteitem":[{"id":"247999","num":451,"name":"考过研，我当时____。"},{"id":"248000","num":658,"name":"没考过，想想都挺累。"}],"imgsrc":"","voteType":"pkVote","question":"你们考过研吗？"},{"digest":"你们喜欢不喜欢？","date":"2016-12-26","url":"","option_type":0,"date_type":0,"sumnum":1337,"docid":"","ref":"<!--@@PKVOTEID=58379-->","voteid":"58379","voteitem":[{"id":"248001","num":122,"name":"喜欢，看电影就图个乐，何况还有大明星们加持"},{"id":"248002","num":1215,"name":"不喜欢，宣传到这个份儿上太掉价"}],"imgsrc":"","voteType":"pkVote","question":"你们喜欢不喜欢？"},{"digest":"你觉得这个事谢霆锋有错吗？","date":"2016-12-26","url":"","option_type":0,"date_type":0,"sumnum":527,"docid":"","ref":"<!--@@PKVOTEID=58380-->","voteid":"58380","voteitem":[{"id":"248003","num":351,"name":"有错，粉丝买票不是看做饭的"},{"id":"248004","num":176,"name":"没错，表演什么内容是个人自由"}],"imgsrc":"","voteType":"pkVote","question":"你觉得这个事谢霆锋有错吗？"},{"digest":"PK","date":"2016-12-26","url":"","option_type":0,"date_type":0,"sumnum":218,"docid":"","ref":"<!--@@PKVOTEID=58377-->","voteid":"58377","voteitem":[{"id":"247997","num":148,"name":"易友\u201c倒霉的菜鸟\u201d"},{"id":"247998","num":70,"name":"易友\u201c一日万年\u201d"}],"imgsrc":"","voteType":"pkVote","question":"PK"}]
         * shareLink : https://c.m.163.com/news/a/C9741113000181BR.html?spss=newsapp&spsw=1
         * digest :
         * topiclist_news : [{"hasCover":true,"subnum":"超过1000万","alias":"FUNNY MOMENT","tname":"轻松一刻","ename":"qingsongyike","tid":"T1350383429665","cid":"C1348654575297"}]
         * dkeys : 谢霆锋,王传君,王家卫
         * ec : 孟想成真_NX2002
         * topiclist : []
         * docid : C9741113000181BR
         * picnews : true
         * title : 轻松一刻:承认吧,你们的老婆都说过这句话
         * tid :
         * video : [{"commentid":"C84JV7AL00964ILB","topicid":"0096","broadcast":"in","videosource":"其他","commentboard":"mobile_bbs","appurl":"","mp4Hd_url":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/rbPOL1739-mobile.mp4","url_m3u8":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4","size":"","ref":"<!--VIDEO#0-->","cover":"http://vimg2.ws.126.net/image/snapshot/2016/12/S/T/VC84JV4ST.jpg","url_mp4":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4","alt":"年终策划视频《小时代之狗血2017》","mp4_url":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/rbPOL1739-mobile.mp4","m3u8Hd_url":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/HD/movie_index.m3u8","m3u8_url":"http://flv2.bn.netease.com/videolib3/1612/24/rbPOL1739/SD/movie_index.m3u8","vid":"VC84JV7AL"}]
         * template : normal
         * threadVote : 1
         * rewards : [{"id":20,"ref":"<!--REWARD#0-->","category":"新媒体","description":"随手解救贫苦小编","name":"一米四九的二狗","rewardOptions":[{"id":148,"slogan":"买不了吃亏","count":10,"type":0},{"id":149,"slogan":"买不了上当","count":50,"type":0},{"id":150,"slogan":"啥也买不了","count":180,"type":0},{"id":151,"slogan":"客官想要啥","count":1630,"type":0},{"id":152,"slogan":"帮二狗发家","count":1,"type":2},{"id":153,"slogan":"帮二狗致富","count":5,"type":2},{"id":154,"slogan":"帮二狗增高","count":18,"type":2},{"id":155,"slogan":"帮二狗整容","count":163,"type":2}],"image":"http://img1.cache.netease.com/3g/xmt/ergou2.png","createDate":"2016-04-14 10:24:24","head":"http://img1.cache.netease.com/3g/xmt/ergou1.png"}]
         * threadAgainst : 5
         * boboList : []
         * replyBoard : news2_bbs
         * source : 轻松一刻工作室
         * hasNext : false
         * voicecomment : off
         * relative_sys : [{"id":"C96TRHMR00038FO9","title":"邱淑贞大女儿晒圣诞美照 网友点赞叫其参加选美","source":"网易娱乐","imgsrc":"http://cms-bucket.nosdn.127.net/c8c5f87fdfe345edb4c1f98970f343a320161226084605.png","docID":"C96TRHMR00038FO9","from":"BJ","type":"doc","ptime":"2016-12-26 08:46:15","href":""},{"id":"C9711VQL04220C6A","title":"这个圣诞有点污 瑞轩88让你品尝暧昧文化","source":"网易丹东","imgsrc":"http://cms-bucket.nosdn.127.net/a194563df2a4482f82dc7adcd5a7d40b20161226094535.jpeg","docID":"C9711VQL04220C6A","from":"BJ","type":"doc","ptime":"2016-12-26 09:42:11","href":""},{"id":"C97C1OAS04248E93","title":"三阳地铁站人流量日达11万人次 圣诞安保\"忙坏\"","source":"无锡商报","imgsrc":"http://cms-bucket.nosdn.127.net/9db7a71643eb40a8820d09b57234289b20161226125337.png","docID":"C97C1OAS04248E93","from":"BJ","type":"doc","ptime":"2016-12-26 12:54:18","href":""}]
         * ptime : 2016-12-26 10:34:05
         */

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




}
