package com.whf.messagerelayer.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.whf.messagerelayer.R;
import com.whf.messagerelayer.utils.NativeDataManager;

import java.util.HashSet;
import java.util.Set;


public class KeywordActivity extends AppCompatActivity {

    private FlexboxLayout mFlexboxLayout;
    private HashSet<String> mTextSet;
    private NativeDataManager mNativeDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
        initActionbar();

        mNativeDataManager = new NativeDataManager(this);
        mTextSet = (HashSet<String>) mNativeDataManager.getKeywordSet();

        this.mFlexboxLayout = (FlexboxLayout) findViewById(R.id.layout_flexbox);
        initFlexboxLayout();
    }

    private void initActionbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("添加").setIcon(R.mipmap.ic_add)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showAddDialog();
                        return true;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit,null,false);
        TextView textView = (TextView) view.findViewById(R.id.dialog_title);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);

        textView.setText("请输入关键字");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String keyword = editText.getText().toString();
                if(keyword.length()!=0){
                    Set<String> set = new HashSet<>(mNativeDataManager.getKeywordSet());
                    mFlexboxLayout.addView(createItemView(keyword,set.size()));
                    set.add(keyword);
                    mNativeDataManager.setKeywordSet(set);
                }else{
                    Toast.makeText(KeywordActivity.this,"请输入有效字符",Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void initFlexboxLayout() {
        int i = 0;
        for (String keyword:mTextSet) {
            mFlexboxLayout.addView(createItemView(keyword,i++));
        }
    }

    private View createItemView(String text, int index) {
        View view =  LayoutInflater.from(this).inflate(R.layout.item_keyword,null,false);
        TextView textView = (TextView) view.findViewById(R.id.text_keyword);
        textView.setText(text);
        initBackground(textView, index);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null
                , getResources().getDrawable(R.mipmap.ic_del), null);

        return view;
    }

    private void initBackground(TextView textView, int index) {
        switch (index % 4) {
            case 0:
                textView.setBackgroundResource(R.drawable.bg_keyword_one);
                break;
            case 1:
                textView.setBackgroundResource(R.drawable.bg_keyword_two);
                break;
            case 2:
                textView.setBackgroundResource(R.drawable.bg_keyword_three);
                break;
            case 3:
                textView.setBackgroundResource(R.drawable.bg_keyword_four);
                break;
        }
    }

    /**
     * 点击删除其View
     * @param view
     */
    public void removeClick(View view){
        TextView textView = (TextView) view;
        String keyword = textView.getText().toString();
        Set<String> set = new HashSet<>(mNativeDataManager.getKeywordSet());
        set.remove(keyword);
        mNativeDataManager.setKeywordSet(set);

        mFlexboxLayout.removeView((View) view.getParent());
    }
}
