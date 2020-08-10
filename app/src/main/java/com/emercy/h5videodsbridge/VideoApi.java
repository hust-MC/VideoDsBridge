package com.emercy.h5videodsbridge;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;

public class VideoApi {
    private Context mContext;
    VideoPlayerView mVideoView;


    public VideoApi(Context context) {
        this.mContext = context;
        mVideoView = new VideoPlayerView(context);
    }

    @JavascriptInterface
    public String start(Object msg) {
        final Activity activity = (Activity) mContext;
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                FrameLayout contentView = activity.findViewById(R.id.video_root);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(1080, 607);

                contentView.addView(mVideoView, params);
                mVideoView.play();
            }
        });

        return "start success";
    }

    @JavascriptInterface
    public String pause(Object msg) {
        mVideoView.pause();
        return "pause success";
    }

    @JavascriptInterface
    public String resume(Object msg) {
        mVideoView.start();
        return "resume success";
    }

    @JavascriptInterface
    public String seek(Object msg) {
        int second = Integer.valueOf((String) msg);
        mVideoView.seekTo(mVideoView.getCurrentPosition() + second * 1000);
        return "pause success";
    }
}