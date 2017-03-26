package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.NativeDataManager;
import com.whf.messagerelayer.utils.SmsRelayerManager;

public class SmsService extends IntentService {

    private NativeDataManager mNativeDataManager;

    public SmsService(){
        super("SmsService");
    }

    public SmsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mNativeDataManager = new NativeDataManager(this);
        String content = intent.getStringExtra(Constant.EXTRA_MESSAGE_CONTENT);
        Log.e("::::::::::::::","service中:::::::::001");
        if (mNativeDataManager.getSmsRelay()) {
            SmsRelayerManager.relaySms(mNativeDataManager, content);
            Log.e("::::::::::::::","service中:::::::::002");
        }
        if (mNativeDataManager.getEmailRelay()) {
            EmailRelayerManager.relayEmail(mNativeDataManager, content);
        }
    }



}
