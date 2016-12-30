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
import cn.bproject.neteasynews.bean.PicListBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class PicListAdapter extends BaseAdapter {
    private final String TAG = PicListAdapter.class.getSimpleName();

    private Context mContext;

    private ArrayList<PicListBean> mPicListBeens;

    public PicListAdapter(Context context, ArrayList<PicListBean> picListBeens) {
        mContext = context;
        mPicListBeens = picListBeens;
    }

    @Override
    public int getCount() {
        return mPicListBeens.size();
    }

    @Override
    public Object getItem(int i) {
        return mPicListBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_pic_linearlayout, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_play_time = (TextView) view.findViewById(R.id.tv_play_time);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        PicListBean picListBean = (PicListBean) getItem(i);

        String imageSrc = picListBean.getCover();
        String title = picListBean.getSetname();
        String datetime = picListBean.getDatetime();

        viewHolder.tv_title.setText(title);
        viewHolder.tv_play_time.setText(datetime);

        setNetPicture(imageSrc, viewHolder.iv_pic);

        return view;
    }

    class ViewHolder {
        public ImageView iv_pic;
        public TextView tv_title;
        public TextView tv_play_time;

    }

    /**
     * 使用Glide加载图片
     * @param url
     * @param img
     */
    private void setNetPicture(String url, ImageView img){
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.defaultbg)
                .crossFade()
                .into(img);
    }
}
