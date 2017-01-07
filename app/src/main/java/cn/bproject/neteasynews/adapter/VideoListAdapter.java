package cn.bproject.neteasynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.bean.VideoBean;

/**
 * Created by liaozhoubei on 2017/1/7.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<VideoBean> mVideoBeanList;
    private OnItemClickListener mOnItemClickListener;

    public VideoListAdapter(Context context, ArrayList<VideoBean> mVideoBeanList) {
        mContext = context;
        this.mVideoBeanList = mVideoBeanList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_video_layout, null);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getIAdapterPosition();
                final VideoBean videoBean = mVideoBeanList.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, videoBean, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final  VideoBean videoBean = mVideoBeanList.get(position);

        String imageSrc = videoBean.getCover();
        String title = videoBean.getTitle();
        String source = videoBean.getVideosource();
        String postTime = videoBean.getPtime();

        holder.tv_title.setText(title);
        holder.tv_from.setText(source);
        holder.tv_play_time.setText(postTime);

        holder.iv_video.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(imageSrc)
                .placeholder(R.drawable.defaultbg_h)
                .crossFade()
                .into(holder.iv_video);

    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(int position, T t, View v);
    }

    public class ViewHolder extends IViewHolder {

        public TextView tv_from ;
        public TextView tv_title ;
        public TextView tv_play_time ;
        public ImageView iv_video ;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_video = (ImageView) itemView.findViewById(R.id.iv_video);
            tv_play_time = (TextView) itemView.findViewById(R.id.tv_play_time);

        }
    }


}
