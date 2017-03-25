package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

import com.whf.messagerelayer.bean.ServicerEnum;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.NativeDataManager;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

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
            relaySms(mNativeDataManager.getObjectMobile(), content);
        }
        if (mNativeDataManager.getEmailRelay()) {
            relayEmail(mNativeDataManager.getEmailServicer(),content);
        }
    }

    //发送短信至目标邮件
    private void relayEmail(String emailServicer,String content) {
        Properties props = new Properties();
        switch (emailServicer) {
            case Constant.EMAIL_SERVICER_QQ:
                initProperties(props, ServicerEnum.EMAIL_QQ.getUrl(), ServicerEnum.EMAIL_QQ.getPort());
                break;
            case Constant.EMAIL_SERVICER_163:
                initProperties(props, ServicerEnum.EMAIL_163.getUrl(), ServicerEnum.EMAIL_163.getPort());
                break;
            case Constant.EMAIL_SERVICER_126:
                initProperties(props, ServicerEnum.EMAIL_126.getUrl(), ServicerEnum.EMAIL_126.getPort());
                break;
            case Constant.EMAIL_SERVICER_OUTLOOK:
                initProperties(props, ServicerEnum.EMAIL_OUTLOOK.getUrl(), ServicerEnum.EMAIL_OUTLOOK.getPort());
                break;
            case Constant.EMAIL_SERVICER_GMAIL:
                initProperties(props, ServicerEnum.EMAIL_GMAIL.getUrl(), ServicerEnum.EMAIL_GMAIL.getPort());
                break;
            case Constant.EMAIL_SERVICER_OTHER:
                initProperties(props, mNativeDataManager.getEmailAddress(), mNativeDataManager.getEmailPort());
                break;
        }

        props.put("mail.smpt.auth", true);
        props.put("mail.transport.protocol", "smpt");
        props.put("mail.smpt.user", mNativeDataManager.getEmailAccount());
        props.put("mail.smpt.password", mNativeDataManager.getEmailPort());

        Session session = Session.getDefaultInstance(props, new MyAuthenticator());

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(mNativeDataManager.getEmailAccount());//发件人
            message.setRecipients(MimeMessage.RecipientType.TO,mNativeDataManager.getEmailToAccout());
            message.setSubject("短信转发");//主题
            message.setContent(content,"text/html;charset=UTF-8");
            message.saveChanges();
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private void initProperties(Properties props, String address, String port) {
        props.put("mail.smpt.host", address);
        props.put("mail.smpt.port", port);
    }

    /**
     * 发送短信至目标手机号
     *
     * @param objectMobile 目标手机号
     * @param content      短信内容
     */
    private void relaySms(String objectMobile, String content) {
        if (objectMobile != null) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(objectMobile, null, content, null, null);
        }
    }

    class MyAuthenticator extends Authenticator {

    }
}
