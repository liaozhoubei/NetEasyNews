package cn.bproject.neteasynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.bean.BottomTab;
import cn.bproject.neteasynews.fragment.AboutFragment;
import cn.bproject.neteasynews.fragment.NewsFragment;
import cn.bproject.neteasynews.fragment.PhotoFragment;
import cn.bproject.neteasynews.fragment.VideoFragment;
import cn.bproject.neteasynews.widget.FragmentTabHost;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();


    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<BottomTab> mBottomTabs = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }

    // 初始化底部标签栏
    private void initTab() {
        // 新闻标签
        BottomTab bottomTab_news = new BottomTab(NewsFragment.class,R.string.news_fragment,R.drawable.select_icon_news);
        // 图片标签
        BottomTab bottomTab_photo = new BottomTab(PhotoFragment.class,R.string.photo_fragment,R.drawable.select_icon_photo);
        // 视频标签
        BottomTab bottomTab_video = new BottomTab(VideoFragment.class,R.string.video_fragment,R.drawable.select_icon_video);
        // 我 标签
        BottomTab bottomTab_about = new BottomTab(AboutFragment.class,R.string.about_fragment,R.drawable.select_icon_about);


        mBottomTabs.add(bottomTab_news);
        mBottomTabs.add(bottomTab_photo);
        mBottomTabs.add(bottomTab_video);
        mBottomTabs.add(bottomTab_about);


        // 设置FragmentTab
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        for (BottomTab bottomTab : mBottomTabs){

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(bottomTab.getTitle()));

            tabSpec.setIndicator(buildIndicator(bottomTab));

            mTabHost.addTab(tabSpec, bottomTab.getFragment(),null);

        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                LogUtils.d(TAG, "onTabChanged: mTabHost.setOnTabChangedListener" + R.string.news_fragment);

            }
        });

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);

    }

    // 设置底部tab的图片和文字
    private View buildIndicator(BottomTab bottomTab){

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(bottomTab.getIcon());
        text.setText(bottomTab.getTitle());

        return  view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String tag =  mTabHost.getCurrentTabTag();
        if (resultCode == 789){
            Bundle bundle = data.getExtras();
            int tabPosition = bundle.getInt("NewTabPostion");
            NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag(tag);
            newsFragment.setCurrentChannel(tabPosition);
            newsFragment.notifyChannelChange();
        }
    }
}
