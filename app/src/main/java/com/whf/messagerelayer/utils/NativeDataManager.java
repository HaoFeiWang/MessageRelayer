package com.whf.messagerelayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.whf.messagerelayer.confing.Constant;

/**
 * Created by WHF on 2017/3/24.
 */

public class NativeDataManager {

    private SharedPreferences mPreference;

    public NativeDataManager(Context context) {
        mPreference = context.getSharedPreferences(Constant.SETTING_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getObjectMobile() {
        return mPreference.getString(Constant.KEY_OBJECT_MOBILE, "点击设置");
    }

    public void setObjectMobile(String mobile) {
        mPreference.edit().putString(Constant.KEY_OBJECT_MOBILE, mobile).apply();
    }

    public void setSmsRelay(Boolean b) {
        mPreference.edit().putBoolean(Constant.KEY_RELAY_SMS, b).apply();
    }

    public Boolean getSmsRelay() {
        return mPreference.getBoolean(Constant.KEY_RELAY_SMS, false);
    }

    public void setEmailRelay(Boolean b) {
        mPreference.edit().putBoolean(Constant.KEY_RELAY_EMAIL, b).apply();
    }

    public boolean getEmailRelay() {
        return mPreference.getBoolean(Constant.KEY_RELAY_EMAIL, false);
    }

    public void setReceiver(Boolean b) {
        mPreference.edit().putBoolean(Constant.KEY_RECEIVER, b).apply();
    }

    public boolean getReceiver() {
        return mPreference.getBoolean(Constant.KEY_RECEIVER, true);
    }

    public String getEmailServicer() {
        return mPreference.getString(Constant.KEY_EMAIL_SERVICER, Constant.EMAIL_SERVICER_QQ);
    }

    public void setEmailServicer(String servicer) {
        mPreference.edit().putString(Constant.KEY_EMAIL_SERVICER, servicer).apply();
    }

    public void setEmailAccount(String account) {
        mPreference.edit().putString(Constant.KEY_EMAIL_ACCOUNT, account).apply();
    }

    public String getEmailAccount() {
        return mPreference.getString(Constant.KEY_EMAIL_ACCOUNT, "点击设置");
    }

    public void setEmailPassword(String password) {
        mPreference.edit().putString(Constant.KEY_EMAIL_PASSWORD, password).apply();
    }

    public String getEmailPassword() {
        return mPreference.getString(Constant.KEY_EMAIL_PASSWORD, null);
    }

    public void setEmailHost(String address) {
        mPreference.edit().putString(Constant.KEY_EMAIL_HOST, address).apply();
    }

    public void setEmailPort(String port) {
        mPreference.edit().putString(Constant.KEY_EMAIL_PORT, port).apply();
    }

    public String getEmailHost() {
        return mPreference.getString(Constant.KEY_EMAIL_HOST, "点击设置");
    }

    public String getEmailPort() {
        return mPreference.getString(Constant.KEY_EMAIL_PORT, "点击设置");
    }

    public void setEmailSsl(Boolean b) {
        mPreference.edit().putBoolean(Constant.KEY_EMAIL_SSL, b).apply();
    }

    public Boolean getEmailSsl() {
        return mPreference.getBoolean(Constant.KEY_EMAIL_SSL, true);
    }

    public void setEmailToAccount(String account) {
        mPreference.edit().putString(Constant.KEY_EMAIL_TO_ACCOUNT, account).apply();
    }

    public String getEmailToAccount() {
        return mPreference.getString(Constant.KEY_EMAIL_TO_ACCOUNT, "点击设置");
    }

    public void setEmailSenderName(String name) {
        mPreference.edit().putString(Constant.KEY_EMAIL_SENDER_NAME, name).apply();
    }

    public String getEmailSenderName() {
        return mPreference.getString(Constant.KEY_EMAIL_SENDER_NAME, "短信助手");
    }

    public void setEmailSubject(String subject) {
        mPreference.edit().putString(Constant.KEY_EMAIL_SUBJECT, subject).apply();
    }

    public String getEmailSubject() {
        return mPreference.getString(Constant.KEY_EMAIL_SUBJECT, "短信转发");
    }

    public Boolean getDatabaseFlag(){
        return mPreference.getBoolean(Constant.DB_CONTACT_FLAG,true);
    }

    public void setDatabaseFlag(boolean flag){
        mPreference.edit().putBoolean(Constant.DB_CONTACT_FLAG,flag).apply();
    }

}