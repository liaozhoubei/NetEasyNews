package cn.bproject.neteasynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.bproject.neteasynews.bean.VideoBean;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by liaozhoubei on 2017/1/3.
 */

public class VideoDetailActivity extends AppCompatActivity {
    private TextView percentTv;
    private TextView netSpeedTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        Intent intent = getIntent();
        VideoBean videoBean = intent.getParcelableExtra("VIDEO");
        Log.d("VideoDetailActivity", "onCreate: " + videoBean);

        String title = videoBean.getTitle();
        String m3u8_url =  videoBean.getM3u8_url();
        String m3u8Hd_url =  videoBean.getM3u8Hd_url();
        String mp4_url = videoBean.getMp4_url();
        String mp4Hd_url = videoBean.getMp4Hd_url();
        String ptime =  videoBean.getPtime();
        String vid =  videoBean.getVid();
        String videosource = videoBean.getVideosource();

        // 显示缓冲百分比的TextView
        percentTv = (TextView) findViewById(R.id.buffer_percent);
        //显示下载网速的TextView
        netSpeedTv = (TextView) findViewById(R.id.net_speed);
        if (Vitamio.isInitialized(this)) {
            VideoView videoView = (VideoView) findViewById(R.id.vitamio);
            // 测试Url：   "http://baobab.wdjcdn.com/145076769089714.mp4"
            videoView.setVideoPath(mp4_url);
//            videoView.setVideoURI(Uri.parse("https://flv.bn.netease.com/videolib3/1701/01/bJeLj1751/SD/bJeLj1751-mobile.mp4"));
            MediaController controller = new MediaController(this);
            videoView.setMediaController(controller);
            videoView.start();

            videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    percentTv.setText("已缓冲：" + percent + "%");
                }
            });
            videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
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
    }

}
