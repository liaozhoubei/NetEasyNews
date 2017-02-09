package cn.bproject.neteasynews.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.Utils.LocalCacheUtils;
import cn.bproject.neteasynews.Utils.PrefUtils;

/**
 * Created by Bei on 2016/12/24.
 */

public abstract class BaseFragment extends Fragment{

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

    public boolean isLastNews(String key) {
        long threeHour = 3 * 60 * 60 * 1000;
        long currentTime = System.currentTimeMillis();
        long saveTime = PrefUtils.getLong(getActivity(), key, currentTime);
        // 判断保存缓存的时间与现在的时间是否超过3小时，没有超过就读取缓存
        long ll = currentTime - saveTime;

        if (ll < threeHour) {
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
}
