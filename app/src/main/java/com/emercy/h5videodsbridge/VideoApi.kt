package com.emercy.h5videodsbridge

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.widget.FrameLayout
import android.widget.VideoView

class VideoApi(private val mContext: Context) {

    @JavascriptInterface
    fun start(msg: Any): String? {
        val activity = mContext as Activity
        activity.runOnUiThread {
            val contentView = activity.findViewById<FrameLayout>(R.id.video_root)

            val videoView = VideoPlayerView(mContext)
            contentView.addView(videoView)
            videoView.start()
        }

        return "$msg［syn call］http://1258344700.vod2.myqcloud.com/f271450cvodtranscq1258344700/337a929d5285890801971869792/v.f220.m3u8"
    }
}
