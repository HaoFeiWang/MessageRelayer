package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.NativeDataManager;

public class SmsService extends IntentService {

    private NativeDataManager mNativeDataManager;
    public SmsService(String name) {
        super(name);
        mNativeDataManager = new NativeDataManager(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String content = intent.getStringExtra(Constant.EXTRA_MESSAGE_CONTENT);
        String mobile = intent.getStringExtra(Constant.EXTRA_MESSAGE_MOBILE);

        if(mNativeDataManager.getSmsRelay()){
            relaySms(mNativeDataManager.getObjectMobile(),content);
        }
        if(mNativeDataManager.getEmailRelay()){
            relayEmail();
        }
    }

    //发送短信至目标邮件
    private void relayEmail() {

    }

    /**
     * 发送短信至目标手机号
     * @param objectMobile 目标手机号
     * @param content 短信内容
     */
    private void relaySms(String objectMobile,String content){
        if(objectMobile!=null){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(objectMobile,null,content,null,null);
        }
    }
}
