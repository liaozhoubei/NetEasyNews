package cn.bproject.neteasynews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.bean.NewsListNormalBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class NewsListAdapter extends BaseAdapter{

    private Context mContext;

    private ArrayList<NewsListNormalBean> mNewsListNormalBeanList;

    public NewsListAdapter(Context context, ArrayList<NewsListNormalBean> newsListNormalBeanList) {
        mContext = context;
        mNewsListNormalBeanList = newsListNormalBeanList;
    }

    @Override
    public int getCount() {
        return mNewsListNormalBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mNewsListNormalBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_news_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.item_news_linearlayout = (LinearLayout) view.findViewById(R.id.item_news_linearlayout);
            viewHolder.item_news_tv_title = (TextView) view.findViewById(R.id.item_news_tv_title);
            viewHolder.item_news_tv_img = (ImageView) view.findViewById(R.id.item_news_tv_img);
            viewHolder.item_news_tv_time = (TextView) view.findViewById(R.id.item_news_tv_time);
            viewHolder.item_news_tv_arrow = (TextView) view.findViewById(R.id.item_news_tv_arrow);
            viewHolder.item_news_tv_source = (TextView) view.findViewById(R.id.item_news_tv_source);
            viewHolder.item_news_three_image_view = (LinearLayout) view.findViewById(R.id.item_news_three_image_view);
            viewHolder.item_news_three_image_title = (TextView) view.findViewById(R.id.item_news_three_image_title);
            viewHolder.item_news_three_image = (LinearLayout) view.findViewById(R.id.item_news_three_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get(i);
        int hasAd = newsListNormalBean.getHasAD();
        List<NewsListNormalBean.ImgextraBean> imgextraBeenlist = newsListNormalBean.getImgextra();
        String imageSrc = newsListNormalBean.getImgsrc();
        String title = newsListNormalBean.getTitle();
        String source = newsListNormalBean.getSource();
        String postTime = newsListNormalBean.getPtime();

        viewHolder.item_news_tv_title.setText(title);
        // 设置图片
        Glide.with(mContext)
                .load(imageSrc)
                .placeholder(R.drawable.defaultbg)
                .crossFade()
                .into(viewHolder.item_news_tv_img);


        viewHolder.item_news_tv_time.setText(postTime);
        viewHolder.item_news_tv_source.setText(source);


        return view;
    }

    class ViewHolder {
        // 普通情况下的布局视图，左边标题，右边图片
        public LinearLayout item_news_linearlayout;
        public TextView item_news_tv_title;
        public ImageView item_news_tv_img;

        // 底部布局视图
        public TextView item_news_tv_time;
        public TextView item_news_tv_arrow;
        public TextView item_news_tv_source;


        // 拥有三张图片的布局，默认为Gone
        public LinearLayout item_news_three_image_view;
        public TextView item_news_three_image_title;
        public LinearLayout item_news_three_image;

    }
}
