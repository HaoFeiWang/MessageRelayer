package com.whf.messagerelayer.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.utils.NativeDataManager;

public class SmsRelayerActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    private Switch mSmsSwitch,mSmsProxySwitch;
    private RelativeLayout mMobileRelative;
    private View mMobileLine;
    private TextView mMobileText;

    private NativeDataManager mNativeDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_relayer);

        init();
    }

    private void init(){
        mNativeDataManager = new NativeDataManager(this);

        initView();
        initData();
        initListener();

    }

    private void initView(){
        mSmsSwitch = (Switch) findViewById(R.id.switch_sms);
        mSmsProxySwitch = (Switch) findViewById(R.id.switch_sms_proxy);

        mMobileLine = findViewById(R.id.line_mobile);
        mMobileRelative = (RelativeLayout) findViewById(R.id.layout_mobile);
        mMobileText = (TextView) findViewById(R.id.text_mobile);
    }

    private void initData(){
        if(mNativeDataManager.getSmsRelay()){
            mSmsSwitch.setChecked(true);
            mMobileLine.setVisibility(View.VISIBLE);
            mMobileRelative.setVisibility(View.VISIBLE);
            mMobileText.setText(mNativeDataManager.getObjectMobile());
        }else{
            mSmsSwitch.setChecked(false);
            mMobileLine.setVisibility(View.GONE);
            mMobileRelative.setVisibility(View.GONE);
        }
    }

    private void initListener(){
        mSmsSwitch.setOnCheckedChangeListener(this);
        mSmsProxySwitch.setOnCheckedChangeListener(this);

        mMobileRelative.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_sms:
                smsChecked(isChecked);
                break;
            case R.id.switch_sms_proxy:
                smsProxyChecked();
                break;
        }
    }

    private void smsProxyChecked() {

    }

    /**
     * 使用短信转发至指定手机号的Switch的事件方法
     * @param isChecked
     */
    private void smsChecked(boolean isChecked) {
        if(isChecked){
            mNativeDataManager.setSmsRelay(true);
            mMobileLine.setVisibility(View.VISIBLE);
            mMobileRelative.setVisibility(View.VISIBLE);
        }else{
            mNativeDataManager.setSmsRelay(false);
            mMobileLine.setVisibility(View.GONE);
            mMobileRelative.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_mobile:
                showEditDialog();
                break;
        }
    }

    private void showEditDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit,null,false);
        final EditText mobileEdit = (EditText) view.findViewById(R.id.dialog_edit);
        mobileEdit.setText(mMobileText.getText());
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setObjectMobile(mobileEdit.getText().toString());
                mMobileText.setText(mobileEdit.getText());
            }
        });
        builder.show();
    }
}
