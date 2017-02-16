package cn.bproject.neteasynews.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DataCleanManager;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.PrefUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;

public class SettingActivity extends Activity implements View.OnClickListener {
    private static final String TAG = SettingActivity.class.getSimpleName();
    private Context context;
    private LinearLayout ll_clear_cache;
    private TextView textView;
    private ThreadManager.ThreadPool threadpool;
    private LinearLayout ll_text_size;
    private ArrayList<String> mSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        initToolbar();
        initView();
        requestData();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("设置");
        threadpool = ThreadManager.getThreadPool();
    }

    private void initView() {
        ll_clear_cache = (LinearLayout) findViewById(R.id.ll_clear_cache);
        textView = (TextView) findViewById(R.id.tv_clear_cache_context);
        ll_text_size = (LinearLayout) findViewById(R.id.ll_text_size);
        ll_clear_cache.setOnClickListener(this);
        ll_text_size.setOnClickListener(this);



    }

    public void requestData(){
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                final String chche = getAllCacheSize();
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("缓存大小为:" + chche);
                    }
                });
            }
        });
    }

    private String getAllCacheSize(){
        String ExternalCache = context.getExternalCacheDir().getPath();
        //  路径为/data/data/<application package>/cache
        String CacheDir = context.getCacheDir().getPath();
        long cacheSize = getCacheSize(ExternalCache) + getCacheSize(CacheDir);
        return DataCleanManager.getFormatSize(cacheSize);
    }

    private long getCacheSize(String path) {
        long cache = 0;
        if (path != null){
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

    /**
     * 弹出清除缓存对话框
     */
    public void createClearCacheDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.dialog_fire_missiles)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        threadpool.execute(new Runnable() {
                            @Override
                            public void run() {
                                DataCleanManager.cleanExternalCache(context);
                                DataCleanManager.cleanInternalCache(context);
                            }
                        });
                        textView.setText("缓存大小为:0" );
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

    private int mTextSizeWhich;// 记录临时选择的字体大小(点击确定之前)

    /**
     * 弹出设置新闻详情页对话框
     */
    public void createChangeTextSizeDalog(){
        // Where we track the selected items
        mSelectedItems = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] items = getResources().getStringArray(R.array.array_text_size);
        int fontSize = PrefUtils.getInt(context, getString(R.string.text_size), 2);
        // Set the dialog title
        builder.setTitle(R.string.pick_toppings).setSingleChoiceItems(items, fontSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTextSizeWhich = which;
            }
        });

        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 将设置保存在SharedPreferences中
                PrefUtils.setInt(context, getString(R.string.text_size), mTextSizeWhich);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.create();
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_clear_cache:
                createClearCacheDialog();
                break;
            case R.id.ll_text_size:
                createChangeTextSizeDalog();
                break;
        }
    }
}
