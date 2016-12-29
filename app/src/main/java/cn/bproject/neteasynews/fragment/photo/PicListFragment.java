package cn.bproject.neteasynews.fragment.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.BaseFragment;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by liaozhoubei on 2016/12/29.
 */

public class PicListFragment  extends BaseFragment implements DefineView {
    private String tid; // 图片频道id，用于打开新闻详情页
    private View mView;
    private UltimateRecyclerView mRefreshRecyclerView;


    public static PicListFragment newInstance(String tid){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, tid);
        PicListFragment fragment = new PicListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pic_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        mRefreshRecyclerView = (UltimateRecyclerView) mView.findViewById(R.id.pull_load_more_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRefreshRecyclerView.setLayoutManager(layoutManager);
        mRefreshRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("PicListFragment", "run: xialashux");

                    }
                });
            }
        });


//        mRefreshRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRefreshRecyclerView.setPullLoadMoreCompleted();
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                Log.d("PicListFragment", "run: xialashux");
//
//                            }
//                        });
//                    }
//                }).start();
//            }
//
//            @Override
//            public void onLoadMore() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                Log.d("PicListFragment", "run: xialashux");
//                                mRefreshRecyclerView.setPullLoadMoreCompleted();
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });
//
//        testlist = new ArrayList<>();
//        for (int i = 0; i < 30; i ++) {
//            testlist.add("数据" + i);
//        }
//        ttaa = new testAdapter(testlist);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        mRefreshRecyclerView.setLinearLayout();
//        mRefreshRecyclerView.setAdapter(ttaa);
    }

    @Override
    public void initValidata() {
        if(getArguments()!=null){
            //取出保存的频道TID
            tid = getArguments().getString("TID");
        }
    }

    @Override
    public void initListener() {


    }

    @Override
    public void bindData() {

    }

    private testAdapter ttaa;
    private ArrayList<String> testlist;
  public class testAdapter extends RecyclerView.Adapter<testAdapter.ViewHolder>{

      private ArrayList<String> testlist;

      public testAdapter(ArrayList<String> testlist) {
          this.testlist = testlist;
      }

      @Override
      public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(getActivity()).inflate(R.layout.testitem, null);
          testAdapter.ViewHolder viewholder = new testAdapter.ViewHolder(view);
          return viewholder;
      }

      @Override
      public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(testlist.get(position));
      }


      @Override
      public int getItemCount() {
          return testlist.size();
      }

      public class ViewHolder extends RecyclerView.ViewHolder {
          public TextView textView;
          public View mView;

          public ViewHolder(View itemView) {
              super(itemView);
              mView = itemView;
              textView = (TextView) mView.findViewById(R.id.teee);
          }
      }

  }
}
