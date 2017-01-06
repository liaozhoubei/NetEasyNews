package cn.bproject.neteasynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.CategoryDataUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.adapter.FixedPagerAdapter;
import cn.bproject.neteasynews.bean.NewsBean;
import cn.bproject.neteasynews.fragment.news.NewsListFragment;

import static cn.bproject.neteasynews.R.id.tab_layout;

/**
 * Created by Administrator on 2016/12/24.
 * 新闻模块
 */

public class NewsFragment extends Fragment {

    private final String TAG = NewsFragment.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mNewsViewpager;
    private View mView;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<BaseFragment> fragments;
    private static List<NewsBean> newsBeans;
    private BaseFragment mFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tablayout_pager, container, false);
        mTabLayout = (TabLayout) mView.findViewById(tab_layout);
        mNewsViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);

        Toolbar myToolbar = (Toolbar) mView.findViewById(R.id.my_toolbar);
//        getActivity().setSupportActionBar(myToolbar);

        newsBeans = CategoryDataUtils.getNewsCategoryBeans();

        initValidata();
        return mView;
    }



    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setNewsBean(newsBeans);
        fragments = new ArrayList<BaseFragment>();
        for (int i = 0; i < newsBeans.size(); i++) {
            BaseFragment  fragment = NewsListFragment.newInstance(newsBeans.get(i).getTid());
            fragments.add(fragment);
        }

        fixedPagerAdapter.setFragments(fragments);

        mNewsViewpager.setAdapter(fixedPagerAdapter);
        mTabLayout.setupWithViewPager(mNewsViewpager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mNewsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d(TAG, "onPageSelected: mNewsViewpager页面监听执行了" + position);
                BaseFragment fragment = fragments.get(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }



}
