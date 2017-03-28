package com.whf.messagerelayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.adapter.holder.ContactHolder;
import com.whf.messagerelayer.bean.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by WHF on 2017/3/28.
 */

abstract class BaseContactAdapter extends RecyclerView.Adapter<ContactHolder>{

    LayoutInflater mInflater;
    List<Contact> mContactList;

    BaseContactAdapter(Context context, List<Contact> contactList) {
        if (contactList == null) {
            this.mContactList = new ArrayList<>();
        } else {
            this.mContactList = contactList;
        }
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getItemView(mInflater,parent);
        ContactHolder holder = new ContactHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContactHolder holder,int position) {
        final Contact contact = mContactList.get(position);
        holder.mContactName.setText(contact.getContactName());
        holder.mContactNum.setText(contact.getContactNum());

        onItemClick(contact,holder);
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    protected abstract void onItemClick(Contact contact,ContactHolder holder);
    protected abstract View getItemView(LayoutInflater inflater,ViewGroup parent);


}
