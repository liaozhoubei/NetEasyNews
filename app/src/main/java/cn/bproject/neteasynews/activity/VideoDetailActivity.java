package cn.bproject.neteasynews.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.Utils.NetWorkUtil;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.fragment.VideoFragment;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;
import cn.bproject.neteasynews.widget.LoadingPage;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by liaozhoubei on 2017/1/3.
 */

public class VideoDetailActivity extends AppCompatActivity implements DefineView {
    private final String TAG = VideoFragment.class.getSimpleName();

    private final String VID = "VID";
    private String vid;

    private TextView percentTv;
    private TextView netSpeedTv;
    private VideoView mVideoView;

    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private VideoBean mVideoBean;
    private String mMp4_url;
    private RelativeLayout mRl_video;
    private LoadingPage mLoadingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Intent intent = getIntent();
        if (intent != null) {
            vid = intent.getStringExtra(VID);
        }
        Log.d("VideoDetailActivity", "onCreate: " + vid);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }


        mRl_video = (RelativeLayout) findViewById(R.id.rl_video);
        // 显示缓冲百分比的TextView
        percentTv = (TextView) findViewById(R.id.buffer_percent);
        //显示下载网速的TextView
        netSpeedTv = (TextView) findViewById(R.id.net_speed);
        mVideoView = (VideoView) findViewById(R.id.vitamio);

        mLoadingPage = (LoadingPage) findViewById(R.id.loading_page);

    }

    @Override
    public void initValidata() {
        if (NetWorkUtil.isWifiConnected(this)){
            // 创建线程池
            mThreadPool = ThreadManager.getThreadPool();
            showLoadingPage();
            requestData();
        } else {
            Toast.makeText(this, "非WIFI状态", Toast.LENGTH_SHORT).show();
        }
    }

    public void requestData() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
////                http://c.m.163.com/nc/video/detail/VC8TVUN5N.html
                String url = Api.videoDetailUrl + vid + Api.EndUrlVideoDetailUrl;
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mVideoBean= DataParse.VideoDetail(result);
                        String title = mVideoBean.getTitle();
                        String m3u8_url = mVideoBean.getM3u8_url();
                        String m3u8Hd_url = mVideoBean.getM3u8Hd_url();
                        mMp4_url = mVideoBean.getMp4_url();
                        String mp4Hd_url = mVideoBean.getMp4Hd_url();
                        String ptime = mVideoBean.getPtime();
                        String vid = mVideoBean.getVid();
                        String videosource = mVideoBean.getVideosource();
                        LogUtils.d(TAG, "requestData: 视频地址为：" + mMp4_url);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!TextUtils.isEmpty(mMp4_url)){
                                    showNewsPage();
                                    bindData();
                                } else {
                                    showEmptyPage();
                                }

                            }
                        });
                    }

                    @Override
                    public void onError( Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showErroPage();
                            }
                        });
                    }
                });


            }
        });
    }

    @Override
    public void initListener() {
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //开始缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        percentTv.setVisibility(View.VISIBLE);
                        netSpeedTv.setVisibility(View.VISIBLE);
                        mp.pause();
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        percentTv.setVisibility(View.GONE);
                        netSpeedTv.setVisibility(View.GONE);
                        mp.start();
                        break;
                    //正在缓冲
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        netSpeedTv.setText("当前网速:" + extra + "kb/s");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void bindData() {
        if (Vitamio.isInitialized(this)) {

            // 测试Url：  http://flv2.bn.netease.com/tvmrepo/2017/1/4/V/EC8TVS34V/SD/EC8TVS34V-mobile.mp4
            mVideoView.setVideoURI(Uri.parse(mMp4_url));
            MediaController controller = new MediaController(this);
            mVideoView.setMediaController(controller);
            mVideoView.start();

            mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    percentTv.setText("已缓冲：" + percent + "%");
                }
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 如果有新闻就展示新闻页面
     */
    private void showNewsPage() {
        mRl_video.setVisibility(View.VISIBLE);
        mLoadingPage.setSuccessView();
    }

    /**
     * 展示加载页面
     */
    private void showLoadingPage() {
        mRl_video.setVisibility(View.INVISIBLE);
        mLoadingPage.setLoadingView();
        //
    }

    /**
     * 如果没有网络就展示空消息页面
     */
    private void showEmptyPage() {
        mRl_video.setVisibility(View.INVISIBLE);
        mLoadingPage.setEmptyView();
    }

    private void showErroPage() {
        mRl_video.setVisibility(View.INVISIBLE);
        mLoadingPage.setErrorView();
        mLoadingPage.setLoadingClickListener(new LoadingPage.LoadingClickListener() {
            @Override
            public void clickListener() {
                requestData();
            }
        });
    }
}
