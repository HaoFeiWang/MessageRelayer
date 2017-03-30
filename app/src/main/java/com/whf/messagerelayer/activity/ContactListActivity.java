package com.whf.messagerelayer.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.ContactListAdapter;
import com.whf.messagerelayer.adapter.decoration.ContactDecoration;
import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.ContactManager;
import com.whf.messagerelayer.utils.NativeDataManager;
import com.whf.messagerelayer.utils.db.DataBaseManager;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mContactList;
    private ContactListAdapter mContactListAdapter;
    private DataBaseManager mDbManager;
    private NativeDataManager mNativeDataManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initActionbar();

        mDbManager = new DataBaseManager(this);
        mNativeDataManage = new NativeDataManager(this);

        mContactList = (RecyclerView) findViewById(R.id.list_contact);
        initRecyclerView();
    }

    private void initActionbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("查找").setIcon(R.mipmap.ic_serch)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
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
        intent.putExtra(Constant.EXTRA_DATA_CHANGE, true);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化RecyclerView
     * 从系统数据库中拿出所有联系人
     */
    private void initRecyclerView() {
        mContactList.addItemDecoration(new ContactDecoration());
        mContactList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new AsyncTask<Void, Void, ArrayList<Contact>>() {

            @Override
            protected ArrayList<Contact> doInBackground(Void... params) {
                return ContactManager.getContactList(ContactListActivity.this);
            }

            @Override
            protected void onPostExecute(ArrayList<Contact> contacts) {
                mContactListAdapter = new ContactListAdapter(ContactListActivity.this, contacts);
                mContactList.setAdapter(mContactListAdapter);
            }
        }.execute();
    }
}
