package cn.bproject.neteasynews.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.bproject.neteasynews.Utils.IOUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.MD5Encoder;
import cn.bproject.neteasynews.Utils.UIUtils;


/**
 * 写入缓存与读取缓存的工具类
 */
public  class CacheUtils {
    private final String TAG = CacheUtils.class.getSimpleName();

    // 写缓存
    // 以url为key, 以json为value
    public void setCache(String url, String json) {
        // 以url为文件名, 以json为文件内容,保存在本地
        File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
        String filename = null;
        try {
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
            LogUtils.d(TAG, "设置的getCache: " + url);
            filename = MD5Encoder.encode(url);
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

}
