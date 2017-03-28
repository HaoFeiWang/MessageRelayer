package com.whf.messagerelayer.adapter;

import android.content.Context;

import com.whf.messagerelayer.adapter.holder.ContactHolder;
import com.whf.messagerelayer.bean.Contact;

import java.util.List;

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactSelectAdapter extends BaseContactListAdapter{

    public ContactSelectAdapter(Context context, List<Contact> contactList) {
        super(context, contactList);
    }

    @Override
    protected void onItemClick(Contact contact, ContactHolder holder) {

    }
}
