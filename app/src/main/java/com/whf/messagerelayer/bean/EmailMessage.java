package com.whf.messagerelayer.bean;

/**
 * Created by WHF on 2017/3/25.
 */

public class EmailMessage {
    private String mSenderAccount;
    private String mReceiverAccount;
    private String mSenderName;
    private String mSubject;
    private String mContent;

    public EmailMessage() {
    }

    public EmailMessage(String senderAccount, String receiverAccount
            , String senderName, String subject, String content) {
        this.mSenderAccount = senderAccount;
        this.mReceiverAccount = receiverAccount;
        this.mSenderName = senderName;
        this.mSubject = subject;
        this.mContent = content;
    }

    public String getSenderAccount() {
        return mSenderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.mSenderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return mReceiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.mReceiverAccount = receiverAccount;
    }

    public String getSenderName() {
        return mSenderName;
    }

    public void setSenderName(String senderName) {
        this.mSenderName = senderName;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }
}
