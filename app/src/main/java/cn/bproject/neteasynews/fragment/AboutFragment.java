package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.VideoDetailActivity;

/**
 * Created by Administrator on 2016/12/24.
 * 我 页面模块
 */

public class AboutFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about, null);
        Button video = (Button) view.findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VideoDetailActivity.class));
            }
        });
        return view;
    }


}
