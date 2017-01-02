//package cn.bproject.neteasynews.fragment.photo;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//import cn.bproject.neteasynews.R;
//import cn.bproject.neteasynews.bean.ImageDetailBean;
//import cn.bproject.neteasynews.common.Api;
//import cn.bproject.neteasynews.common.DefineView;
//import cn.bproject.neteasynews.fragment.BaseFragment;
//import cn.bproject.neteasynews.http.PicDetailProtocol;
//
///**
// * Created by Bei on 2016/12/31.
// *
// * 暂时弃用
// */
//
//public class PicDetailFragment extends BaseFragment implements DefineView{
//    private String tid; // 图片频道id，用于打开新闻详情页
//    private String setid;  //   图片的分类
//    private static final String KEY_TID = "TID";  //频道id
//    private static final String SETID = "SETID";
//    private ViewPager mViewPager;
//    private View mView;
//    private ImageView mIv_pic;
//    private TextView mTv_title;
//    private TextView mTv_pic_sum;
//    private TextView mTv_pic_content;
//
//    public static PicDetailFragment newInstance(String tid, String setid) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(KEY_TID, tid);
//        bundle.putSerializable(SETID, setid);
//        PicDetailFragment fragment = new PicDetailFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mView = inflater.inflate(R.layout.fragment_pic_detail, null);
//
//        return mView;
//    }
//
//    @Override
//    public void initView() {
//        mIv_pic = (ImageView) mView.findViewById(R.id.iv_pic);
//        mTv_title = (TextView) mView.findViewById(R.id.tv_title);
//        mTv_pic_sum = (TextView) mView.findViewById(R.id.tv_pic_sum);
//        mTv_pic_content = (TextView) mView.findViewById(R.id.tv_pic_content);
//
//    }
//
//    @Override
//    public void initValidata() {
//        if (getArguments() != null) {
//            //取出保存的频道TID
//            tid = getArguments().getString(KEY_TID);
//            setid = getArguments().getString(SETID);
//        }
//    }
//
//    @Override
//    public void initListener() {
//
//    }
//
//    @Override
//    public void bindData() {
//        PicDetailProtocol picDetailProtocol = new PicDetailProtocol(tid);
//        ImageDetailBean imageDetailBean = picDetailProtocol.getDetailData(Api.PictureDetailUrl + tid +"/" + setid +".json");
//        imageDetailBean.getImgsum();
//        List<ImageDetailBean.PhotosBean> photosBeens = imageDetailBean.getPhotos();
//
//    }
//
//    public class PicDetailAdapter extends FragmentStatePagerAdapter {
//        public PicDetailAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
//}
