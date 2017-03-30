package com.whf.messagerelayer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.NativeDataManager;

public class RuleActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mMoblieRuleLayout, mKeywordRuleLayout, mPrefixRuleLayout, mSuffixRuleLayout;
    private NativeDataManager mNativeDataManager;
    private TextView mPrefixText, mSuffixText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        initActionbar();
        mNativeDataManager = new NativeDataManager(this);
        initView();
        initData();
        initListener();
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        mMoblieRuleLayout = (RelativeLayout) findViewById(R.id.layout_rule_mobile);
        mKeywordRuleLayout = (RelativeLayout) findViewById(R.id.layout_rule_keyword);

        mPrefixRuleLayout = (RelativeLayout) findViewById(R.id.layout_rule_prefix);
        mSuffixRuleLayout = (RelativeLayout) findViewById(R.id.layout_rule_suffix);

        mPrefixText = (TextView) findViewById(R.id.text_prefix);
        mSuffixText = (TextView) findViewById(R.id.text_suffix);
    }

    private void initListener() {
        mMoblieRuleLayout.setOnClickListener(this);
        mKeywordRuleLayout.setOnClickListener(this);

        mPrefixRuleLayout.setOnClickListener(this);
        mSuffixRuleLayout.setOnClickListener(this);
    }

    private void initData() {
        String prefix = mNativeDataManager.getContentPrefix();
        if (prefix != null) {
            mPrefixText.setText(prefix);
        }
        String suffix = mNativeDataManager.getContentSuffix();
        if (suffix != null) {
            mSuffixText.setText(suffix);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_rule_mobile:
                startActivity(new Intent(this, SelectedContactActivity.class));
                break;
            case R.id.layout_rule_keyword:
                startActivity(new Intent(this, KeywordActivity.class));
                break;
            case R.id.layout_rule_prefix:
                showEditDialog("请输入要附加的内容前缀", Constant.KEY_CONTENT_PREFIX);
                break;
            case R.id.layout_rule_suffix:
                showEditDialog("请输入要附加的内容后缀", Constant.KEY_CONTENT_SUFFIX);
                break;
        }
    }

    private void showEditDialog(String title, final String key) {
        AlertDialog.Builder buileder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        TextView textTitle = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textTitle.setText(title);
        buileder.setView(view);
        buileder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        buileder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                if (key.equals(Constant.KEY_CONTENT_PREFIX)) {
                    mNativeDataManager.setContentPrefix(text);
                    mPrefixText.setText(text);
                } else {
                    mNativeDataManager.setContentSuffix(text);
                    mSuffixText.setText(text);
                }
            }
        });

        buileder.show();
    }
}
