package cn.bproject.neteasynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.bean.PicListBean;

/**
 * Created by liaozhoubei on 2017/1/7.
 */

public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PicListBean> mPicListBeens;
    private OnItemClickListener mOnItemClickListener;

    public PicListAdapter(Context context, ArrayList<PicListBean> picListBeens) {
        mContext = context;
        mPicListBeens = picListBeens;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_pic_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PicListBean picListBean = mPicListBeens.get(position);

        String imageSrc = picListBean.getCover();
        String title = picListBean.getSetname();

        holder.tv_title.setText(title);

        Glide.with(mContext)
                .load(imageSrc)
                .placeholder(R.drawable.defaultbg)
                .crossFade()
                .into(holder.iv_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getIAdapterPosition();
                final PicListBean picListBean = mPicListBeens.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, picListBean, v);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPicListBeens.size();
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

        public LinearLayout rl_root;
        public ImageView iv_pic;
        public TextView tv_title;


        public ViewHolder(View itemView) {
            super(itemView);
            rl_root = (LinearLayout) itemView.findViewById(R.id.rl_root);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


}
