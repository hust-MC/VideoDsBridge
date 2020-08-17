package com.emercy.h5videodsbridge;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoApi {
    private Context mContext;
    private static VideoPlayerView mVideoView;

    private boolean mHasAttached;

    public VideoApi(Context context) {
        this.mContext = context;
        mVideoView = new VideoPlayerView(context);
    }

    @JavascriptInterface
    public String start(Object msg) {
        if (mHasAttached) {
            return "has Attached";
        }

        String url = "";
        int width = 0;
        int height = 0;

        try {
            JSONObject videoInfo = new JSONObject((String) msg);
            url = videoInfo.getString("url");
            width = videoInfo.getInt("width");
            height = videoInfo.getInt("height");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Activity activity = (Activity) mContext;
        final String finalUrl = url;
        final int finalWidth = width;
        final int finalHeight = height;
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                FrameLayout contentView = activity.findViewById(R.id.video_root);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(1080, 607);

                contentView.addView(mVideoView, params);
                mHasAttached = true;
                mVideoView.play(finalUrl);
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
        int second = Integer.parseInt((String) msg);
        mVideoView.seekTo(mVideoView.getCurrentPosition() + second * 1000);
        return "pause success";
    }

    @JavascriptInterface
    public String fullScreen(Object msg) {
        mVideoView.fullScreen();
        return "full screen";
    }

    @JavascriptInterface
    public String exitFullScreen(Object msg) {
        mVideoView.exitFullScreen();
        return "exit full screen";
    }

    public static boolean onBackPressed() {
        if (mVideoView != null) {
            return mVideoView.onBack();
        }
        return false;
    }
}