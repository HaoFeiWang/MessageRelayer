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
        mPreference.edit().putBoolean(Constant.KEY_RELAY_SMS, b).apply();

    }

    public Boolean getSmsRelay(){
        return mPreference.getBoolean(Constant.KEY_RELAY_SMS, false);
    }

    public void setSmsProxyRelay(Boolean b){
        mPreference.edit().putBoolean(Constant.KEY_RELAY_SMS_PROXY, b).apply();

    }

    public boolean getSmsProxyRelay(){
        return mPreference.getBoolean(Constant.KEY_RELAY_SMS_PROXY, false);
    }

    public void setEmailRelay(Boolean b){
        mPreference.edit().putBoolean(Constant.KEY_RELAY_EMAIL, b).apply();
    }

    public boolean getEmailRelay(){
        return mPreference.getBoolean(Constant.KEY_RELAY_EMAIL, false);
    }

    public void setReceiver(Boolean b){
        mPreference.edit().putBoolean(Constant.KEY_RECEIVER, b).apply();
    }

    public boolean getReceiver(){
        return mPreference.getBoolean(Constant.KEY_RECEIVER, false);
    }

    public String getEmailServicer(){
        return mPreference.getString(Constant.KEY_EMAIL_SERVICER,Constant.EMAIL_SERVICER_QQ);
    }
    public void setEmailServicer(String servicer){

    }
}