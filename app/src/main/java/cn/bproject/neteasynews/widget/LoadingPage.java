package cn.bproject.neteasynews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cn.bproject.neteasynews.R;

/**
 * 根据当前状态来显示不同页面的自定义控件
 * <p>
 * - 未加载 - 加载中 - 加载失败 - 数据为空 - 加载成功
 */
public class LoadingPage extends FrameLayout {

    private final String TAG = LoadingPage.class.getSimpleName();

    private ResultState mCurrentState = ResultState.STATE_UNLOAD;// 当前状态

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    private Context mContext;
    private LoadingClickListener loadingClickListener;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 正在加载
        if (mLoadingView == null) {
            mLoadingView = onCreateLoadingView();
            addView(mLoadingView);
        }

        // 加载失败
        if (mErrorView == null) {
            mErrorView = onCreateErrorView();
            // 点击重试
            mErrorView.findViewById(R.id.btn_retry).setOnClickListener(
                    new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (loadingClickListener != null) {
                                loadingClickListener.clickListener();
                            }
                        }
                    });
            addView(mErrorView);
        }

        // 数据为空
        if (mEmptyView == null) {
            mEmptyView = onCreateEmptyView();
            addView(mEmptyView);
        }

        showRightPage();
    }

    /**
     * 根据当前状态,展示正确页面
     */
    private void showRightPage() {
        if (mLoadingView != null) {
            mLoadingView
                    .setVisibility((mCurrentState == ResultState.STATE_LOADING || mCurrentState == ResultState.STATE_UNLOAD) ? View.VISIBLE
                            : View.GONE);
        }

        if (mEmptyView != null) {
            mEmptyView
                    .setVisibility(mCurrentState == ResultState.STATE_EMPTY ? View.VISIBLE
                            : View.GONE);
        }

        if (mErrorView != null) {
            mErrorView
                    .setVisibility(mCurrentState == ResultState.STATE_ERROR ? View.VISIBLE
                            : View.GONE);
        }

    }

    /**
     * 初始化正在加载布局
     */
    private View onCreateLoadingView() {

        return LayoutInflater.from(mContext).inflate(R.layout.page_loading, null);
    }

    /**
     * 初始化加载失败布局
     */
    private View onCreateErrorView() {
        return LayoutInflater.from(mContext).inflate(R.layout.page_error, null);
    }

    /**
     * 初始化数据为空布局
     */
    private View onCreateEmptyView() {
        return LayoutInflater.from(mContext).inflate(R.layout.page_empty, null);
    }

    public void setLoadingView(){
        setCurrentState(ResultState.STATE_LOADING);
        refreshView();
    }

    public void setErrorView(){
        setCurrentState(ResultState.STATE_ERROR);
        refreshView();
    }

    public void setEmptyView(){
        setCurrentState(ResultState.STATE_EMPTY);
        refreshView();
    }

    public void setSuccessView(){
        setCurrentState(ResultState.STATE_SUCCESS);
        refreshView();
    }

    public void setLoadingClickListener(LoadingClickListener buttonClickListener) {
        this.loadingClickListener = buttonClickListener;
    }

    public void setCurrentState(ResultState currentState) {
        this.mCurrentState = currentState;
    }

    public ResultState getCurrentState() {
        return mCurrentState;
    }

    public void refreshView() {
        mCurrentState = getCurrentState();
        showRightPage();
    }

    /**
     * 使用枚举表示访问网络的几种状态
     */
    public enum ResultState {
        STATE_UNLOAD,    // 尚未开始加载数据的时候
        STATE_LOADING,    // 正在加载数据
        STATE_SUCCESS, // 访问成功
        STATE_EMPTY, // 数据为空
        STATE_ERROR;// 访问失败
    }

    public interface LoadingClickListener {
        void clickListener();
    }

}
