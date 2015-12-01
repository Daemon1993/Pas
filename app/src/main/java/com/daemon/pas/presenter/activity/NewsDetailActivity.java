package com.daemon.pas.presenter.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.ui.activity.NewsDetailView;

import java.lang.reflect.Field;

import de.greenrobot.event.EventBus;

public class NewsDetailActivity extends ActivityPresenter<NewsDetailView> implements View.OnClickListener {


    public static final String URL = "url";

    private WebView webView;
    private boolean blockLoadingNetworkImage;
    private String url;

    @Override
    public Class<NewsDetailView> getIViewClass() {
        return NewsDetailView.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();


        setSupportActionBar(iView.toolbar);
        iView.toolbar.setNavigationOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWebView();
    }

    private void initWebView() {

        url = getIntent().getStringExtra(URL);

        webView = new WebView(getApplicationContext());

        iView.rlMain.addView(webView);


        blockLoadingNetworkImage = true;

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setBlockNetworkImage(true);

        webView.setWebViewClient(new webViewClient());

        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        webView.setWebChromeClient(
                new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        if (newProgress == 100) {
                            iView.flLoading.setVisibility(View.GONE);

                            if (blockLoadingNetworkImage) {
                                webView.getSettings().setBlockNetworkImage(false);
                                blockLoadingNetworkImage = false;
                            }
                        }
                    }
                });

        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    class webViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            iView.flLoading.setVisibility(View.VISIBLE);

            //说明不是自己的 或者说 不需要webview去加载的 就自己应用来处理
            return false;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onDestroy() {

        // EventBus.getDefault().unregister(this);
        if (iView.rlMain != null) {
            iView.rlMain.removeAllViews();
        }

        setConfigCallback(null);

        super.onDestroy();

        if (webView != null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
        }

        EventBus.getDefault().unregister(this);
    }

    public void setConfigCallback(WindowManager windowManager) {
        try {
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field = field.getType().getDeclaredField("mBrowserFrame");
            field = field.getType().getDeclaredField("sConfigCallback");
            field.setAccessible(true);
            Object configCallback = field.get(null);
            if (null == configCallback) {
                return;
            }
            field = field.getType().getDeclaredField("mWindowManager");
            field.setAccessible(true);
            field.set(configCallback, windowManager);
        } catch (Exception e) {
        }
    }

}
