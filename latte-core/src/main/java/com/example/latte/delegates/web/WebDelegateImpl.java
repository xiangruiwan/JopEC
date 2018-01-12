package com.example.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte.delegates.web.route.RouteKeys;

/**
 * Created by Administrator on 2018/1/8.
 */

public class WebDelegateImpl extends WebDelegate {
//    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }
    @Override
    public IWebViewInitializer setInitializer() {
        return null;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public WebView initWebView(WebView webView) {
        return null;
    }

    @Override
    public WebViewClient initWebViewClient() {
        return null;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return null;
    }
}
