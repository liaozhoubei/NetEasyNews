package cn.bproject.neteasynews.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.R;

/**
 * Created by liaozhoubei on 2017/1/5.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTv_new_version;
    private TextView tv_updata_note;
    private TextView mTv_about_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolbar();
        initView();
        initListener();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("关于第二新闻");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    private void initView(){
        TextView text_version = (TextView) findViewById(R.id.text_version);
        mTv_new_version = (TextView) findViewById(R.id.tv_new_version);
        tv_updata_note = (TextView) findViewById(R.id.tv_updata_note);
        mTv_about_star = (TextView) findViewById(R.id.tv_about_star);
        text_version.setText(getVersion());
    }

    private void initListener(){
        mTv_new_version.setOnClickListener(this);
        tv_updata_note.setOnClickListener(this);
        mTv_about_star.setOnClickListener(this);
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.version_name) + "1.0";
        }
    }

    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("https://fir.im/cloudreader");
        Intent intent;
        switch (v.getId()){
            case R.id.tv_new_version:
                uri = Uri.parse("https://github.com/liaozhoubei/NetEasyNews");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.tv_updata_note:
                uri = Uri.parse("https://github.com/liaozhoubei/NetEasyNews/blob/master/README.md");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.tv_about_star:

                uri = Uri.parse("https://github.com/liaozhoubei/NetEasyNews");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
