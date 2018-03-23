package com.whf.messagerelayer.data.bean;

/**
 * 邮件
 * Created by WHF on 2017/3/25.
 */

public class EmailMessage {
    /**
     * 发送方账户
     */
    private String mSenderAccount;

    /**
     * 接受方账户
     */
    private String mReceiverAccount;

    /**
     * 名称
     */
    private String mSenderName;

    /**
     * 主题
     */
    private String mSubject;

    /**
     * 内容
     */
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

    @Override
    public String toString() {
        return "EmailMessage{" +
                "mSenderAccount='" + mSenderAccount + '\'' +
                ", mReceiverAccount='" + mReceiverAccount + '\'' +
                ", mSenderName='" + mSenderName + '\'' +
                ", mSubject='" + mSubject + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
