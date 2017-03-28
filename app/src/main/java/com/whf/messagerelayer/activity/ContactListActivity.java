package com.whf.messagerelayer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.ContactListAdapter;
import com.whf.messagerelayer.utils.ContactManager;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactList = (RecyclerView) findViewById(R.id.list_contact);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mContactList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mContactList.setAdapter(new ContactListAdapter(this, ContactManager.getContactList(this)));
    }
}
