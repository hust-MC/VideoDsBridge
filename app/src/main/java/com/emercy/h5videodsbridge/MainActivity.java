package com.emercy.h5videodsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import wendu.dsbridge.DWebView;

public class MainActivity extends Activity {

    private DWebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        DWebView.setWebContentsDebuggingEnabled(true);
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