package cn.bproject.neteasynews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.Tab;
import cn.bproject.neteasynews.fragment.AboutFragment;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.fragment.NewsFragment;
import cn.bproject.neteasynews.fragment.PhotoFragment;
import cn.bproject.neteasynews.fragment.VideoFragment;
import cn.bproject.neteasynews.widget.FragmentTabHost;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();


    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(5);
    private BaseFragment fragment;
    private PhotoFragment photoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initTab();

    }

    // 初始化Toolbar
    private void initToolbar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    // 初始化底部标签栏
    private void initTab() {
        // 新闻标签
        Tab tab_news = new Tab(NewsFragment.class,R.string.news_fragment,R.drawable.select_icon_news);
        // 图片标签
        Tab tab_photo = new Tab(PhotoFragment.class,R.string.photo_fragment,R.drawable.select_icon_photo);
        // 视频标签
        Tab tab_video = new Tab(VideoFragment.class,R.string.video_fragment,R.drawable.select_icon_video);
        // 我 标签
        Tab tab_about = new Tab(AboutFragment.class,R.string.about_fragment,R.drawable.select_icon_about);


        mTabs.add(tab_news);
        mTabs.add(tab_photo);
        mTabs.add(tab_video);
        mTabs.add(tab_about);


        // 设置FragmentTab
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        for (Tab tab : mTabs){

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            mTabHost.addTab(tabSpec,tab.getFragment(),null);

        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                LogUtils.d(TAG, "onTabChanged: mTabHost.setOnTabChangedListener" + R.string.news_fragment);
//                if (tabId==getString(R.string.news_fragment)){
//                    fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tabId);
//                    fragment.loadData();
//                }

            }
        });

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);

    }

    // 设置底部tab的图片和文字
    private View buildIndicator(Tab tab){

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return  view;
    }

}
