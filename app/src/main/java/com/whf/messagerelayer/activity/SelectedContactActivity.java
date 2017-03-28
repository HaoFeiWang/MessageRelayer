package com.whf.messagerelayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.ContactSelectedAdapter;
import com.whf.messagerelayer.adapter.decoration.ContactDecoration;
import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.utils.db.DataBaseManager;

import java.util.ArrayList;

public class SelectedContactActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mSelcetedContactList;
    private Button mAddContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_contact);

        mSelcetedContactList = (RecyclerView) findViewById(R.id.list_selected_contact);
        mAddContactButton = (Button) findViewById(R.id.button_add_contact);
        mAddContactButton.setOnClickListener(this);

        initRecyclerView();
    }

    private void initRecyclerView(){
        mSelcetedContactList.addItemDecoration(new ContactDecoration());
        mSelcetedContactList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        DataBaseManager dataBaseManager = new DataBaseManager(this);
        ArrayList<Contact> selectedList = dataBaseManager.getAllContact();
        mSelcetedContactList.setAdapter(new ContactSelectedAdapter(this,selectedList));
        dataBaseManager.closeHelper();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_contact:
                startActivity(new Intent(this, ContactListActivity.class));
                finish();
                break;
        }
    }
}
