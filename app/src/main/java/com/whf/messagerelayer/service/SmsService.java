package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;

import com.whf.messagerelayer.bean.Contact;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.NativeDataManager;
import com.whf.messagerelayer.utils.SmsRelayerManager;
import com.whf.messagerelayer.utils.db.DataBaseManager;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

public class SmsService extends IntentService {

    private NativeDataManager mNativeDataManager;
    private DataBaseManager mDataBaseManager;

    public SmsService() {
        super("SmsService");
    }

    public SmsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mNativeDataManager = new NativeDataManager(this);
        mDataBaseManager = new DataBaseManager(this);
        String mobile = intent.getStringExtra(Constant.EXTRA_MESSAGE_MOBILE);
        String content = intent.getStringExtra(Constant.EXTRA_MESSAGE_CONTENT);
        Set<String> keySet = mNativeDataManager.getKeywordSet();
        Set<String> regexSet = mNativeDataManager.getRegexSet();
        ArrayList<Contact> contactList = mDataBaseManager.getAllContact();

        if (keySet.size() != 0) {// 关键字规则
            for (String key : keySet) {
                if (content.contains(key)) {
                    relayMessage(content);
                    return;
                }
            }
        }

        if (contactList.size() != 0) {// 手机号规则
            for (Contact contact : contactList) {
                if (contact.getContactNum().equals(mobile)) {
                    relayMessage(content);
                    return;
                }
            }
        }

        if (regexSet.size() != 0) { // 正则表达式规则
            for (String regex : regexSet) {
                if (Pattern.matches(regex, content)) {
                    relayMessage(content);
                    return;
                }
            }
        }
    }

    private void relayMessage(String content) {
        String suffix = mNativeDataManager.getContentSuffix();
        String prefix = mNativeDataManager.getContentPrefix();
        if (suffix != null) {
            content = content + suffix;
        }
        if (prefix != null) {
            content = prefix + content;
        }
        if (mNativeDataManager.getSmsRelay()) {
            SmsRelayerManager.relaySms(mNativeDataManager, content);
        }
        if (mNativeDataManager.getEmailRelay()) {
            EmailRelayerManager.relayEmail(mNativeDataManager, content);
        }
    }

    @Override
    public void onDestroy() {
        mDataBaseManager.closeHelper();
        super.onDestroy();
    }
}
