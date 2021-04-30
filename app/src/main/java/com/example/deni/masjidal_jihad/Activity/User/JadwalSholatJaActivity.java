package com.example.deni.masjidal_jihad.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.deni.masjidal_jihad.R;

public class JadwalSholatJaActivity extends AppCompatActivity {


    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat_ja);

        this.setTitle("Jadwal Sholat");
        webView = findViewById(R.id.wv_jadwal_sholat);


        webView.loadUrl("https://umroh.com/jadwal-sholat/tangerang");
        webView.setWebViewClient(new WebViewClient());
    }
}