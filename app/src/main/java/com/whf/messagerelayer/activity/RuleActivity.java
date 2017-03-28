package com.whf.messagerelayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.whf.messagerelayer.R;

public class RuleActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout mMoblieRuleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        initView();
        initListener();
    }

    private void initView(){
        mMoblieRuleLayout = (RelativeLayout) findViewById(R.id.layout_rule_mobile);
    }

    private void initListener(){
        mMoblieRuleLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_rule_mobile:
                startActivity(new Intent(this,SelectedContactActivity.class));
                break;
        }
    }
}
