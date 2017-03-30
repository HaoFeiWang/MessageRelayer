package com.whf.messagerelayer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.whf.messagerelayer.R;

public class AboutActivity extends AppCompatActivity {


    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void showWebView(){

    }
}
