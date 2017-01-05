package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.bproject.neteasynews.AboutActivity;
import cn.bproject.neteasynews.FeedbackActivity;
import cn.bproject.neteasynews.R;

/**
 * Created by Administrator on 2016/12/24.
 * 我 页面模块
 */

public class AboutFragment extends Fragment{
    private final String TAG = AboutAdapter.class.getSimpleName();
    private ArrayList<String> mLv_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about, null);
        ListView lv_about = (ListView) view.findViewById(R.id.lv_about);
        mLv_list = new ArrayList<>();
        mLv_list.add("账号信息");
        mLv_list.add("意见反馈");
        mLv_list.add("关于App");
        lv_about.setAdapter(new AboutAdapter());
        lv_about.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: 点击了");
                Intent intent = null;
                if (position == 0) {
                    Toast.makeText(getActivity(), "暂未实现", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    intent = new Intent(getActivity(), FeedbackActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AboutActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }

    private class AboutAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mLv_list.size();
        }

        @Override
        public Object getItem(int position) {
            return mLv_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_about, null);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_about);
            textView.setText(mLv_list.get(position));
            return convertView;
        }
    }


}
