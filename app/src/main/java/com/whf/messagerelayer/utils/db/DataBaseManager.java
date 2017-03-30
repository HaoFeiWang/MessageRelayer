package com.whf.messagerelayer.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.FormatMobile;

import java.util.ArrayList;
import java.util.List;


/**
 * 存储被选中的联系人的数据库管理类
 * Created by WHF on 2017/3/28.
 */

public class DataBaseManager {

    private DataBaseHelper mHelper;

    public DataBaseManager(Context context) {
        this.mHelper = new DataBaseHelper(context);

    }

    /**
     * 添加一条数据
     *
     * @param contact
     */
    public void addContact(Contact contact) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.DB_KEY_NAME, contact.getContactName());
        values.put(Constant.DB_KEY_MOBLIE, contact.getContactNum());
        database.insert(Constant.DB_TABLE_NAME, null, values);
    }

    /**
     * 添加多条数据
     *
     */
    public void addContactList(List<Contact> contactList) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        for (Contact contact : contactList) {
            String num = contact.getContactNum();
            if (FormatMobile.hasPrefix(num)){
                num = FormatMobile.formatMobile(num);
            }
            ContentValues values = new ContentValues();
            values.put(Constant.DB_KEY_NAME, contact.getContactName());
            values.put(Constant.DB_KEY_MOBLIE, num);
            database.insert(Constant.DB_TABLE_NAME, null, values);
        }
    }

    /**
     * 获取所有联系人
     */
    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> contactList = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(Constant.DB_TABLE_NAME
                , null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setContactName(cursor.getString(cursor.getColumnIndex(Constant.DB_KEY_NAME)));
            contact.setContactNum(cursor.getString(cursor.getColumnIndex(Constant.DB_KEY_MOBLIE)));
            contactList.add(contact);
        }
        return contactList;
    }

    /**
     * 删除某一联系人，根据其手机号
     *
     * @param mobile
     */
    public void deleteContactFromMobile(String mobile) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        database.delete(Constant.DB_TABLE_NAME, Constant.DB_KEY_MOBLIE + "= ?" , new String[]{mobile});
    }

    /**
     * 删除所有联系人
     */
    public void deleteAll() {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        database.delete(Constant.DB_TABLE_NAME, null, null);
    }

    /**
     * 关闭SqLiteDatabase
     */
    public void closeHelper() {
        mHelper.close();
    }
}
