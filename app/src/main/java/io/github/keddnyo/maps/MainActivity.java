package io.github.keddnyo.maps;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
        settings.setJavaScriptEnabled(true);

        Uri data = getIntent().getData();

        if (data != null) {
            String inputData = data.getSchemeSpecificPart();
            String geoReplace;

            if (inputData.contains("?")) {
                geoReplace = inputData.substring(inputData.indexOf(":") + 1, inputData.indexOf("?") - 1);
            } else {
                geoReplace = inputData.substring(inputData.indexOf(":") + 1);
            }

            String geo = geoReplace.replace("%2C", ",");

            webView.loadUrl("https://yandex.ru/maps?text=" + geo);
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