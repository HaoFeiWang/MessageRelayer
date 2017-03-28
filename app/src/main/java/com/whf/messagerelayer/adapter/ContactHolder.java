package com.whf.messagerelayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whf.messagerelayer.R;

/**
 * Created by WHF on 2017/3/28.
 */
public class ContactHolder extends RecyclerView.ViewHolder{

    public TextView mContactName,mContactNum;
    public ImageView mSelecter;

    public ContactHolder(View itemView) {
        super(itemView);
        mContactName = (TextView) itemView.findViewById(R.id.text_contact_name);
        mContactNum = (TextView) itemView.findViewById(R.id.text_contact_num);
        mSelecter = (ImageView) itemView.findViewById(R.id.image_contact_selecter);
    }
}
