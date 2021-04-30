package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KiblatJaActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiblat_ja);

        this.setTitle("Kiblat");
        webview = findViewById(R.id.wv_kiblat);


        webview.loadUrl("https://www.al-habib.info/arah-kiblat/");
        webview.setWebViewClient(new WebViewClient());
    }
}