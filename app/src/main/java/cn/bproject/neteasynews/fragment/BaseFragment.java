package cn.bproject.neteasynews.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Bei on 2016/12/24.
 */

public abstract class BaseFragment extends Fragment{

    private final String TAG = BaseFragment.class.getSimpleName();

//    private Context mContext;
//
//    private LoadingPage mLoadingPage;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mContext = context;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        Log.d(TAG, "onCreateView: BaseFragment的构造方法执行了");
//        mLoadingPage = new LoadingPage(mContext) {
//
//            @Override
//            public View onCreateSuccessView() {
//                // 注意:此处一定要调用BaseFragment的onCreateSuccessView, 否则栈溢出
//                View view = BaseFragment.this.onCreateSuccessView();
//                return view;
//            }
//
//            @Override
//            public ResultState onLoad() {
//                Log.d(TAG, "onLoad: BaseFragment执行了");
//                return BaseFragment.this.onLoad();
//            }
//
//        };
//
//        return mLoadingPage;
//    }
//
//    // 加载成功的布局, 必须由子类来实现
//    public abstract View onCreateSuccessView();
//
//    // 加载网络数据, 必须由子类来实现
//    public abstract ResultState onLoad();


//    /**
//     * 未知bug，如果不在onStart或者  onResume中调用loadData()方法，则第一个fragment会一直处于loading状态
//     * 原因为loadData()被调用的时候，BaseFragment的onCreateView没有调用，导致mLoadingPage为空。
//     * 在此次调用的弊端为，每次使用TabLayout标签，loadData()方法会被调用两次，
//     * 一次为Viewpager的点击事件，一次为fragment创建的时候
//     */
//    @Override
//    public void onResume() {
//        super.onResume();
//        loadData();
//    }
//
//    // 开始加载数据
//    public void loadData() {
//        if (mLoadingPage != null) {
//            Log.d(TAG, "loadData: mLoadingPage执行了");
//            mLoadingPage.loadData();
//        } else {
//            Log.d(TAG, "loadData: mLoadingPage为空");
//        }
//    }



//    // 对网络返回数据的合法性进行校验
//    public ResultState check(Object obj) {
//        if (obj != null) {
//            if (obj instanceof ArrayList) {// 判断是否是集合
//                ArrayList list = (ArrayList) obj;
//
//                if (list.isEmpty()) {
//                    return ResultState.STATE_EMPTY;
//                } else {
//                    return ResultState.STATE_SUCCESS;
//                }
//            }
//        }
//
//        return ResultState.STATE_ERROR;
//
//    }
}
