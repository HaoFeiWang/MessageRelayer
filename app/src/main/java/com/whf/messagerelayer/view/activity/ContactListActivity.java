package com.whf.messagerelayer.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.view.adapter.ContactListAdapter;
import com.whf.messagerelayer.view.adapter.decoration.ContactDecoration;
import com.whf.messagerelayer.data.bean.Contact;
import com.whf.messagerelayer.data.Constants;
import com.whf.messagerelayer.utils.ContactManager;
import com.whf.messagerelayer.data.DataBaseManager;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mContactRecycler;
    private ContactListAdapter mContactListAdapter;
    private ArrayList<Contact> mContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initActionbar();

        mContactRecycler = (RecyclerView) findViewById(R.id.list_contact);
        initRecyclerView();
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("查找").setIcon(R.mipmap.ic_find)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showFindDialog();
                        return true;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add("确定").setIcon(R.mipmap.ic_save)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        updateSelect();
                        return true;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    private void showFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        TextView textView = (TextView) view.findViewById(R.id.dialog_title);
        textView.setText("请输入要查找的联系人全名");

        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("查找", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editText.getText().toString();
                if (name.length() != 0){
                    findName(name);
                }
            }
        });

        builder.show();
    }

    /**
     * 根据名字查找
     */
    private void findName(String name) {
        int length = mContactList.size();
        for (int i=0;i<length;i++){
            if (mContactList.get(i).getContactName().equals(name)) {
                mContactRecycler.scrollToPosition(i);
                Toast.makeText(this,"找到啦~~",Toast.LENGTH_LONG).show();
                break;
            }else if(i==length-1){
                Toast.makeText(this,"没找到~~",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 保存被选中的联系人到自定义数据库
     */
    private void updateSelect() {
        DataBaseManager dataBaseManager = new DataBaseManager(this);
        ArrayList<Contact> contactList = mContactListAdapter.getSelectedList();
        dataBaseManager.addContactList(contactList);
        dataBaseManager.closeHelper();
        Intent intent = new Intent(this, SelectedContactActivity.class);
        intent.putExtra(Constants.EXTRA_DATA_CHANGE, true);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化RecyclerView
     * 从系统数据库中拿出所有联系人
     */
    private void initRecyclerView() {
        mContactRecycler.addItemDecoration(new ContactDecoration());
        mContactRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new AsyncTask<Void, Void, ArrayList<Contact>>() {

            @Override
            protected ArrayList<Contact> doInBackground(Void... params) {
                mContactList = ContactManager.getContactList(ContactListActivity.this);
                return mContactList;
            }

            @Override
            protected void onPostExecute(ArrayList<Contact> contacts) {
                mContactListAdapter = new ContactListAdapter(ContactListActivity.this, contacts);
                mContactRecycler.setAdapter(mContactListAdapter);
            }
        }.execute();
    }
}
