package com.whf.messagerelayer.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.bean.EmailMessage;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.EmailManager;
import com.whf.messagerelayer.utils.NativeDataManager;

public class EmailRelayerActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    private Switch mEmailSwitch,mSslSwitch;
    private RelativeLayout mLayoutAccount,mLayoutServicer,mLayoutAddress,mLayoutPort,mLayoutToAccount;
    private TextView mTextAccount,mTextServicer,mTextAddress,mTextPort,mTextToAccount;
    private View mAddressLine,mPortLine;

    private NativeDataManager mNativeDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_relayer);

        this.mNativeDataManager = new NativeDataManager(this);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        String servecer = mNativeDataManager.getEmailServicer();
        if(servecer.equals(Constant.EMAIL_SERVICER_OTHER)){
            mLayoutAddress.setVisibility(View.VISIBLE);
            mLayoutPort.setVisibility(View.VISIBLE);
            mAddressLine.setVisibility(View.VISIBLE);
            mPortLine.setVisibility(View.VISIBLE);
            mTextAddress.setText(mNativeDataManager.getEmailHost());
            mTextPort.setText(mNativeDataManager.getEmailPort());
        }else{
            mLayoutAddress.setVisibility(View.GONE);
            mLayoutPort.setVisibility(View.GONE);
            mAddressLine.setVisibility(View.GONE);
            mPortLine.setVisibility(View.GONE);
        }
        mEmailSwitch.setChecked(mNativeDataManager.getEmailRelay());
        mSslSwitch.setChecked(mNativeDataManager.getEmailSsl());
        mTextServicer.setText(servecer);
        mTextAccount.setText(mNativeDataManager.getEmailAccount());
        mTextToAccount.setText(mNativeDataManager.getEmailToAccount());

    }

    private void initView(){
        mEmailSwitch = (Switch) findViewById(R.id.switch_email);
        mSslSwitch = (Switch) findViewById(R.id.switch_ssl);

        mLayoutServicer = (RelativeLayout) findViewById(R.id.layout_servicer);
        mLayoutAccount = (RelativeLayout) findViewById(R.id.layout_account);
        mLayoutAddress = (RelativeLayout) findViewById(R.id.layout_address);
        mLayoutPort = (RelativeLayout) findViewById(R.id.layout_port);
        mLayoutToAccount = (RelativeLayout) findViewById(R.id.layout_to_account);

        mTextServicer = (TextView) findViewById(R.id.text_servicer);
        mTextAccount = (TextView) findViewById(R.id.text_account);
        mTextAddress = (TextView) findViewById(R.id.text_address);
        mTextPort = (TextView) findViewById(R.id.text_port);
        mTextToAccount = (TextView) findViewById(R.id.text_to_account);

        mAddressLine = findViewById(R.id.line_address);
        mPortLine = findViewById(R.id.line_port);
    }

    private void initListener(){
        mLayoutServicer.setOnClickListener(this);
        mLayoutAccount.setOnClickListener(this);
        mLayoutAddress.setOnClickListener(this);
        mLayoutPort.setOnClickListener(this);
        mLayoutToAccount.setOnClickListener(this);

        mEmailSwitch.setOnCheckedChangeListener(this);
        mSslSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_email:
                mNativeDataManager.setEmailRelay(isChecked);
                break;
            case R.id.switch_ssl:
                mNativeDataManager.setEmailSsl(isChecked);
                break;
        }
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
            case R.id.layout_to_account:
                showToAccountDialog();
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
                        mTextServicer.setText("126邮箱");
                        dialog.cancel();
                        break;
                    case R.id.text_163_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_163);
                        mTextServicer.setText("163邮箱");
                        dialog.cancel();
                        break;
                    case R.id.text_gmail_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_GMAIL);
                        mTextServicer.setText("Gmail");
                        dialog.cancel();
                        break;
                    case R.id.text_other_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OTHER);
                        mTextServicer.setText("其他邮箱");
                        dialog.cancel();
                        break;
                    case R.id.text_qq_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_QQ);
                        mTextServicer.setText("QQ邮箱");
                        dialog.cancel();
                        break;
                    case R.id.text_outlook_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OUTLOOK);
                        mTextServicer.setText("OutLook");
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
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP服务器地址");
        editText.setText(mNativeDataManager.getEmailHost());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailHost(editText.getText().toString());
                mTextAddress.setText(editText.getText().toString());
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
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP端口号");
        editText.setText(mNativeDataManager.getEmailPort());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailPort(editText.getText().toString());
                mTextPort.setText(editText.getText().toString());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void showToAccountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit,null,false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入目标邮箱账号");
        editText.setText(mNativeDataManager.getEmailToAccount());
        builder.setView(view);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailToAccount(editText.getText().toString());
                mTextToAccount.setText(editText.getText());
            }
        });

        builder.setNeutralButton("测试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.show();
                new AsyncTask<Void,Void,Integer>(){
                    @Override
                    protected Integer doInBackground(Void... params) {
                        Log.e("::::::::::","开始发送");
                        return EmailManager.relayEmail(mNativeDataManager,"这周任务完成不错");
                    }
                    @Override
                    protected void onPostExecute(Integer integer) {
                        progressDialog.cancel();
                        if(integer==EmailManager.CODE_SUCCESS){
                            mNativeDataManager.setEmailToAccount(editText.getText().toString());
                            mTextToAccount.setText(editText.getText());
                            Log.e("::::::::::","发送成功");
                        }else{
                            Log.e("::::::::::","发送失败");
                        }
                    }
                }.execute();
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
