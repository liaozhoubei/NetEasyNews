package cn.bproject.neteasynews.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import cn.bproject.neteasynews.bean.NewsBean;
import cn.bproject.neteasynews.fragment.BaseFragment;

/**
 * Created by Bei on 2016/12/25.
 */

public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private List<NewsBean> newsBeans;

    public void setNewsBean(List<NewsBean> newsBeans) {
        this.newsBeans = newsBeans;
    }

    private List<BaseFragment> fragments;

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) super.instantiateItem(container, position);
        } catch (Exception e) {

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsBeans.get(position % newsBeans.size()).getTitle();
    }



}
