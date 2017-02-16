package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.activity.AboutActivity;
import cn.bproject.neteasynews.activity.FeedbackActivity;
import cn.bproject.neteasynews.activity.SettingActivity;

/**
 * Created by Administrator on 2016/12/24.
 * 我 页面模块
 */

public class AboutFragment extends BaseFragment{
    private final String TAG = AboutFragment.class.getSimpleName();
    private String[] data;
    private AboutAdapter adapter;
    private ListView mListView;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_about, null);
        Toolbar myToolbar = initToolbar(mView, R.id.my_toolbar, R.id.toolbar_title, R.string.user_home);
        initView();
        bindData();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        data = getActivity().getResources().getStringArray(R.array.array_about_type);
        mListView = (ListView) mView.findViewById(R.id.list_item);

        View user_view = LayoutInflater.from(getActivity()).inflate(R.layout.user_view, mListView, false);
        ImageView user_icon = (ImageView) user_view.findViewById(R.id.user_icon);
        TextView user_name = (TextView) user_view.findViewById(R.id.user_name);

        mListView.addHeaderView(user_view);

    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                Uri uri;
//                1.项目主页
//                2.问题反馈
//                3.设置
//                4.关于App

                switch (position) {
                    case 0:
                        // 用户界面
                        Toast.makeText(getActivity(), "未实现登陆功能", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // 进入项目主页
//                String url = "https://github.com/liaozhoubei/NetEasyNews";
//                WebViewActivity.loadUrl(getActivity(), url, "加载中...");
                        uri = Uri.parse(getActivity().getResources().getString(R.string.project_url));
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        // 进入问题反馈
                        intent = new Intent(getActivity(), FeedbackActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        // 设置
                        intent = new Intent(getActivity(), SettingActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        // 进入关于App
                        intent = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void bindData() {
        adapter = new AboutAdapter();
        mListView.setAdapter(adapter);
    }

    private class AboutAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public String getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_about, null);
            TextView tv_about = (TextView) convertView.findViewById(R.id.tv_about);
            tv_about.setText(data[position]);
            return convertView;
        }
    }
}
