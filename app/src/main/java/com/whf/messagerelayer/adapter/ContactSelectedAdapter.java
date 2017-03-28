package com.whf.messagerelayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.holder.ContactHolder;
import com.whf.messagerelayer.bean.Contact;

import java.util.List;

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactSelectedAdapter extends BaseContactAdapter {

    public ContactSelectedAdapter(Context context, List<Contact> contactList) {
        super(context, contactList);
    }

    @Override
    protected void onItemClick(Contact contact, ContactHolder holder) {

    }

    @Override
    protected View getItemView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_selected_contact,parent,false);
    }
}
