package com.whf.messagerelayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.whf.messagerelayer.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout mSmsLayout,mEmailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        mSmsLayout = (RelativeLayout) findViewById(R.id.sms_relay_layout);
        mEmailLayout = (RelativeLayout) findViewById(R.id.email_relay_layout);

        mSmsLayout.setOnClickListener(this);
        mEmailLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sms_relay_layout:
                startActivity(new Intent(this,SmsRelayerActivity.class));
                break;
            case R.id.email_relay_layout:
                startActivity(new Intent(this,EmailRelayerActivity.class));
                break;
        }
    }
}
