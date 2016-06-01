package com.example.bcsd.callvan.Activiy;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bcsd.callvan.R;

/**
 * Created by EUNBEE on 2016-05-25.
 */
public class RegisterActivity extends Activity{

    WebView webView;
    //Proxy proxy;
    String jsonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_lin);

        webView = (WebView)findViewById(R.id.register_wv);
        webView.setWebViewClient(new WebViewClient());
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webView.loadUrl("http://a.hanpyo.com:3000/#/register");
        webView.setWebViewClient(new WebViewClientClass());

        //proxy = new Proxy();
        //jsonData = proxy.getJSON();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

}
