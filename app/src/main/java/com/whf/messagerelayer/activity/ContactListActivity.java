package com.whf.messagerelayer.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.ContactAdapter;
import com.whf.messagerelayer.adapter.decoration.ContactDecoration;
import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.utils.ContactManager;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mContactList;
    private ContactAdapter mContactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactList = (RecyclerView) findViewById(R.id.list_contact);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mContactList.addItemDecoration(new ContactDecoration());
        mContactList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        final ProgressDialog dialog = new ProgressDialog(this);
        new AsyncTask<Void,Void,ArrayList<Contact>>() {
            @Override
            protected void onPreExecute() {
                dialog.show();
            }

            @Override
            protected ArrayList<Contact> doInBackground(Void... params) {
                return ContactManager.getContactList(ContactListActivity.this);
            }

            @Override
            protected void onPostExecute(ArrayList<Contact> contacts) {
                dialog.cancel();
                mContactListAdapter = new ContactAdapter(ContactListActivity.this,contacts);

                mContactList.setAdapter(mContactListAdapter);
            }
        }.execute();


    }
}
