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



    private final String TAG = BaseProtocol.class.getSimpleName();


    // index表示的是从哪个位置开始返回20条数据, 用于分页
    public T getData(String url) {
        // 先判断是否有缓存, 有的话就加载缓存
//        String result = getCache(type, columm, index);

//        if (StringUtils.isEmpty(result)) {// 如果没有缓存,或者缓存失效
            // 请求服务器
        String result = getDataFromServer(url);
//        }

        // 开始解析
        if (result != null) {
            T data = parseData(result, getTid());
            return data;
        }

        return null;
    }

    /**
     * 获取新闻详情页数据
     *  不缓存数据
     * @return
     */
    public T getDetailData(String url) {
        String connectUrl = buildURL(url);
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
     * @param url 链接的url
     * @return
     */
    private String getDataFromServer(String url) {

        String connectUrl = buildURL(url);

        HttpHelper.HttpResult httpResult = HttpHelper.get(connectUrl);

        if (httpResult != null) {
            String result = httpResult.getString();
            LogUtils.d(TAG, "getDataFromServer 访问结果:" + result);
            // 写缓存
            if (!StringUtils.isEmpty(result)) {
                setCache(url, result);
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
    public void setCache(String cacheurl, String json) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
        String filename = null;
        try {
            String url = buildURL(cacheurl);
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
    public String getCache(String url) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
        String filename = null;
        try {
            String connectUrl = buildURL(url);
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
     * 传入url
     *
     * @param url
     * @return
     */
    public abstract String buildURL(String url);

    //

    /**
     * 解析新闻列表json数据, 子类必须实现
     *
     * @param result 新闻json数据
     * @param tid    频道id
     * @return
     */
    public abstract T parseData(String result, String tid);


}
