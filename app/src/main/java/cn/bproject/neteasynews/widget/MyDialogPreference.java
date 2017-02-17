package cn.bproject.neteasynews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2017/2/14.
 * 自定义的DialogPreference
 */

public class MyDialogPreference extends android.preference.DialogPreference {

    private OnDialogClick onDialogClick;

    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }

    public MyDialogPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        Log.d("MyDialogPreference", "onDialogClosed:现在是 " + positiveResult);
        // 设置保存在defaultSharePreference中的值
//        persistBoolean(positiveResult);
        if (onDialogClick != null){
            if (positiveResult){
                onDialogClick.PositiveButton();
            } else {
                onDialogClick.NegativeButton();
            }
        }
    }


    public interface OnDialogClick {
        // 点击确定
        void PositiveButton();
        // 点击取消
        void NegativeButton();
    }
}