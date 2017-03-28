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

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactAdapter extends BaseContactListAdapter {


    public ContactAdapter(Context context, List<Contact> contactList) {
        super(context, contactList);
    }

    @Override
    public void onBindViewHolder(final ContactHolder holder,int position) {
        super.onBindViewHolder(holder,position);
    }

    @Override
    protected void onItemClick(final Contact contact,ContactHolder holder) {
        if(contact.getSelected()){
            holder.mSelecter.setImageResource(R.mipmap.ic_selected);
        }else {
            holder.mSelecter.setImageResource(R.mipmap.ic_unselected);
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
}
