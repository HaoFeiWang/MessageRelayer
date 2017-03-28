package com.whf.messagerelayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.bean.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactHolder> {

    private LayoutInflater mInflater;
    private List<Contact> mContactList;

    public ContactListAdapter(Context context, List<Contact> contactList) {
        if (contactList == null) {
            this.mContactList = new ArrayList<>();
        } else {
            this.mContactList = contactList;
        }
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_contact, parent, false);
        ContactHolder holder = new ContactHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContactHolder holder,int position) {
        final Contact contact = mContactList.get(position);
        holder.mContactName.setText(contact.getContactName());
        holder.mContactNum.setText(contact.getContactNum());
        if(contact.getSelected()){
            holder.mSelecter.setImageResource(R.mipmap.ic_select);
        }else {
            holder.mSelecter.setImageResource(R.mipmap.ic_unselect);
        }
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contact.getSelected()){
                    contact.setSelected(false);
                }else{
                    contact.setSelected(true);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

}
