package com.eglisemr.ui.donation;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eglisemr.R;
import com.eglisemr.model.Resource;

public class DonationFragment extends Fragment {

    private static final String TAG = DonationFragment.class.getName();
    private DonationViewModel donationViewModel;
    private WebView webView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        donationViewModel =
                new ViewModelProvider(this).get(DonationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_donation, container, false);
        webView = root.findViewById(R.id.webview);
        progressBar = root.findViewById(R.id.progress_bar);
        mySwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);

        progressBar.setVisibility(View.VISIBLE);

        donationViewModel.getResource().observe(getViewLifecycleOwner(), new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {

                initWebView(resource);
                startWebView();
            }
        });
        return root;
    }

    private void initWebView(Resource resource) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        //webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSupportZoom(true);

        webView.loadUrl(resource.getFeedUrl());

        progressBar.setVisibility(View.GONE);
        mySwipeRefreshLayout.setRefreshing(true);

        mySwipeRefreshLayout.setOnRefreshListener(
                () -> {
                    Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                    mySwipeRefreshLayout.setRefreshing(true);
                    // This method performs the actual data-refresh operation.
                    // The method calls setRefreshing(false) when it's finished.
                    webView.loadUrl(resource.getFeedUrl());
                }
        );

    }

    private void startWebView() {

        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mySwipeRefreshLayout.setRefreshing(false);
                webView.loadData("Verify your connection and refresh!", MimeTypeMap.getFileExtensionFromUrl(""), "UTF-8");
            }

            public void onProgressChanged(WebView view, int progress) {

            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                //progressBar.setVisibility(View.VISIBLE);
            }

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                mySwipeRefreshLayout.setRefreshing(false);
            }

        });

    }

}