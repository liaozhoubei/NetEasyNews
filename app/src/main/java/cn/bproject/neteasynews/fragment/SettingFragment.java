package cn.bproject.neteasynews.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import java.io.File;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DataCleanManager;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.widget.MyDialogPreference;

/**
 * Created by Administrator on 2017/2/17.
 */

public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, DefineView {
    private final String TAG = SettingFragment.class.getSimpleName();
    private MyDialogPreference myDialogPreference;
    private ListPreference listPreference;
    private SharedPreferences sharedPreferences;
    private Context context;
    private ThreadManager.ThreadPool threadpool;
    private String[] text_size_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
//        addPreferencesFromResource(R.xml.preferences);
        addPreferencesFromResource(R.xml.preference_setting);

        context = getActivity();
        threadpool = ThreadManager.getThreadPool();


        initView();
        initValidata();
        initListener();


    }

    @Override
    public void onResume() {
        super.onResume();
        myDialogPreference.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        listPreference.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        myDialogPreference.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        listPreference.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void initView() {
        myDialogPreference = (MyDialogPreference) findPreference("clear_cache");
        listPreference = (ListPreference) findPreference("text_size");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void initValidata() {
        int text_size_value = Integer.valueOf(sharedPreferences.getString("text_size", "2"));
        text_size_title = getActivity().getResources().getStringArray(R.array.array_text_size_title);
        listPreference.setSummary(text_size_title[text_size_value]);
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                final String chche = getAllCacheSize();
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        myDialogPreference.setSummary("缓存大小为:" + chche);
                    }
                });
            }
        });
    }

    @Override
    public void initListener() {
        myDialogPreference.setOnDialogClick(new MyDialogPreference.OnDialogClick() {
            @Override
            public void PositiveButton() {
                threadpool.execute(new Runnable() {
                    @Override
                    public void run() {
                        DataCleanManager.cleanExternalCache(context);
                        DataCleanManager.cleanInternalCache(context);
                    }
                });
                myDialogPreference.setSummary("缓存大小为:0" );
            }

            @Override
            public void NegativeButton() {
                LogUtils.d(TAG, "取消清除缓存");
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("text_size")) {
            int text_size_value = Integer.valueOf(sharedPreferences.getString(key, "2"));
            listPreference.setSummary(text_size_title[text_size_value]);
        }
    }

    private String getAllCacheSize() {
        String ExternalCache = context.getExternalCacheDir().getPath();
        //  路径为/data/data/<application package>/cache
        String CacheDir = context.getCacheDir().getPath();
        long cacheSize = getCacheSize(ExternalCache) + getCacheSize(CacheDir);
        return DataCleanManager.getFormatSize(cacheSize);
    }

    private long getCacheSize(String path) {
        long cache = 0;
        if (path != null) {
            File file = new File(path);
            try {
                cache = DataCleanManager.getFolderSize(file);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e("SettingActivity", "无法获取缓存目录");
            }
        }
        return cache;
    }

    @Override
    public void bindData() {

    }
}
