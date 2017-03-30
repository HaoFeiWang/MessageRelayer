package com.whf.messagerelayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.whf.messagerelayer.bean.Contact;

import java.util.ArrayList;


/**
 * Created by WHF on 2017/3/28.
 */

public class ContactManager {

    /**
     * 从ContentProvider中获取所有联系人
     * @param context
     * @return
     */
    public static ArrayList<Contact> getContactList(Context context){
        ArrayList<Contact> mContactList = new ArrayList<>();

        Cursor cursor = context.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                        ,null,null,null,null);
        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setContactName(cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

            contact.setContactNum(cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            mContactList.add(contact);

        }
        return mContactList;
    }

}
