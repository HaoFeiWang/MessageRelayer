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
        return mPreference.getString(Constant.KEY_OBJECT_MOBILE, null);
    }

    public void setObjectMobile(String mobile) {
        mPreference.edit().putString(Constant.KEY_OBJECT_MOBILE, mobile).apply();
    }

    public void setSmsRelay(Boolean b) {
        mPreference.edit().putBoolean(Constant.KEY_SMS_RELAY, b).apply();

    }

    public Boolean getSmsRelay(){
        return mPreference.getBoolean(Constant.KEY_SMS_RELAY, false);
    }

    public void setSmsProxyRelay(Boolean b){
        mPreference.edit().putBoolean(Constant.KEY_SMS_PROXY_RELAY, b).apply();

    }

    public boolean getSmsProxyRelay(){
        return mPreference.getBoolean(Constant.KEY_SMS_PROXY_RELAY, false);
    }

    public void setEmailRelay(Boolean b){
        mPreference.edit().putBoolean(Constant.KEY_EMAIL_RELAY, b).apply();
    }

    public boolean getEmailRelay(){
        return mPreference.getBoolean(Constant.KEY_EMAIL_RELAY, false);
    }
}