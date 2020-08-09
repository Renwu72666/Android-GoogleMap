package com.example.googlemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    boolean webviewReady =false;
    private EditText LatitudeText, LongtitudeText;
    private Button findmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        LatitudeText = (EditText) findViewById(R.id.LatitudeText);
        LongtitudeText = (EditText) findViewById(R.id.LongtitudeText);
        findmap = (Button) findViewById(R.id.findmap);
        setWebView();
        findmap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String centerURL = "javascript:centerAt(" +
                        LatitudeText.getText() + "," +
                        LongtitudeText.getText() + ")";
                webView.loadUrl(centerURL);
                if (webviewReady) webView.loadUrl(centerURL);
            }
        });
    }
    private  void  setWebView(){
        webView =(WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webviewReady = true;
            }
        });
        webView.loadUrl("file:///android_asset/googlemapCenter.html");
    }
}
