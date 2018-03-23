package com.whf.messagerelayer.data;

/**
 * 常量
 * Created by WHF on 2017/3/24.
 */

public interface Constants {

    String TAG = "Relay_Log";

    //Intent传值的Extra名
    String EXTRA_MESSAGE_CONTENT = "content";
    String EXTRA_MESSAGE_MOBILE = "mobile";
    String EXTRA_DATA_CHANGE = "updata";

    //SharedPreference的文件名
    String SETTING_FILE_NAME = "settingConf";


    //转发渠道配置
    String KEY_RECEIVER = "is_receiver";
    String KEY_RELAY_SMS = "is_sms_relay";
    String KEY_RELAY_EMAIL = "is_email_relay";


    //手机配置
    String KEY_OBJECT_MOBILE = "mobile";


    //邮箱配置
    String KEY_EMAIL_SERVICER = "email_servicer";
    String KEY_EMAIL_HOST = "host";
    String KEY_EMAIL_PORT = "port";
    String KEY_EMAIL_SSL = "ssl";
    String KEY_EMAIL_ACCOUNT = "account";
    String KEY_EMAIL_PASSWORD = "password";
    String KEY_EMAIL_TO_ACCOUNT = "to_account";
    String KEY_EMAIL_SENDER_NAME = "sender_name";
    String KEY_EMAIL_SUBJECT = "subject";


    //邮箱名称
    String EMAIL_SERVICER_QQ = "QQ邮箱";
    String EMAIL_SERVICER_163 = "163邮箱";
    String EMAIL_SERVICER_126 = "126邮箱";
    String EMAIL_SERVICER_OUTLOOK = "OutLook";
    String EMAIL_SERVICER_GMAIL = "Gmail";
    String EMAIL_SERVICER_OTHER = "其他邮箱";


    //数据库配置
    String DB_TABLE_NAME = "contact";
    String DB_KEY_ID = "id";
    String DB_KEY_NAME = "name";
    String DB_KEY_MOBLIE = "mobile";
    String DB_KEY_SELECTED = "selected";


    //关键字的列表
    String KEY_KEYWORD_LIST = "keyword";

    //前缀和后缀
    String KEY_CONTENT_PREFIX = "prefix";
    String KEY_CONTENT_SUFFIX = "suffix";
}
