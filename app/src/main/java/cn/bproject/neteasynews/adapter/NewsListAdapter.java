package cn.bproject.neteasynews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.bean.NewsListNormalBean;

import static cn.bproject.neteasynews.R.id.item_news_tv_title;

/**
 * Created by Administrator on 2016/12/26.
 */

public class NewsListAdapter extends BaseAdapter {
    private final String TAG = NewsListAdapter.class.getSimpleName();

    private static final int BIG_IMG = 0;
    private static final int SMALL_IMG = 1;
    private static final int THREE_IMG = 2;

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
    public int getItemViewType(int position) {
        NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get(position);
        int hasAd = newsListNormalBean.getHasAD();
        List<NewsListNormalBean.ImgextraBean> imgextraBeenlist = newsListNormalBean.getImgextra();
        if (hasAd == 1) {
            return BIG_IMG;
        } else if (hasAd != 1 && (imgextraBeenlist != null && imgextraBeenlist.size() > 1)) {
            return THREE_IMG;
        }
        return SMALL_IMG;

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            if (getItemViewType(i) == BIG_IMG) {
                view = View.inflate(mContext, R.layout.item_news_big_pic, null);
                viewHolder.big_Image = (ImageView) view.findViewById(R.id.big_Image);
            } else if (getItemViewType(i) == THREE_IMG) {
                view = View.inflate(mContext, R.layout.item_news_three_pic, null);
                viewHolder.one_image = (ImageView) view.findViewById(R.id.one_image);
                viewHolder.two_image = (ImageView) view.findViewById(R.id.two_image);
                viewHolder.three_image = (ImageView) view.findViewById(R.id.three_image);
            } else {
                view = View.inflate(mContext, R.layout.item_news_layout, null);
                viewHolder.item_news_tv_img = (ImageView) view.findViewById(R.id.item_news_tv_img);
            }
            viewHolder.item_news_tv_title = (TextView) view.findViewById(item_news_tv_title);
            viewHolder.item_news_tv_time = (TextView) view.findViewById(R.id.item_news_tv_time);
            viewHolder.item_news_tv_arrow = (TextView) view.findViewById(R.id.item_news_tv_arrow);
            viewHolder.item_news_tv_source = (TextView) view.findViewById(R.id.item_news_tv_source);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get(i);
        // 如果有额外图片存在
        List<NewsListNormalBean.ImgextraBean> imgextraBeenlist = newsListNormalBean.getImgextra();
        String imageSrc = newsListNormalBean.getImgsrc();
        String title = newsListNormalBean.getTitle();
        String source = newsListNormalBean.getSource();
        String postTime = newsListNormalBean.getPtime();
        // 文章的id号
        String docid = newsListNormalBean.getDocid();

        if (getItemViewType(i) == BIG_IMG) {
            // 一张大图的情况
//            viewHolder.big_Image.setScaleType(ImageView.ScaleType.);
            Glide.with(mContext)
                    .load(imageSrc)
                    .placeholder(R.drawable.defaultbg_h)
                    .crossFade()
                    .into(viewHolder.big_Image);
        } else if (getItemViewType(i) == THREE_IMG) {
            // 三张图片的情况
            setNetPicture(imageSrc, viewHolder.one_image);
            for (int j = 0; j < imgextraBeenlist.size(); j++) {
                if (j == 0) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), viewHolder.two_image);
                } else if (j == 1) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), viewHolder.three_image);
                }
            }
        } else {
            // 设置图片
            setNetPicture(imageSrc, viewHolder.item_news_tv_img);
        }

        viewHolder.item_news_tv_title.setText(title);
        viewHolder.item_news_tv_time.setText(postTime);
        viewHolder.item_news_tv_source.setText(source);

        return view;
    }

    class ViewHolder {
        // 普通情况下的布局视图，左边标题，右边图片
        public TextView item_news_tv_title;
        public ImageView item_news_tv_img;

        // 底部布局视图
        public TextView item_news_tv_time;
        public TextView item_news_tv_arrow;
        public TextView item_news_tv_source;


        // 一张大图
        public ImageView big_Image;
        // 拥有三张图片的布局
        public ImageView one_image;
        public ImageView two_image;
        public ImageView three_image;

    }

    private void setNetPicture(String url, ImageView img) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.defaultbg)
                .crossFade()
                .into(img);
    }

}
