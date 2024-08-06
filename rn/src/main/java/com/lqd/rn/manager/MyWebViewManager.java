package com.lqd.rn.manager;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class MyWebViewManager extends SimpleViewManager<WebView> {

    private ReactApplicationContext context;

    public MyWebViewManager(ReactApplicationContext context) {
        this.context = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "WebView";
    }

    @NonNull
    @Override
    protected WebView createViewInstance(@NonNull ThemedReactContext reactContext) {
        WebView webView = new WebView(reactContext);
        webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return webView;
    }

    @ReactProp(name = "url")
    public void setUrl(WebView webView,String url){
        Log.e("WebView","url is "+url);
        initWebViewSetting(webView);
        webView.loadUrl(url);
    }

    private void initWebViewSetting(WebView webView){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });
    }
}
