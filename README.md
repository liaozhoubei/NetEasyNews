# NetEasyNews
仿网易新闻项目

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/1.gif)

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/2.gif)

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/3.gif)

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/4.gif)

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/5.gif)

![image](https://github.com/liaozhoubei/NetEasyNews/blob/master/images/6.gif)

apk下载：

https://pan.baidu.com/s/1jI3E6bW

# version 1.0
《第二新闻》安卓客户端-是一个资讯类新闻客户端，包括首页新闻、详情、图片浏览、视频观看等，实时数据抓取新闻等相关功能客户端。

    1.本项目使用了FragmentTabHost +  Tablayout + Viewpager进行搭建
    2.使用了MVC模式。
    3.网络上使用个人封装的httpClient请求来进行网络请求。
    4.使用glide:3.7.0作为图片加载库
    5.使用com.github.chrisbanes:PhotoView:latest.release扩展ImageView 支持通过单点/多点触摸来进行图片缩放
    6.使用可上拉、下拉加载的irecyclerview扩展RecyclerView
    7.使用Vitamio 作为在线视频观看的第三方库
    8.使用com.tencent.bugly作为异常上报的第三方库

下一次更新的版本将会使用MVP模型实现data与view的解耦，还会添加屏幕适配等功能，至于什么时候能更新，就看心情吧O(∩_∩)O哈哈~

# 已知错误信息

1、如果采用Okhttp进行获取网易api数据，将会有几率返回403：服务器拒绝的问题。
原因未知。
具体表现为：

	1、使用头条新闻：http://c.m.163.com/nc/article/list/T1348647909107/0-20.html
	2、刷新其他新闻获取下一页数据：
		http://c.m.163.com/nc/article/list/T1467284926140/0-20.html

均会出现403错误。
其中获取下一页数据会在第一次响应是返回403，第二次请求则会返回正确数据，但是在请求依旧重复前面的情况。

解决方案：
采用httpClien作为网络请求库。
