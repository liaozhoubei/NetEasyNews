package cn.bproject.neteasynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.bean.ImageDetailBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;

/**
 * Created by Bei on 2016/12/31.
 */

public class PicDetailActivity extends AppCompatActivity implements DefineView {
    private final String TAG = PicDetailActivity.class.getSimpleName();

    private String tid; // 图片频道id，用于打开新闻详情页
    private String setid;  //   图片的分类

    private ViewPager mViewPager;
    private ArrayList<BaseFragment> mBaseFragmentArrayList;
    private static final String KEY_TID = "TID";  //频道id
    private static final String SETID = "SETID";
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private ImageDetailBean mImageDetailBean;
    private List<ImageDetailBean.PhotosBean> mPhotosBeens;
    private Context mContext;
    private boolean isGone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);
        mContext = this;
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_pic);

    }

    @Override
    public void initValidata() {
        mThreadPool = ThreadManager.getThreadPool();
        Intent intent = getIntent();
        tid = intent.getStringExtra(KEY_TID);
        setid = intent.getStringExtra(SETID);
        requestData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        PicDetailAdapter picDetailAdapter = new PicDetailAdapter(mPhotosBeens);
        mViewPager.setAdapter(picDetailAdapter);
        mViewPager.setCurrentItem(0);
    }

    public void requestData() {
//        mUrl = Api.CommonUrl + Api.toutiaoId + "/" + mStartIndex + Api.endUrl;
//        Log.d(TAG, "mUrl地址为: " + mUrl);
//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
////                String url = Api.PictureDetailUrl + getTid() + "/"+getAllParams(params) + ".json";

                String url = Api.PictureDetailUrl + tid + "/" + setid + ".json";
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mImageDetailBean = DataParse.ImageDetail(result);
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                LogUtils.d(TAG, ": 解析id" + mImageDetailBean);
                                if (mImageDetailBean != null) {
                                    mPhotosBeens = mImageDetailBean.getPhotos();
                                    bindData();
                                }

                            }
                        });
                    }

                    @Override
                    public void onError(String result, Exception e) {

                    }
                });


            }
        });

    }

    public class PicDetailAdapter extends PagerAdapter {

        private List<ImageDetailBean.PhotosBean> mPhotosBeens;
        ;

        public PicDetailAdapter(List<ImageDetailBean.PhotosBean> photosBeens) {
            mPhotosBeens = photosBeens;
        }

        @Override
        public int getCount() {
            return mPhotosBeens.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.fragment_pic_detail, null);
            ImageView iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_pic_sum = (TextView) view.findViewById(R.id.tv_pic_sum);
            final ScrollView sv_content = (ScrollView) view.findViewById(R.id.sv_content);
            TextView tv_pic_content = (TextView) view.findViewById(R.id.tv_pic_content);
            ImageDetailBean.PhotosBean photosBean = mPhotosBeens.get(position);
            final String title = photosBean.getImgtitle();
            final String note = photosBean.getNote();
            isGone = false;

            tv_title.setText(title);
            if (note != null) {
                tv_pic_content.setText(note);
                isGone = false;
            } else {
                sv_content.setVisibility(View.GONE);
                isGone = true;
            }

            Glide.with(mContext)
                    .load(photosBean.getImgurl())
                    .placeholder(R.drawable.defaultbg)
                    .crossFade()
                    .into(iv_pic);

            iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + note);
                    if (isGone) {
                        sv_content.setVisibility(View.VISIBLE);
                        isGone = false;
                    } else {
                        sv_content.setVisibility(View.GONE);
                        isGone = true;
                    }
                }
            });

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
