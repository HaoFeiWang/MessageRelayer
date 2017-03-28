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

    public static ArrayList<String> getContactNumFromId(Context context, String contactId) {
        Cursor contactPhone = context.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null
                        , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);

        ArrayList<String> phoneNumList = new ArrayList<>();
        while (contactPhone.moveToNext()) {
            //存在一个ID多个电话的Bug
            phoneNumList.add(contactPhone.getString(contactPhone
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }
        return phoneNumList;

    }

    public static ArrayList<Contact> getContactList(Context context) {
        ArrayList<Contact> mContactList = new ArrayList<>();
        Cursor cursor = context.getContentResolver()
                .query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            //获取联系人的ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            ArrayList<String> contactNumList = getContactNumFromId(context, contactId);
            //获取联系人的名字
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (contactNumList.size() > 0) {
                mContactList.add(new Contact(contactId, contactName, contactNumList.get(0)));
            }
        }
        return mContactList;
    }

}
