package com.superp.superppdv_consulta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity
{
    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        setContentView(R.layout.activity_fullscreen);

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        getApplicationContext().registerReceiver(networkChangeReceiver, intentFilter);

        WebView webview = findViewById(R.id.webview);
        ProgressBar progressBar = findViewById(R.id.progressBarEmpresa);


        SharedPreferences sharedPreferences = getSharedPreferences("Productive", MODE_PRIVATE);

        String ip = sharedPreferences.getString("ip", "");
        String porta = sharedPreferences.getString("porta","");
        String caminho = "http://" +         ip + ":" + porta + "/m";
        Log.e("caminho", caminho);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl(caminho);
        progressBar.setVisibility(View.VISIBLE);

        webview.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onPageFinished(WebView view, String url)
                                     {
                                         super.onPageFinished(view, url);
                                         progressBar.setVisibility(View.INVISIBLE);
                                     }
                                 }
        );
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        if(intent.getBooleanExtra("close_activity",false))
        {
            this.finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        getApplicationContext().unregisterReceiver(networkChangeReceiver);
    }

}