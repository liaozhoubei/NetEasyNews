package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.activity.AboutActivity;
import cn.bproject.neteasynews.activity.FeedbackActivity;

/**
 * Created by Administrator on 2016/12/24.
 * 我 页面模块
 */

public class AboutFragment extends Fragment implements View.OnClickListener{
    private final String TAG = AboutFragment.class.getSimpleName();
    private ArrayList<String> mLv_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about, null);
        TextView tv_new_version = (TextView) view.findViewById(R.id.tv_new_version);
        TextView tv_function = (TextView) view.findViewById(R.id.tv_function);
        TextView tv_about_star = (TextView) view.findViewById(R.id.tv_about_star);
        tv_new_version.setOnClickListener(this);
        tv_function.setOnClickListener(this);
        tv_about_star.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        Uri uri;
        switch (v.getId()){
            case R.id.tv_new_version:
//                url = "https://github.com/liaozhoubei/NetEasyNews";
//                WebViewActivity.loadUrl(getActivity(), url, "加载中...");
                uri = Uri.parse("https://github.com/liaozhoubei/NetEasyNews");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.tv_function:
                intent = new Intent(getActivity(), FeedbackActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_about_star:
                intent = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
