package com.whf.messagerelayer.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.ContactAdapter;
import com.whf.messagerelayer.adapter.decoration.ContactDecoration;
import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.utils.ContactManager;
import com.whf.messagerelayer.utils.NativeDataManager;
import com.whf.messagerelayer.utils.db.DataBaseManager;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mContactList;
    private ContactAdapter mContactListAdapter;
    private DataBaseManager mDbManager;
    private NativeDataManager mNativeDataManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mDbManager = new DataBaseManager(this);
        mNativeDataManage = new NativeDataManager(this);

        mContactList = (RecyclerView) findViewById(R.id.list_contact);
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contact_action_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.update:
                updateSelect();
                break;
            case R.id.serch:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSelect(){
        DataBaseManager dataBaseManager = new DataBaseManager(this);
        ArrayList<Contact> contactList = mContactListAdapter.getSelectedList();
        dataBaseManager.addContactList(contactList);
        dataBaseManager.closeHelper();
        startActivity(new Intent(this,SelectedContactActivity.class));
        finish();
    }

    private void initRecyclerView() {
        mContactList.addItemDecoration(new ContactDecoration());
        mContactList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(LayoutInflater.from(this).inflate(R.layout.dialog_progress, null, false));
//        final AlertDialog dialog = builder.create();
        new AsyncTask<Void, Void, ArrayList<Contact>>() {
            @Override
            protected void onPreExecute() {
//                if (mNativeDataManage.getDatabaseFlag()){
//                    dialog.show();
//                }
            }

            @Override
            protected ArrayList<Contact> doInBackground(Void... params) {
//                if (mNativeDataManage.getDatabaseFlag()) {
//                    for (Contact contact : ContactManager.getContactList(ContactListActivity.this)) {
//                        mDbManager.addContact(contact);
//                    }
//                    mNativeDataManage.setDatabaseFlag(false);
//                }
//                return mDbManager.getAllContact();
                return ContactManager.getContactList2(ContactListActivity.this);
            }

            @Override
            protected void onPostExecute(ArrayList<Contact> contacts) {
//                dialog.cancel();
                mContactListAdapter = new ContactAdapter(ContactListActivity.this, contacts);
                mContactList.setAdapter(mContactListAdapter);
            }
        }.execute();
    }
}
