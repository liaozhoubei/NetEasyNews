package cn.bproject.neteasynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.bproject.neteasynews.widget.LoadingPage;
import cn.bproject.neteasynews.widget.LoadingPage.ResultState;

/**
 * Created by Bei on 2016/12/24.
 */

public abstract class BaseFragment extends Fragment{

    private final String TAG = BaseFragment.class.getSimpleName();

    private Context mContext;

    private LoadingPage mLoadingPage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: BaseFragment的构造方法执行了");
        mLoadingPage = new LoadingPage(mContext) {

            @Override
            public View onCreateSuccessView() {
                // 注意:此处一定要调用BaseFragment的onCreateSuccessView, 否则栈溢出
                View view = BaseFragment.this.onCreateSuccessView();
                return view;
            }

            @Override
            public ResultState onLoad() {
                Log.d(TAG, "onLoad: BaseFragment执行了");
                return BaseFragment.this.onLoad();
            }

        };

        return mLoadingPage;
    }

    // 加载成功的布局, 必须由子类来实现
    public abstract View onCreateSuccessView();

    // 加载网络数据, 必须由子类来实现
    public abstract ResultState onLoad();



    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    // 开始加载数据
    public void loadData() {
        if (mLoadingPage != null) {
            Log.d(TAG, "loadData: mLoadingPage执行了");
            mLoadingPage.loadData();
        } else {
            Log.d(TAG, "loadData: mLoadingPage为空");
        } 
    }



    // 对网络返回数据的合法性进行校验
    public ResultState check(Object obj) {
        // 下列为测试数据
        if (obj != null) {
            if ("123".equals(obj.toString())){
                return ResultState.STATE_ERROR;
            } else {
                return ResultState.STATE_SUCCESS;
            }
        } else {
            return ResultState.STATE_EMPTY;
        }

    }
}
