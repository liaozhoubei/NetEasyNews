package cn.bproject.neteasynews.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class VideoDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = VideoFragment.class.getSimpleName();

    private final String VID = "VID";
    private final String MP4URL = "MP4URL";
    private String vid;

    private Context mContext;

    private TextView percentTv;
    private TextView netSpeedTv;
    private VideoView mVideoView;

    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private VideoBean mVideoBean;
    private String mMp4_url;
    private RelativeLayout mRl_video;
    private LoadingPage mLoadingPage;
    private ImageView video_cover;

    private boolean isShowVideo = true; // 判断是否播放视频

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            vid = intent.getStringExtra(VID);
            mMp4_url = intent.getStringExtra(MP4URL);
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

        video_cover = (ImageView) findViewById(R.id.video_cover);

        mLoadingPage = (LoadingPage) findViewById(R.id.loading_page);

        video_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVideoDialog();
            }
        });

    }

    @Override
    public void initValidata() {
        // 创建线程池
        mThreadPool = ThreadManager.getThreadPool();
        if (NetWorkUtil.isWifiConnected(this)) {
            isShowVideo = true;
            showLoadingPage();
//            requestData();
            showVideoPage();
        } else {
            showVideoDialog();
        }
    }

    /**
     * 弹出清除缓存对话框
     */
    public void showVideoDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(R.string.show_video_without_wifi)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isShowVideo = true;

                        showLoadingPage();
//                        requestData();
                        showVideoPage();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isShowVideo = false;

                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

    public void requestData() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
////                http://c.m.163.com/nc/video/detail/VC8TVUN5N.html
                String url = Api.videoDetailUrl + vid + Api.EndUrlVideoDetailUrl;
                Log.i(TAG, "run: video url = " + url);
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mVideoBean = DataParse.VideoDetail(result);
                        String cover_url = mVideoBean.getCover();
                        mMp4_url = mVideoBean.getMp4_url();
                        LogUtils.d(TAG, "requestData: 视频地址为：" + mMp4_url);
                        showVideoPage();
                    }

                    @Override
                    public void onError(Exception e) {
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

    /**
     * 判断是否要加载视频内容
     */
    public void showVideoPage(){
        if (isShowVideo) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    video_cover.setVisibility(View.INVISIBLE);
                    mVideoView.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(mMp4_url)) {
                        showNewsPage();
                        bindData();
                    } else {
                        showEmptyPage();
                    }
                }
            });
        } else {
            video_cover.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
        }
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
                        percentTv.setVisibility(View.INVISIBLE);
                        netSpeedTv.setVisibility(View.INVISIBLE);
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
        // 初始化Vitamio
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
