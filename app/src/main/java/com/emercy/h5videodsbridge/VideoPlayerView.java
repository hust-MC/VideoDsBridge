package com.emercy.h5videodsbridge;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class VideoPlayerView extends FrameLayout {

    VideoView mVideoView;
    public VideoPlayerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mVideoView = new VideoView(getContext());

        addView(mVideoView);

        mVideoView.post(new Runnable() {
            @Override
            public void run() {
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;

                Button button = new Button(getContext());
                button.setBackgroundResource(R.mipmap.video_play);
                addView(button, params);
            }
        });

    }

    public void start() {

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e("MCLOG", "prepare");
                mVideoView.bringToFront();
            }
        });
        mVideoView.setVideoPath("https://vd2.bdstatic.com/mda-kftgk8ir8x4qfp70/v1-cae/hd/mda-kftgk8ir8x4qfp70.mp4?vt=1&cd=0&did=4407a15cc94bb224d11dd70901455c6e&logid=0722453597&vid=3922470302414820306&pd=0&pt=0&av=11.22.0.0&cr=1&sle=1&sl=221&split=484492&auth_key=1596877922-0-0-0d1719da1bc49b9845e2b4eea0fc6a70&bcevod_channel=searchbox_feed&pdx=0&nt=0&dt=1");

        mVideoView.start();
    }
}
