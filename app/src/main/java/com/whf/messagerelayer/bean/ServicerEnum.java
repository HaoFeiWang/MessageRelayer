package com.whf.messagerelayer.bean;

/**
 * Created by WHF on 2017/3/24.
 */

public enum ServicerEnum {

    EMAIL_QQ("smtp.qq.com","465",true),EMAIL_126("smtp.qq.com","465",true)
    ,EMAIL_163("smtp.qq.com","465",true),EMAIL_OUTLOOK("smtp.qq.com","465",true)
    ,EMAIL_GMAIL("smtp.qq.com","465",true);

    private String mUrl;
    private String mPort;
    private boolean mSsl;

    ServicerEnum(String url, String port, boolean ssl) {
        this.mUrl = url;
        this.mPort = port;
        this.mSsl = ssl;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        this.mPort = port;
    }

    public boolean isSsl() {
        return mSsl;
    }

    public void setSsl(boolean ssl) {
        this.mSsl = ssl;
    }
}
