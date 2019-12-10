package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class wView extends AppCompatActivity {
    private String url;



    private final static String CHANNEL_ID= "NOFITICATION";
    private final static int NOFITICATION_id = 0;

    String direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba);

        final Bundle bundle = getIntent().getExtras();
        direccion = bundle.getString("link");
        url=direccion;


        WebView webView = (WebView)findViewById(R.id.visorWeb);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
