package com.whf.messagerelayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.whf.messagerelayer.data.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * SharedPreference工具
 * Created by WHF on 2017/3/24.
 */

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil mPreferenceUtil;
    private static SharedPreferences mPreference;

    private SharedPreferenceUtil(Context context) {
        mPreference = context.getSharedPreferences(Constants.SETTING_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getInstance(Context context){
        if (mPreferenceUtil==null){
            synchronized (SharedPreferenceUtil.class){
                if (mPreferenceUtil == null){
                    mPreferenceUtil = new SharedPreferenceUtil(context);
                }
            }
        }
        return mPreferenceUtil;
    }

    public String getObjectMobile() {
        return mPreference.getString(Constants.KEY_OBJECT_MOBILE, "点击设置");
    }

    public void setObjectMobile(String mobile) {
        mPreference.edit().putString(Constants.KEY_OBJECT_MOBILE, mobile).apply();
    }

    public void setSmsRelay(Boolean b) {
        mPreference.edit().putBoolean(Constants.KEY_RELAY_SMS, b).apply();
    }

    public Boolean getSmsRelay() {
        return mPreference.getBoolean(Constants.KEY_RELAY_SMS, false);
    }

    public void setEmailRelay(Boolean b) {
        mPreference.edit().putBoolean(Constants.KEY_RELAY_EMAIL, b).apply();
    }

    public boolean getEmailRelay() {
        return mPreference.getBoolean(Constants.KEY_RELAY_EMAIL, false);
    }

    public void setReceiver(Boolean b) {
        mPreference.edit().putBoolean(Constants.KEY_RECEIVER, b).apply();
    }

    public boolean getReceiver() {
        return mPreference.getBoolean(Constants.KEY_RECEIVER, true);
    }

    public String getEmailServicer() {
        return mPreference.getString(Constants.KEY_EMAIL_SERVICER, Constants.EMAIL_SERVICER_QQ);
    }

    public void setEmailServicer(String servicer) {
        mPreference.edit().putString(Constants.KEY_EMAIL_SERVICER, servicer).apply();
    }

    public void setEmailAccount(String account) {
        mPreference.edit().putString(Constants.KEY_EMAIL_ACCOUNT, account).apply();
    }

    public String getEmailAccount() {
        return mPreference.getString(Constants.KEY_EMAIL_ACCOUNT, "点击设置");
    }

    public void setEmailPassword(String password) {
        mPreference.edit().putString(Constants.KEY_EMAIL_PASSWORD, password).apply();
    }

    public String getEmailPassword() {
        return mPreference.getString(Constants.KEY_EMAIL_PASSWORD, null);
    }

    public void setEmailHost(String address) {
        mPreference.edit().putString(Constants.KEY_EMAIL_HOST, address).apply();
    }

    public void setEmailPort(String port) {
        mPreference.edit().putString(Constants.KEY_EMAIL_PORT, port).apply();
    }

    public String getEmailHost() {
        return mPreference.getString(Constants.KEY_EMAIL_HOST, null);
    }

    public String getEmailPort() {
        return mPreference.getString(Constants.KEY_EMAIL_PORT, null);
    }

    public void setEmailSsl(Boolean b) {
        mPreference.edit().putBoolean(Constants.KEY_EMAIL_SSL, b).apply();
    }

    public Boolean getEmailSsl() {
        return mPreference.getBoolean(Constants.KEY_EMAIL_SSL, true);
    }

    public void setEmailToAccount(String account) {
        mPreference.edit().putString(Constants.KEY_EMAIL_TO_ACCOUNT, account).apply();
    }

    public String getEmailToAccount() {
        return mPreference.getString(Constants.KEY_EMAIL_TO_ACCOUNT, "点击设置");
    }

    public void setEmailSenderName(String name) {
        mPreference.edit().putString(Constants.KEY_EMAIL_SENDER_NAME, name).apply();
    }

    public String getEmailSenderName() {
        return mPreference.getString(Constants.KEY_EMAIL_SENDER_NAME, "短信助手");
    }

    public void setEmailSubject(String subject) {
        mPreference.edit().putString(Constants.KEY_EMAIL_SUBJECT, subject).apply();
    }

    public String getEmailSubject() {
        return mPreference.getString(Constants.KEY_EMAIL_SUBJECT, "短信转发");
    }

    public void setKeywordSet(Set<String> values){
        mPreference.edit().putStringSet(Constants.KEY_KEYWORD_LIST,values).apply();
    }

    public Set<String> getKeywordSet(){
        return mPreference.getStringSet(Constants.KEY_KEYWORD_LIST,new HashSet<String>());
    }

    public void setContentPrefix(String prefix){
        mPreference.edit().putString(Constants.KEY_CONTENT_PREFIX,prefix).apply();
    }

    public void setContentSuffix(String suffix){
        mPreference.edit().putString(Constants.KEY_CONTENT_SUFFIX,suffix).apply();
    }

    public String getContentSuffix(){
        return mPreference.getString(Constants.KEY_CONTENT_SUFFIX,null);
    }

    public String getContentPrefix(){
        return mPreference.getString(Constants.KEY_CONTENT_PREFIX,null);
    }

}