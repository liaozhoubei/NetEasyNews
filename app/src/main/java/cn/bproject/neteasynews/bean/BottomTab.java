package cn.bproject.neteasynews.bean;

/**
 * Created by Ivan on 15/9/25.
 * 底部标签栏标签
 * 新闻、图片、视频、我
 */
public class BottomTab {

    private  int title;
    private  int icon;
    private Class fragment;

    public BottomTab(Class fragment, int title, int icon) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
