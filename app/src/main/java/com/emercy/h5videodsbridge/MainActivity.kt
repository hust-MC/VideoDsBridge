package com.emercy.h5videodsbridge

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import wendu.dsbridge.DWebView

class MainActivity : AppCompatActivity() {

    private lateinit var mWebView: DWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DWebView.setWebContentsDebuggingEnabled(true)
        mWebView = findViewById(R.id.webview)
        mWebView.loadUrl("file:///android_asset/video.html")
        mWebView.addJavascriptObject(VideoApi(this), null)
    }
}