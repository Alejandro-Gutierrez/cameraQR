package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Page extends AppCompatActivity {

    private String url;



    private final static String CHANNEL_ID= "NOFITICATION";
    private final static int NOFITICATION_id = 0;

    String direccion;

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
