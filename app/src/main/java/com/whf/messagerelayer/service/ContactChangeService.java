package com.whf.messagerelayer.service;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;

import com.whf.messagerelayer.activity.ContactListActivity;
import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.utils.ContactManager;
import com.whf.messagerelayer.utils.db.DataBaseManager;

public class ContactChangeService extends Service {

    private ContentObserver mObserver;
    private DataBaseManager mDbManager;

    public ContactChangeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mDbManager = new DataBaseManager(this);
        mObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                updataContent();
            }
        };
        getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI,true,mObserver);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updataContent(){
        mDbManager.deleteAll();
        for (Contact contact : ContactManager.getContactList(ContactChangeService.this)) {
            mDbManager.addContact(contact);
        }
    }

    /**
     * 释放相关资源
     */
    @Override
    public void onDestroy() {
        mDbManager.closeHelper();
        getContentResolver().unregisterContentObserver(mObserver);
        super.onDestroy();
    }
}
