package com.whf.messagerelayer.confing;

/**
 * Created by WHF on 2017/3/24.
 */

public class Constant {

    //Intent传值的Extra名
    public static final String EXTRA_MESSAGE_CONTENT = "content";
    public static final String EXTRA_MESSAGE_MOBILE = "mobile";

    //SharedPreference的文件名
    public static final String SETTING_FILE_NAME = "settingConf";


    //转发渠道配置
    public static final String KEY_RECEIVER = "is_receiver";
    public static final String KEY_RELAY_SMS = "is_sms_relay";
    public static final String KEY_RELAY_EMAIL = "is_email_relay";


    //手机配置
    public static final String KEY_OBJECT_MOBILE = "mobile";


    //邮箱配置
    public static final String KEY_EMAIL_SERVICER = "email_servicer";
    public static final String KEY_EMAIL_HOST = "host";
    public static final String KEY_EMAIL_PORT = "port";
    public static final String KEY_EMAIL_SSL = "ssl";
    public static final String KEY_EMAIL_ACCOUNT = "account";
    public static final String KEY_EMAIL_PASSWORD = "password";
    public static final String KEY_EMAIL_TO_ACCOUNT = "to_account";
    public static final String KEY_EMAIL_SENDER_NAME = "sender_name";
    public static final String KEY_EMAIL_SUBJECT = "subject";


    //邮箱名称
    public static final String EMAIL_SERVICER_QQ = "QQ邮箱";
    public static final String EMAIL_SERVICER_163 = "163邮箱";
    public static final String EMAIL_SERVICER_126 = "126邮箱";
    public static final String EMAIL_SERVICER_OUTLOOK = "OutLook";
    public static final String EMAIL_SERVICER_GMAIL = "Gmail";
    public static final String EMAIL_SERVICER_OTHER = "其他邮箱";


    public static final String DB_TABLE_NAME = "contact";
    public static final String DB_KEY_ID = "id";
    public static final String DB_KEY_NAME = "name";
    public static final String DB_KEY_MOBLIE = "mobile";
    public static final String DB_KEY_SELECTED = "selected";

    public static final String DB_CONTACT_FLAG = "contact_flag";

}
