package cn.bproject.neteasynews.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.fragment.SettingFragment;
import cn.bproject.neteasynews.widget.AppCompatPreferenceActivity;

import static cn.bproject.neteasynews.R.id.my_toolbar;

public class SettingActivity extends AppCompatPreferenceActivity {
    private static final String TAG = SettingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        initToolbar();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SettingFragment settingFragment = new  SettingFragment();
        fragmentTransaction.replace(R.id.id_content, settingFragment);
        fragmentTransaction.commit();


    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(my_toolbar);
        toolbar.setTitle("");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("设置");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
