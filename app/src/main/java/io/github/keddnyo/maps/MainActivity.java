package io.github.keddnyo.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.github.keddnyo.maps.common.Constants;

public class MainActivity extends Activity {

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web_view);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            settings.setUserAgentString(Constants.USER_AGENT);
        }

        Uri data = getIntent().getData();

        if (data != null) {
            if (data.getScheme().equals("geo")) {
                String geo = data.getSchemeSpecificPart();

                if (geo.contains("?q=")) {
                    geo = geo.substring(geo.indexOf("?q=") + 3);
                }

                webView.loadUrl(Constants.YANDEX_MAPS_TEMPLATE_URL + geo);
            } else {
                webView.loadUrl(data.toString());
            }

        } else {
            webView.loadUrl(Constants.YANDEX_MAPS_URL);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}