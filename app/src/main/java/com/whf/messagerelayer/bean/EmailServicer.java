package com.whf.messagerelayer.bean;

/**
 * Created by WHF on 2017/3/24.
 */

public class EmailServicer {
    private String mUrl;
    private String mPort;
    private boolean mSsl;

    public EmailServicer(){

    }

    public EmailServicer(String url, String port, boolean ssl) {
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
