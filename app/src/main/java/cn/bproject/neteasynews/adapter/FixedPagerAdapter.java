package cn.bproject.neteasynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.channelmanager.ProjectChannelBean;

import java.lang.reflect.Method;
import java.util.List;

import cn.bproject.neteasynews.fragment.BaseFragment;

/**
 * Created by Bei on 2016/12/25.
 */

public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private List<ProjectChannelBean> channelBeanList;
    private FragmentManager fm;
    private List<BaseFragment> fragments;

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void setChannelBean(List<ProjectChannelBean> newsBeans) {
        this.channelBeanList = newsBeans;
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
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
            removeFragment(container, position);
            fragment = (BaseFragment) super.instantiateItem(container, position);
        } catch (Exception e) {

        }
        return fragment;
    }

    private void removeFragment(ViewGroup container,int index) {
        String tag = getFragmentTag(container.getId(), index);
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null)
            return;
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
        ft = null;
        fm.executePendingTransactions();
    }

    private String getFragmentTag(int viewId, int index) {
        try {
            Class<FragmentPagerAdapter> cls = FragmentPagerAdapter.class;
            Class<?>[] parameterTypes = { int.class, long.class };
            Method method = cls.getDeclaredMethod("makeFragmentName",
                    parameterTypes);
            method.setAccessible(true);
            String tag = (String) method.invoke(this, viewId, index);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tname = channelBeanList.get(position % channelBeanList.size()).getTname();
        return tname;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
