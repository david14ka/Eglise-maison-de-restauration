package com.eglisemr.ui.message;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eglisemr.R;
import com.eglisemr.model.Resource;

public class VideoActivity extends AppCompatActivity {

    private WebView myWebView;
    private FrameLayout layoutError;
    private Resource mResource;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mResource = new Resource();
        //mResource.setTitle(getIntent().getStringExtra("title"));
        mResource.setFeedUrl(getIntent().getStringExtra("url"));

        layoutError = findViewById(R.id.layout_error);
        progressBar = findViewById(R.id.progress_bar);
        layoutError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        initWebView();

    }

    private void initWebView() {

        myWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(mResource.getFeedUrl()+"?autoplay=1");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //!!progressBar.setVisibility(View.VISIBLE);

            if ("www.maisondelarestauration.com".equals(Uri.parse(url).getHost())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);*/
            return false;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            //super.onReceivedError(view, request, error);


        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}