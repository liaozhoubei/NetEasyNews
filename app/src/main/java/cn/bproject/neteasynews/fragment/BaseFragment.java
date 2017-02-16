package cn.bproject.neteasynews.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.Utils.LocalCacheUtils;
import cn.bproject.neteasynews.common.DefineView;

/**
 * Created by Bei on 2016/12/24.
 */

public abstract class BaseFragment extends Fragment implements DefineView {
    public final int HANDLER_SHOW_NEWS = 11;
    public final int HANDLER_SHOW_ERROR = 12;
    public final int HANDLER_SHOW_REFRESH_LOADMORE = 13;
    public final int HANDLER_SHOW_REFRESH_LOADMORE_ERRO = 15;


    private final String TAG = BaseFragment.class.getSimpleName();

    /**
     * 设置toolbar标题居中，没有返回键
     * @param view
     * @param id    toolbar的id
     * @param titleId   textView的id
     * @param titleString   textView设置的文字
     * @return  返回toolbar
     */
    public Toolbar initToolbar(View view, int id, int titleId, int titleString) {
        Toolbar toolbar = (Toolbar) view.findViewById(id);
//        toolbar.setTitle("");
        TextView textView = (TextView) view.findViewById(titleId);
        textView.setText(titleString);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    /**
     *
     * @param key
     * @return  返回true表示是最新的保存的，返回false表示保存已经超过3个小时
     */
    public boolean isLastNews(String key) {
        long threeHour = 3 * 60 * 60 * 1000;
        long currentTime = System.currentTimeMillis();
        long saveTime = getUpdateTime(getActivity(), key, currentTime);
        // 判断保存缓存的时间与现在的时间是否超过3小时，没有超过就读取缓存
        long ll = currentTime - saveTime;

        if (ll <= threeHour) {
            Log.d(TAG, "saveTime  :  " + saveTime + "   ll:  " + ll + " ll < threeHour " + (ll < threeHour));
            return true;
        } else {
            Log.d(TAG, "saveTime  :  " + saveTime + "   ll:  " + ll + " ll > threeHour " + (ll > threeHour));
            return false;
        }
    }

    // 保存缓存
    public void saveCache(String url, String content){
        if (LocalCacheUtils.hasCacheFile(url)) {
            // 清除之前的缓存
            LocalCacheUtils.removeCache(url);
        }
        // 保存缓存
        LocalCacheUtils.setDiskLruCache(url, content);
    }

    // 设置保存缓存的时间
    public static void saveUpdateTime(Context ctx, String key, long value) {
        SharedPreferences sp = ctx.getSharedPreferences("save_time",
                Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    // 获取保存缓存的时间
    public static long getUpdateTime(Context ctx, String key, long defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("save_time",
                Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }
}
