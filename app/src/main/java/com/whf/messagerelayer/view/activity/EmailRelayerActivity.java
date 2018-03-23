package com.whf.messagerelayer.view.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.data.Constant;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.NativeDataManager;

public class EmailRelayerActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private Switch mEmailSwitch, mSslSwitch;
    private RelativeLayout mLayoutAccount, mLayoutServicer, mLayoutAddress, mLayoutPort, mLayoutToAccount, mLayoutSenderName, mLayoutSubject;
    private TextView mTextAccount, mTextServicer, mTextAddress, mTextPort, mTextToAccount, mTextSenderName, mTextSubject;
    private View mAddressLine, mPortLine;

    private NativeDataManager mNativeDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_relayer);
        initActionbar();

        this.mNativeDataManager = new NativeDataManager(this);
        initView();
        initData();
        initListener();
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        String servecer = mNativeDataManager.getEmailServicer();
        if (servecer.equals(Constant.EMAIL_SERVICER_OTHER)) {
            setAddressVisiable();
        } else {
            setAddressGone();
        }
        mEmailSwitch.setChecked(mNativeDataManager.getEmailRelay());
        mSslSwitch.setChecked(mNativeDataManager.getEmailSsl());
        mTextServicer.setText(servecer);
        mTextAccount.setText(mNativeDataManager.getEmailAccount());
        mTextToAccount.setText(mNativeDataManager.getEmailToAccount());
        mTextSenderName.setText(mNativeDataManager.getEmailSenderName());
        mTextSubject.setText(mNativeDataManager.getEmailSubject());
    }

    /**
     * 显示服务器和端口的设置
     */
    private void setAddressVisiable() {
        mLayoutAddress.setVisibility(View.VISIBLE);
        mLayoutPort.setVisibility(View.VISIBLE);
        mAddressLine.setVisibility(View.VISIBLE);
        mPortLine.setVisibility(View.VISIBLE);
        String host = mNativeDataManager.getEmailHost();
        String port = mNativeDataManager.getEmailPort();
        if (host != null) {
            mTextAddress.setText(host);
        }
        if (port != null) {
            mTextPort.setText(port);
        }
    }

    /**
     * 隐藏服务器和端口设置的
     */
    private void setAddressGone() {
        mLayoutAddress.setVisibility(View.GONE);
        mLayoutPort.setVisibility(View.GONE);
        mAddressLine.setVisibility(View.GONE);
        mPortLine.setVisibility(View.GONE);
    }

    private void initView() {
        mEmailSwitch = (Switch) findViewById(R.id.switch_email);
        mSslSwitch = (Switch) findViewById(R.id.switch_ssl);

        mLayoutServicer = (RelativeLayout) findViewById(R.id.layout_servicer);
        mLayoutAccount = (RelativeLayout) findViewById(R.id.layout_account);
        mLayoutAddress = (RelativeLayout) findViewById(R.id.layout_address);
        mLayoutPort = (RelativeLayout) findViewById(R.id.layout_port);
        mLayoutToAccount = (RelativeLayout) findViewById(R.id.layout_to_account);
        mLayoutSenderName = (RelativeLayout) findViewById(R.id.layout_sender_name);
        mLayoutSubject = (RelativeLayout) findViewById(R.id.layout_subject);

        mTextServicer = (TextView) findViewById(R.id.text_servicer);
        mTextAccount = (TextView) findViewById(R.id.text_account);
        mTextAddress = (TextView) findViewById(R.id.text_address);
        mTextPort = (TextView) findViewById(R.id.text_port);
        mTextToAccount = (TextView) findViewById(R.id.text_to_account);
        mTextSenderName = (TextView) findViewById(R.id.text_sender_name);
        mTextSubject = (TextView) findViewById(R.id.text_subject);

        mAddressLine = findViewById(R.id.line_address);
        mPortLine = findViewById(R.id.line_port);
    }

    private void initListener() {
        mLayoutServicer.setOnClickListener(this);
        mLayoutAccount.setOnClickListener(this);
        mLayoutAddress.setOnClickListener(this);
        mLayoutPort.setOnClickListener(this);
        mLayoutToAccount.setOnClickListener(this);
        mLayoutSenderName.setOnClickListener(this);
        mLayoutSubject.setOnClickListener(this);

        mEmailSwitch.setOnCheckedChangeListener(this);
        mSslSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
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
        switch (v.getId()) {
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
            case R.id.layout_sender_name:
                showSenderNameDialog();
                break;
            case R.id.layout_subject:
                showSubjectDialog();
                break;
        }
    }

    private void showSubjectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入邮件主题");
        editText.setText(mNativeDataManager.getEmailSubject());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailSubject(editText.getText().toString());
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

    private void showSenderNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入发送方名称");
        editText.setText(mNativeDataManager.getEmailSenderName());
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNativeDataManager.setEmailSenderName(editText.getText().toString());
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

    private void showServicerDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_email_servicer, null, false);
        builder.setView(view);
        final AlertDialog dialog = builder.show();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.text_126_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_126);
                        mTextServicer.setText("126邮箱");
                        dialog.cancel();
                        setAddressGone();
                        break;
                    case R.id.text_163_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_163);
                        mTextServicer.setText("163邮箱");
                        dialog.cancel();
                        setAddressGone();
                        break;
                    case R.id.text_gmail_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_GMAIL);
                        mTextServicer.setText("Gmail");
                        dialog.cancel();
                        setAddressGone();
                        break;
                    case R.id.text_other_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OTHER);
                        mTextServicer.setText("其他邮箱");
                        dialog.cancel();
                        setAddressVisiable();
                        break;
                    case R.id.text_qq_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_QQ);
                        mTextServicer.setText("QQ邮箱");
                        dialog.cancel();
                        setAddressGone();
                        break;
                    case R.id.text_outlook_email:
                        mNativeDataManager.setEmailServicer(Constant.EMAIL_SERVICER_OUTLOOK);
                        mTextServicer.setText("OutLook");
                        dialog.cancel();
                        setAddressGone();
                        break;
                }
            }
        };
        initServicerDialogView(onClickListener, view);

    }

    private View initServicerDialogView(View.OnClickListener onClickListener, View view) {
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

    private void showAccountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_email_account, null, false);
        builder.setView(view);

        final EditText textAccount = (EditText) view.findViewById(R.id.editText_account);
        final EditText textPassword = (EditText) view.findViewById(R.id.editText_password);

        String text = mTextAccount.getText().toString();
        if (!text.equals("点击设置")) {
            textAccount.setText(text);
            textPassword.setText(mNativeDataManager.getEmailPassword());
        }

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

    private void showAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP服务器地址");
        String text = mTextAddress.getText().toString();
        editText.setText(text);

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
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入SMTP端口号");
        String text = mTextPort.getText().toString();
        editText.setText(text);

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
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textViewTitle.setText("请输入目标邮箱账号");
        String text = mTextToAccount.getText().toString();
        if (!text.equals("点击设置")) {
            editText.setText(text);
        }

        builder.setView(view);
        final AlertDialog.Builder progressBuilder = new AlertDialog.Builder(this);
        progressBuilder.setView(LayoutInflater.from(this).inflate(R.layout.dialog_progress,null,false));

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
                final AlertDialog progressDialog = progressBuilder.show();
                new AsyncTask<Void, Void, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        return EmailRelayerManager.relayEmail(mNativeDataManager, "配置正确！");
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        progressDialog.cancel();
                        if (integer == EmailRelayerManager.CODE_SUCCESS) {
                            mNativeDataManager.setEmailToAccount(editText.getText().toString());
                            mTextToAccount.setText(editText.getText());
                            Toast.makeText(EmailRelayerActivity.this, "邮箱配置正确", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EmailRelayerActivity.this, "邮箱配置有误", Toast.LENGTH_SHORT).show();
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
