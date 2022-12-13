package com.example.andoirdduan.DocBao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.andoirdduan.R;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webviewTinTuc);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTinTuc");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

    }
}