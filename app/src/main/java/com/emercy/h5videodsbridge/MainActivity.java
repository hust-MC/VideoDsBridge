package com.emercy.h5videodsbridge;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;

import wendu.dsbridge.DWebView;

public class MainActivity extends Activity {

    /**
     * dsBridge实例
     */
    private DWebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 开启inspect调试
        DWebView.setWebContentsDebuggingEnabled(true);

        // 初始化DSWebView，并load Hybrid页面，注入JsInterface API
        mWebView = findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/video.html");
        mWebView.addJavascriptObject(new VideoApi(this), null);
    }

    @Override
    public void onBackPressed() {
        if (!VideoApi.onBackPressed()) {
            super.onBackPressed();
        }
    }
}