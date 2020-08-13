package com.emercy.h5videodsbridge;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;

public class VideoPlayerView extends FrameLayout {

    VideoView mVideoView;
    private static boolean mIsFull;

    public VideoPlayerView(Context context) {
        super(context);
    }

    public void play() {
        mVideoView = new VideoView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mVideoView, params);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e("MCLOG", "prepare");
            }
        });

        MediaController controller = new MediaController(getContext());
        mVideoView.setMediaController(controller);
        controller.setAnchorView(mVideoView); // I get Null piont exception here

        mVideoView.setVideoPath("https://vd2.bdstatic.com/mda-kftgk8ir8x4qfp70/v1-cae/hd/mda-kftgk8ir8x4qfp70.mp4?vt=1&cd=0&did=4407a15cc94bb224d11dd70901455c6e&logid=0722453597&vid=3922470302414820306&pd=0&pt=0&av=11.22.0.0&cr=1&sle=1&sl=221&split=484492&auth_key=1596877922-0-0-0d1719da1bc49b9845e2b4eea0fc6a70&bcevod_channel=searchbox_feed&pdx=0&nt=0&dt=1");

        mVideoView.start();
    }

    public void pause() {
        mVideoView.pause();
    }

    public void seekTo(int second) {
        mVideoView.seekTo(second);
    }

    public void start() {
        mVideoView.start();
    }

    public int getCurrentPosition() {
        return mVideoView.getCurrentPosition();
    }

    private int lastContainerWidth, lastContainerHeight;

    public void fullScreen() {
        final Activity activity = (Activity) getContext();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

                ViewGroup.LayoutParams param = getLayoutParams();
                lastContainerWidth = param.width;
                lastContainerHeight = param.height;
                param.width = ViewGroup.LayoutParams.MATCH_PARENT;
                param.height = ViewGroup.LayoutParams.MATCH_PARENT;
                setLayoutParams(param);
                mIsFull = true;
            }
        });
    }

    public void exitFullScreen() {
        final Activity activity = (Activity) getContext();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ViewGroup.LayoutParams param = getLayoutParams();
                param.width = lastContainerWidth;
                param.height = lastContainerHeight;
                setLayoutParams(param);
                mIsFull = false;
            }
        });
    }

    public boolean onBack() {
        if (mIsFull) {
            exitFullScreen();
            return true;
        }
        return false;
    }
}
