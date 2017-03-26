package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.NativeDataManager;
import com.whf.messagerelayer.utils.SmsRelayerManager;

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

        if (mNativeDataManager.getSmsRelay()) {
            SmsRelayerManager.relaySms(mNativeDataManager.getObjectMobile(), content);
        }else if(mNativeDataManager.getSmsProxyRelay()){
            //SmsRelayerManager.realySmsProxy();
        }
        if (mNativeDataManager.getEmailRelay()) {
            EmailRelayerManager.relayEmail(mNativeDataManager, content);
        }
    }



}
