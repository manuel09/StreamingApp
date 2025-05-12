package com.example.streamingapp;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Trova la WebView nel layout
        webView = findViewById(R.id.webView);
        
        // Impostazioni WebView
        webView.getSettings().setJavaScriptEnabled(true);  // Abilita JavaScript
        webView.getSettings().setDomStorageEnabled(true);  // Abilita il DOM Storage (per il salvataggio dei dati)

        // Bloccare la pubblicità (URL filtrato)
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                // Blocca URL che contengono "ad" o "advertisement"
                if (url.contains("ad") || url.contains("advertisement")) {
                    return true;  // Blocca la pubblicità
                }

                view.loadUrl(url);  // Carica altre pagine normalmente
                return false;
            }
        });

        // Carica il sito di streaming
        webView.loadUrl("https://streamingunity.to/it");  // Sostituisci con il sito vero
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();  // Torna indietro nella cronologia della WebView
        } else {
            super.onBackPressed();  // Se non c'è cronologia, esci dall'app
        }
    }
}
