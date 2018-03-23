package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.whf.messagerelayer.data.bean.Contact;
import com.whf.messagerelayer.data.Constants;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.SharedPreferenceUtil;
import com.whf.messagerelayer.utils.SmsRelayerManager;
import com.whf.messagerelayer.data.DataBaseManager;

import java.util.ArrayList;
import java.util.Set;

public class RelayService extends IntentService {

    private static final String TAG = Constants.TAG + RelayService.class.getSimpleName();

    private SharedPreferenceUtil mSharedPreferenceUtil;
    private DataBaseManager mDataBaseManager;

    public RelayService() {
        super(RelayService.class.getSimpleName());
    }

    public RelayService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);
        mDataBaseManager = new DataBaseManager(this);

        String mobile = intent.getStringExtra(Constants.EXTRA_MESSAGE_MOBILE);
        String content = intent.getStringExtra(Constants.EXTRA_MESSAGE_CONTENT);
        Set<String> keySet = mSharedPreferenceUtil.getKeywordSet();
        ArrayList<Contact> contactList = mDataBaseManager.getAllContact();

        Log.d(TAG, "Receive Mobile = " + mobile + " Content = " + content);

        //无转发规则
        if (keySet.size() == 0 && contactList.size() == 0) {
            relayMessage(content);
        } else if (keySet.size() != 0 && contactList.size() == 0) {//仅有关键字规则
            for (String key : keySet) {
                if (content.contains(key)) {
                    relayMessage(content);
                    break;
                }
            }
        } else if (keySet.size() == 0 && contactList.size() != 0) {//仅有手机号规则
            for (Contact contact : contactList) {
                if (contact.getContactNum().equals(mobile)) {
                    relayMessage(content);
                    break;
                }
            }
        } else {//两种规则共存
            out:
            for (Contact contact : contactList) {
                if (contact.getContactNum().equals(mobile)) {
                    for (String key : keySet) {
                        if (content.contains(key)) {
                            relayMessage(content);
                            break out;
                        }
                    }
                }
            }
        }
    }

    private void relayMessage(String content) {
        String suffix = mSharedPreferenceUtil.getContentSuffix();
        String prefix = mSharedPreferenceUtil.getContentPrefix();
        if (suffix != null) {
            content = content + suffix;
        }
        if (prefix != null) {
            content = prefix + content;
        }
        if (mSharedPreferenceUtil.getSmsRelay()) {
            SmsRelayerManager.relaySms(mSharedPreferenceUtil, content);
        }
        if (mSharedPreferenceUtil.getEmailRelay()) {
            EmailRelayerManager.relayEmail(mSharedPreferenceUtil, content);
        }
    }

    @Override
    public void onDestroy() {
        mDataBaseManager.closeHelper();
        super.onDestroy();
    }
}
