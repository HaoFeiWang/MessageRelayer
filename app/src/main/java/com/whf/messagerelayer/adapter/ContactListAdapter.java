package com.whf.messagerelayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.bean.Contact;

import java.util.List;

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactHolder> {

    private LayoutInflater mInflater;
    private List<Contact> mContactList;

    public ContactListAdapter(Context context, List<Contact> contactList){
        this.mContactList = contactList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(mInflater.inflate(R.layout.item_contact,parent,false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.mContactName.setText(contact.getContactName());
        holder.mContactNum.setText(contact.getContactNum());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
