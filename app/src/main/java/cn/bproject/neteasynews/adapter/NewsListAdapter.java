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

import static cn.bproject.neteasynews.R.id.item_news_three_image_view;

/**
 * Created by Administrator on 2016/12/26.
 */

public class NewsListAdapter extends BaseAdapter {
    private final String TAG = NewsListAdapter.class.getSimpleName();

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


            viewHolder.item_news_three_image_view = (LinearLayout) view.findViewById(item_news_three_image_view);
            viewHolder.item_news_three_image_title = (TextView) view.findViewById(R.id.item_news_three_image_title);
            viewHolder.item_news_three_image = (LinearLayout) view.findViewById(R.id.item_news_three_image);
            viewHolder.big_Image = (ImageView) view.findViewById(R.id.big_Image);
            viewHolder.one_image = (ImageView) view.findViewById(R.id.one_image);
            viewHolder.two_image = (ImageView) view.findViewById(R.id.two_image);
            viewHolder.three_image = (ImageView) view.findViewById(R.id.three_image);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsListNormalBean newsListNormalBean = mNewsListNormalBeanList.get(i);
        // 如果有额外图片存在
        List<NewsListNormalBean.ImgextraBean> imgextraBeenlist = newsListNormalBean.getImgextra();
        String imageSrc = newsListNormalBean.getImgsrc();
        final String title = newsListNormalBean.getTitle();
        String source = newsListNormalBean.getSource();
        String postTime = newsListNormalBean.getPtime();
        // 文章的id号
        String docid = newsListNormalBean.getDocid();
        int hasAd = newsListNormalBean.getHasAD();
        if (hasAd == 1) {

            viewHolder.setOtherLayoutVisable(View.VISIBLE, true);
            viewHolder.item_news_three_image_title.setText(title);
            viewHolder.big_Image.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext)
                    .load(imageSrc)
                    .placeholder(R.drawable.defaultbg_h)
                    .crossFade()
                    .into(viewHolder.big_Image);

        } else if (hasAd != 1 && (imgextraBeenlist != null && imgextraBeenlist.size() > 1)) {
            viewHolder.setOtherLayoutVisable(View.VISIBLE, false);
            viewHolder.item_news_three_image_title.setText(title);
            setNetPicture(imageSrc, viewHolder.one_image);
            for (int j = 0; j < imgextraBeenlist.size(); j++) {
                if (j == 0) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), viewHolder.two_image);
                } else if (j == 1) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), viewHolder.three_image);
                }
            }
        } else {
            viewHolder.setOtherLayoutVisable(View.GONE, false);
            viewHolder.item_news_tv_title.setText(title);
            // 设置图片
            setNetPicture(imageSrc, viewHolder.item_news_tv_img);
        }

        viewHolder.item_news_three_image_title.setText(title);


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
        public ImageView big_Image;
        public ImageView one_image;
        public ImageView two_image;
        public ImageView three_image;

        /**
         * 传入View.GONE则显示普通视图，传入View.VISIBLE则显示三张图片或者一张大图
         *
         * @param visable
         * @param b       当View.VISIBLE并且b为true时展示大图
         */
        public void setOtherLayoutVisable(int visable, boolean b) {
            if (View.GONE == visable) {
                //隐藏有三张图片或者一张大图的视图
                item_news_three_image_view.setVisibility(View.GONE);
                item_news_three_image_title.setVisibility(View.GONE);
                item_news_three_image.setVisibility(View.GONE);


                //显示普通视图
                item_news_linearlayout.setVisibility(View.VISIBLE);
                item_news_tv_title.setVisibility(View.VISIBLE);
                item_news_tv_img.setVisibility(View.VISIBLE);
            } else if (View.VISIBLE == visable) {
                //显示有三张图片或者一张大图的视图
                item_news_three_image_view.setVisibility(View.VISIBLE);
                item_news_three_image_title.setVisibility(View.VISIBLE);
                item_news_three_image.setVisibility(View.VISIBLE);
                if (b) {
                    big_Image.setVisibility(View.VISIBLE);
                    one_image.setVisibility(View.GONE);
                    two_image.setVisibility(View.GONE);
                    three_image.setVisibility(View.GONE);
                } else {
                    big_Image.setVisibility(View.GONE);
                    one_image.setVisibility(View.VISIBLE);
                    two_image.setVisibility(View.VISIBLE);
                    three_image.setVisibility(View.VISIBLE);
                }


                //隐藏普通视图
                item_news_linearlayout.setVisibility(View.GONE);
                item_news_tv_title.setVisibility(View.GONE);
                item_news_tv_img.setVisibility(View.GONE);
            }

        }
    }

    private void setNetPicture(String url, ImageView img){
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.defaultbg)
                .crossFade()
                .into(img);
    }
}
