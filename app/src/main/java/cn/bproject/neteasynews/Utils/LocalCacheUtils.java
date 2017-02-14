package cn.bproject.neteasynews.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static cn.bproject.neteasynews.Utils.MD5Encoder.hashKeyForDisk;

/**
 * Created by Administrator on 2016/12/8.
 * 设置本地缓存
 */

public class LocalCacheUtils {
    private static final String TAG = LocalCacheUtils.class.getSimpleName();

    /**
     * @param url     传入获取信息的网址，作为保存缓存的文件名
     * @param content 传入要缓存的信息
     */
    public static void setDiskLruCache(String url, String content) {
        Context context = UIUtils.getContext();
        DiskLruCache mDiskLruCache = null;
        try {
            File cacheDir = getDiskCacheDir(context, "news");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 10 * 1024 * 1024);

            String key = hashKeyForDisk(url);
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                if (setLocalCache(content, outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
            mDiskLruCache.flush();

            Log.d(TAG, "setDiskLruCache: 写入缓存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取新闻缓存目录地址
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        return new File(getCachePath(context) + File.separator + uniqueName);
    }

    /**
     * 获取缓存目录
     * @param context
     * @return
     */
    public static String getCachePath(Context context){
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            // 当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，否则就调用getCacheDir()方法来获取缓存路径
            // 路径为/sdcard/Android/data/<application package>/cache
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            //  路径为/data/data/<application package>/cache
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取当前版本号
     *
     * @param context
     * @return 返回当前应用的版本号
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    private static final String LOCAL_CACHE_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/NetEasyNews_Cache";

    // 写本地缓存
    public static boolean setLocalCache(String content, OutputStream outputStream) {

        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            out.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "setLocalCache: 没有找到可写入的文件");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "setLocalCache: 写入文件失败，写入流被关闭或者其他I/O原因");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "setLocalCache: I/O流关闭错误");
            }
        }
        return false;
    }

    /**
     * 读取磁盘缓存
     * @param url
     * @return
     */
    public static String getLocalCache( String url) {
        Context context = UIUtils.getContext();
        DiskLruCache mDiskLruCache = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = null;
        InputStream is = null;
        // 获取缓存文件夹
        File cacheDir = getDiskCacheDir(context, "news");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 10 * 1024 * 1024);
            String key = hashKeyForDisk(url);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                is = snapShot.getInputStream(0);
                br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String str;
                while ((str = br.readLine()) != null) {
                    stringBuilder.append(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            IOUtils.close(br);
            IOUtils.close(is);
        }
//        Log.d(TAG, "getLocalCache: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * 清除缓存
     * @param url     根据url清除缓存
     * @return
     */
    public static boolean removeCache(String url) {
        Context context = UIUtils.getContext();
        DiskLruCache mDiskLruCache = null;
        File cacheDir = getDiskCacheDir(context, "news");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 10 * 1024 * 1024);
            String key = hashKeyForDisk(url);
            mDiskLruCache.remove(key);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断缓存文件是否存在
     * @param url   传入url地址作为缓存文件的文件名（经过MD5加密）
     * @return
     */
    public static boolean hasCacheFile(String url) {
        Context context = UIUtils.getContext();
        File cacheDir = getDiskCacheDir(context, "news");
        // 判断缓存目录是否存在，不存在返回false
        if (!cacheDir.exists()) {
            return false;
        }
        // 存在的时候继续判断
        String key = hashKeyForDisk(url);
        File cacheFile = new File(cacheDir.getAbsolutePath() + File.separator +key);
        Log.d(TAG, "hasCacheFile: 缓存文件名为" + cacheFile.getAbsolutePath());
        // 判断缓存文件是否存在，不存在返回false
        if (!cacheDir.exists()) {
            Log.d(TAG, "hasCacheFile: 不存在缓存文件");
            return false;
        }
        return true;
    }

}
