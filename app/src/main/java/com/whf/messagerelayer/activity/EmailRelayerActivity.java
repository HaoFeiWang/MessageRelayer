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
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.NativeDataManager;

import org.w3c.dom.Text;

public class EmailRelayerActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    private Switch mEmailSwitch,mSslSwitch;
    private RelativeLayout mLayoutAccount,mLayoutServicer,mLayoutAddress,mLayoutPort;
    private TextView mTextAccount,mTextServicer;

    private NativeDataManager mNativeDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_relayer);

        this.mNativeDataManager = new NativeDataManager(this);
        initView();
        initListener();
    }

    private void initView(){
        mEmailSwitch = (Switch) findViewById(R.id.switch_email);
        mSslSwitch = (Switch) findViewById(R.id.switch_ssl);

        mLayoutServicer = (RelativeLayout) findViewById(R.id.layout_servicer);
        mLayoutAccount = (RelativeLayout) findViewById(R.id.layout_account);
        mLayoutAddress = (RelativeLayout) findViewById(R.id.layout_address);
        mLayoutPort = (RelativeLayout) findViewById(R.id.layout_port);

        mTextServicer = (TextView) findViewById(R.id.text_servicer);
        mTextAccount = (TextView) findViewById(R.id.text_account);
    }

    private void initListener(){
        mLayoutServicer.setOnClickListener(this);
        mLayoutAccount.setOnClickListener(this);
        mLayoutAddress.setOnClickListener(this);
        mLayoutPort.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_servicer:
                showServicerDialog();
                break;
            case R.id.layout_account:
                showAccountDialog();
                break;
            case R.id.layout_address:
                showAddressDialog();
                break;
            case R.id.layout_port:
                showPortDialog();
                break;
        }
    }

    private void showServicerDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_email_servicer,null,false);
        builder.setView(view);
        final AlertDialog dialog = builder.show();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.text_126_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_126);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_126);
                        dialog.cancel();
                        break;
                    case R.id.text_163_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_163);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_163);
                        dialog.cancel();
                        break;
                    case R.id.text_gmail_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_GMAIL);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_GMAIL);
                        dialog.cancel();
                        break;
                    case R.id.text_other_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OTHER);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_OTHER);
                        dialog.cancel();
                        break;
                    case R.id.text_qq_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_QQ);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_QQ);
                        dialog.cancel();
                        break;
                    case R.id.text_outlook_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OUTLOOK);
                        mTextServicer.setText(Constant.EMAIL_SERVICER_OUTLOOK);
                        dialog.cancel();
                        break;
                }
            }
        };
        initServicerDialogView(onClickListener,view);

    }

    private View initServicerDialogView(View.OnClickListener onClickListener,View view){
        TextView emailQq = (TextView) view.findViewById(R.id.text_qq_email);
        TextView emaill26 = (TextView) view.findViewById(R.id.text_126_email);
        TextView email163 = (TextView) view.findViewById(R.id.text_163_email);
        TextView emailOutlook = (TextView) view.findViewById(R.id.text_outlook_email);
        TextView emailGmail = (TextView) view.findViewById(R.id.text_gmail_email);
        TextView emailOther = (TextView) view.findViewById(R.id.text_other_email);

        email163.setOnClickListener(onClickListener);
        emailGmail.setOnClickListener(onClickListener);
        emaill26.setOnClickListener(onClickListener);
        emailOther.setOnClickListener(onClickListener);
        emailOutlook.setOnClickListener(onClickListener);
        emailQq.setOnClickListener(onClickListener);
        return view;
    }

    private void showAccountDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_email_account,null,false);
        builder.setView(view);

        final EditText textAccount = (EditText) view.findViewById(R.id.editText_account);
        final EditText textPassword = (EditText) view.findViewById(R.id.editText_password);

        textAccount.setText(mNativeDataManager.getEmailAccount());
        textPassword.setText(mNativeDataManager.getEmailPassword());

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailAccount(textAccount.getText().toString());
                mNativeDataManager.setEmailPassword(textPassword.getText().toString());
                mTextAccount.setText(textAccount.getText());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    private void showAddressDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit,null,false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP服务器地址");
        editText.setText(mNativeDataManager.getEmailAddress());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void showPortDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit,null,false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP端口号");
        editText.setText(mNativeDataManager.getEmailAddress());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
