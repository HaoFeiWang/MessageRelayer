package com.whf.messagerelayer.utils;

import com.whf.messagerelayer.bean.EmailMessage;
import com.whf.messagerelayer.confing.Constant;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by WHF on 2017/3/25.
 */

public class EmailManager {

    public static final int CODE_SUCCESS = 0x1;
    public static final int CODE_FAILE = 0x0;

    private static final String PORT_SSL_QQ = "465";
    private static final String PORT_SSL_163 = "465";
    private static final String PORT_SSL_126 = "465";
    private static final String PORT_SSL_GMAIL = "465";
    private static final String PORT_SSL_OUTLOOK = "465";

    private static final String HOST_QQ = "smtp.qq.com";
    private static final String HOST_163 = "smtp.163.com";
    private static final String HOST_126 = "smtp.126.com";
    private static final String HOST_GMAIL = "smtp.gmail.com";
    private static final String HOST_OUTLOOK = "smtp.outlook.com";

    //发送短信至目标邮件
    public static int relayEmail(NativeDataManager dataManager,String content) {
        Properties props = new Properties();
        User user = getSenderUser(dataManager);
        EmailMessage  emailMessage = creatEmailMessage(content,dataManager);
        setHost(dataManager,props);
        setSenderToPro(props,user);
        props.put("mail.smtp.auth", true);//如果不设置，则报553错误
        props.put("mail.transport.protocol", "smtp");

        //getDefaultInstace得到的始终是该方法初次创建的缺省的对象，getInstace每次获取新对象
        Session session = Session.getInstance(props
                ,new SmtpAuthenticator(user));
        session.setDebug(true);

        try {
            MimeMessage message = creatMessage(session,emailMessage);
            Transport.send(message);
            return CODE_SUCCESS;
        } catch (MessagingException e) {
            e.printStackTrace();
            return CODE_FAILE;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return CODE_FAILE;
        }
    }

    /**
     * 创建邮件消息对象
     * @param session
     * @param emailMessage
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    private static MimeMessage creatMessage(Session session, EmailMessage emailMessage)
            throws UnsupportedEncodingException, MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailMessage.getSenderAccount()
                ,emailMessage.getSenderName(),"UTF-8"));//发件人
        message.setRecipients(MimeMessage.RecipientType.TO,emailMessage.getReceiverAccount());//收件人
        message.setSubject(emailMessage.getSubject());//主题
        message.setContent(emailMessage.getContent(),"text/html;charset=UTF-8");
        return message;
    }
    /**
     * SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果
     * 开启了 SSL 连接,需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应
     * 邮箱服务的帮助,QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
     * @param props
     * @param smtpPort
     * @return
     */
    private static void setSslMode(Properties props, String smtpPort) {
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
    }

    /**
     * 从本地数据获取发送方账号和密码
     * @param dataManager
     * @return
     */
    private static User getSenderUser(NativeDataManager dataManager){
        return new User(dataManager.getEmailAccount(),dataManager.getEmailPassword());
    }

    /**
     * 将发送发的账号和密码设置给配置文件
     * @param properties
     * @param user
     * @return
     */
    private static void setSenderToPro(Properties properties, User user){
        properties.put("mail.smtp.username", user.account);
        properties.put("mail.smtp.password",user.password);
    }

    /**
     * 设置主机
     * @param dataManager
     * @param props
     * @return
     */
    private static void setHost(NativeDataManager dataManager, Properties props) {
        boolean ssl = dataManager.getEmailSsl();
        switch (dataManager.getEmailServicer()) {
            case Constant.EMAIL_SERVICER_QQ:
                props.put("mail.smtp.host", HOST_QQ);
                if(ssl) {
                    setSslMode(props,PORT_SSL_QQ);
                }
                break;
            case Constant.EMAIL_SERVICER_163:
                props.put("mail.smtp.host", HOST_163);
                if(ssl) {
                    setSslMode(props,PORT_SSL_163);
                }
                break;
            case Constant.EMAIL_SERVICER_126:
                props.put("mail.smtp.host", HOST_126);
                if(ssl) {
                    setSslMode(props,PORT_SSL_126);
                }
                break;
            case Constant.EMAIL_SERVICER_OUTLOOK:
                props.put("mail.smtp.host", HOST_OUTLOOK);
                if(ssl) {
                    setSslMode(props,PORT_SSL_OUTLOOK);
                }
                break;
            case Constant.EMAIL_SERVICER_GMAIL:
                props.put("mail.smtp.host", HOST_GMAIL);
                if(ssl) {
                    setSslMode(props,PORT_SSL_GMAIL);
                }
                break;
            case Constant.EMAIL_SERVICER_OTHER:
                props.put("mail.smtp.host", dataManager.getEmailHost());
                if(ssl) {
                    setSslMode(props,dataManager.getEmailPort());
                }
                break;
        }
    }


    /**
     * 登录认证
     */
    //uxdwujyofvqqcabd
    private static class SmtpAuthenticator extends Authenticator {
        String mUsername ;
        String mPassword ;
        public SmtpAuthenticator(User user) {
            super();
            this.mUsername = user.account;
            this.mPassword = user.password;
        }
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            if ((mUsername != null) && (mUsername.length() > 0) && (mPassword != null)
                    && (mPassword.length() > 0)) {
                return new PasswordAuthentication(mUsername, mPassword);
            }
            return null;
        }
    }

    /**
     * 发送方账户密码实体类
     */
    private static class User{
        String account;
        String password;
        User(String account,String password){
            this.account = account;
            this.password = password;
        }
    }

    /**
     * 封装消息实体
     * @param content
     * @param dataManager
     * @return
     */
    private static EmailMessage creatEmailMessage(String content,NativeDataManager dataManager){
        EmailMessage message = new EmailMessage();
        message.setContent(content);
        message.setSenderAccount(dataManager.getEmailAccount());
        message.setSenderName(dataManager.getEmailSenderName());
        message.setReceiverAccount(dataManager.getEmailToAccount());
        message.setSubject(dataManager.getEmailSubject());
        return message;
    }
}
