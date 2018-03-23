package com.whf.messagerelayer.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.whf.messagerelayer.data.Constants;
import com.whf.messagerelayer.service.RelayService;
import com.whf.messagerelayer.utils.FormatMobile;
import com.whf.messagerelayer.utils.SharedPreferenceUtil;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(Constants.TAG, "Receiver Message!");

        if (SharedPreferenceUtil.getInstance(context).getReceiver()) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (int i = 0; pdus != null && i < pdus.length; i++) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    startSmsService(context, sms);
                }
            }
        }
    }

    private ComponentName startSmsService(Context context, SmsMessage sms) {
        String mobile = sms.getOriginatingAddress();//发送短信的手机号码

        if (FormatMobile.hasPrefix(mobile)) {
            mobile = FormatMobile.formatMobile(mobile);
        }
        String content = sms.getMessageBody();//短信内容

        Intent serviceIntent = new Intent(context, RelayService.class);
        serviceIntent.putExtra(Constants.EXTRA_MESSAGE_CONTENT, content);
        serviceIntent.putExtra(Constants.EXTRA_MESSAGE_MOBILE, mobile);
        return context.startService(serviceIntent);
    }


}
