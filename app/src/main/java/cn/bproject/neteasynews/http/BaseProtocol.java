package cn.bproject.neteasynews.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.bproject.neteasynews.Utils.IOUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.MD5Encoder;
import cn.bproject.neteasynews.Utils.StringUtils;
import cn.bproject.neteasynews.Utils.UIUtils;


/**
 * 访问网络的基类
 *
 * @author Kevin
 * @date 2015-10-28
 */
public abstract class BaseProtocol<T> {

    public static final int NEWS_TYPE = 1;
    public static final int PIC_TYPE = 2;

    private final String TAG = BaseProtocol.class.getSimpleName();

    public T getData(int index) {
        return getData(NEWS_TYPE, "", index);
    }

    // index表示的是从哪个位置开始返回20条数据, 用于分页
    public T getData(int type, String columm, int index) {
        // 先判断是否有缓存, 有的话就加载缓存
        String result = getCache(type, columm, index);

		if (StringUtils.isEmpty(result)) {// 如果没有缓存,或者缓存失效
        // 请求服务器
		result = getDataFromServer(type, columm, index);
		}

        // 开始解析
        if (result != null) {
            T data = parseData(result, getTid());
            return data;
        }

        return null;
    }

    /**
     * 获取新闻详情页数据
     *
     * @return
     */
    public T getDetailData() {
        String connectUrl = buildURL();
        HttpHelper.HttpResult httpResult = HttpHelper.get(connectUrl);
        String result = null;
        if (httpResult != null) {
            result = httpResult.getString();
            LogUtils.d(TAG, "getDataFromServer 访问结果:" + result);

        }
        // 开始解析
        if (result != null) {
            T data = parseData(result, getTid());
            return data;
        }

        return null;
    }


    /**
     * 从网络获取数据
     * 新闻普通栏目适用
     *
     * @param index  index表示的是从哪个位置开始返回20条数据, 用于分页
     * @param columm 当链接是图片的url时有用
     * @return
     */
    private String getDataFromServer(int type, String columm, int index) {
        // http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
        // http://www.itheima.com/home?index=0&name=zhangsan&age=18
        String connectUrl = getUrl(type, columm, index);

        HttpHelper.HttpResult httpResult = HttpHelper.get(connectUrl);

        if (httpResult != null) {
            String result = httpResult.getString();
            LogUtils.d(TAG, "getDataFromServer 访问结果:" + result);
            // 写缓存
            if (!StringUtils.isEmpty(result)) {
                setCache(type, columm, index, result);
            }

            return result;
        }

        return null;
    }


    /**
     * 获取网络链接关键词, 子类必须实现
     *
     * @return 频道tid：如要闻tid：T1467284926140
     */
    public abstract String getTid();

    /**
     * 获取url部分参数
     *
     * @return url尾部数据
     */
    public abstract String getParams();

    // 写缓存
    // 以url为key, 以json为value
    public void setCache(int type, String columm, int index, String json) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
                String filename = null;
        try {
            String url = getUrl(type, columm, index);
            LogUtils.d(TAG, "设置的setCache: " + url);
            filename = MD5Encoder.encode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 生成缓存文件
        File cacheFile = new File(cacheDir, filename);

        FileWriter writer = null;
        try {
            writer = new FileWriter(cacheFile);
            // 缓存失效的截止时间
            long deadline = System.currentTimeMillis() + 30 * 60 * 1000;// 半个小时有效期
            writer.write(deadline + "\n");// 在第一行写入缓存时间, 换行
            writer.write(json);// 写入json
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }
    }

    // 读缓存
    public String getCache(int type, String columm, int index) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
        String filename = null;
        try {
            String connectUrl = getUrl(type, columm, index);
            LogUtils.d(TAG, "设置的getCache: " + connectUrl);
            filename = MD5Encoder.encode(connectUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 生成缓存文件
        File cacheFile = new File(cacheDir, filename);

        // 判断缓存是否存在
        if (cacheFile.exists()) {
            // 判断缓存是否有效
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                String deadline = reader.readLine();// 读取第一行的有效期
                long deadtime = Long.parseLong(deadline);

                if (System.currentTimeMillis() < deadtime) {// 当前时间小于截止时间,
                    // 说明缓存有效
                    // 缓存有效
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(reader);
            }

        }

        return null;
    }

    /**
     * 返回相应的url链接
     *
     * @param index  网页请求数据的起始位置
     * @param columm 图片心里的类别，只有图片新闻游泳
     * @return
     */
    public String getUrl(int type, String columm, int index) {
        String url = null;
        if (type == NEWS_TYPE) {
            url = buildURL(index + "");
        } else if (type == PIC_TYPE) {
            url = buildURL(columm + index);
        }
        return url;
    }

    /**
     * 传入url所需要的参数
     *
     * @param params
     * @return
     */
    public abstract String buildURL(String... params);

    //

    /**
     * 解析新闻列表json数据, 子类必须实现
     *
     * @param result 新闻json数据
     * @param tid    频道id
     * @return
     */
    public abstract T parseData(String result, String tid);

    /**
     * 遍历传入的可变数组，将他们拼接在一起
     *
     * @param params
     * @return 返回可变数组拼接后的字符串
     */
    public String getAllParams(String... params) {
        StringBuffer sb = new StringBuffer();
        for (String param : params) {
            sb.append(param);
        }
        return sb.toString();
    }
}
