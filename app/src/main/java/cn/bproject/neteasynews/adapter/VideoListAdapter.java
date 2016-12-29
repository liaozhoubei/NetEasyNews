package cn.bproject.neteasynews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.bean.VideoBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class VideoListAdapter extends BaseAdapter {
    private final String TAG = VideoListAdapter.class.getSimpleName();

    private Context mContext;

    private ArrayList<VideoBean> mVideoBeanList;

    public VideoListAdapter(Context context, ArrayList<VideoBean> mVideoBeanList) {
        mContext = context;
        this.mVideoBeanList = mVideoBeanList;
    }

    @Override
    public int getCount() {
        return mVideoBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_video_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_from = (TextView) view.findViewById(R.id.tv_from);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.iv_video = (ImageView) view.findViewById(R.id.iv_video);
            viewHolder.tv_play_time = (TextView) view.findViewById(R.id.tv_play_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        VideoBean videoBean = mVideoBeanList.get(i);

        String imageSrc = videoBean.getCover();
        String title = videoBean.getTitle();
        String source = videoBean.getVideosource();
        String postTime = videoBean.getPtime();

            viewHolder.tv_title.setText(title);
        viewHolder.tv_from.setText(source);
        viewHolder.tv_play_time.setText(postTime);

            viewHolder.iv_video.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext)
                    .load(imageSrc)
                    .placeholder(R.drawable.defaultbg_h)
                    .crossFade()
                    .into(viewHolder.iv_video);
        return view;
    }

    class ViewHolder {
        public TextView tv_from;
        public TextView tv_play_time;
        public TextView tv_title;
        public ImageView iv_video;
    }
}
