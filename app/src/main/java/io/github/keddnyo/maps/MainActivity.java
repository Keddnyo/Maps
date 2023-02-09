package io.github.keddnyo.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

        Uri data = getIntent().getData();

        if (data != null) {
            if (data.getScheme().equals("geo")) {
                String geo = data.getSchemeSpecificPart();

                if (geo.contains("?q=")) {
                    geo = geo.substring(geo.indexOf("?q=") + 3);
                }

                webView.loadUrl("https://yandex.ru/maps?text=" + geo);
            } else {
                webView.loadUrl(data.toString());
            }

        } else {
            webView.loadUrl("https://maps.ya.ru");
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