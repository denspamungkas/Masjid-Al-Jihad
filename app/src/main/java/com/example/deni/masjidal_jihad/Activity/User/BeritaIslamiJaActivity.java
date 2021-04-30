package com.example.deni.masjidal_jihad.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.deni.masjidal_jihad.R;

public class BeritaIslamiJaActivity extends AppCompatActivity {

    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_islami_ja);

        this.setTitle("Berita Islami");
        webview = findViewById(R.id.wv_berita);

        webview.loadUrl("https://muslim.or.id/");
        webview.setWebViewClient(new WebViewClient());
    }
}