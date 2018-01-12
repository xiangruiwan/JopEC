package com.example.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte.delegates.web.WebDelegate;
import com.example.latte.delegates.web.route.Router;
import com.example.latte.ui.loader.LatteLoader;
import com.example.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2018/1/8.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
//    private IPageLoadListener mIPageLoadListener = null;
//    private static final Handler HANDLER = Latte.getHandler();
//
//    public void setPageLoadListener(IPageLoadListener listener) {
//        this.mIPageLoadListener = listener;
//    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
//        if (mIPageLoadListener != null) {
//            mIPageLoadListener.onLoadStart();
//        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
//        if (mIPageLoadListener != null) {
//            mIPageLoadListener.onLoadEnd();
//        }

    }
}
