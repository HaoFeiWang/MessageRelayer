package com.whf.messagerelayer.adapter;

import android.content.Context;
import android.util.Log;
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

public class ContactAdapter extends BaseContactAdapter {

    private ArrayList<Contact> mSelectedList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        super(context, contactList);
        mSelectedList = new ArrayList<>();
    }

    @Override
    protected void onItemClick(final Contact contact,ContactHolder holder) {
        if(contact.getSelected()==1){
            holder.mSelecter.setImageResource(R.mipmap.ic_selected);
        }else {
            holder.mSelecter.setImageResource(R.mipmap.ic_unselected);
        }
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contact.getSelected()==1){
                    contact.setSelected(0);
                    mSelectedList.remove(contact);
                }else{
                    contact.setSelected(1);
                    mSelectedList.add(contact);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    protected View getItemView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_contact,parent,false);
    }

    public ArrayList<Contact> getSelectedList(){
        return mSelectedList;
    }
}
