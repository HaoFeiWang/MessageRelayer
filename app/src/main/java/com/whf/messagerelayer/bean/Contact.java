package com.whf.messagerelayer.bean;

/**
 * Created by WHF on 2017/3/28.
 */
public class Contact {
    private String mContactId;
    private String mContactName;
    private String mContactNum;
    private int mSelected = 0;

    public Contact() {

    }

    public Contact(String contactId, String contactName, String contactNum) {
        this.mContactId = contactId;
        this.mContactName = contactName;
        this.mContactNum = contactNum;
    }

    public String getContactId() {
        return mContactId;
    }

    public void setContactId(String contactId) {
        this.mContactId = contactId;
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        this.mContactName = contactName;
    }

    public String getContactNum() {
        return mContactNum;
    }

    public void setContactNum(String contactNum) {
        this.mContactNum = contactNum;
    }


    public int getSelected() {
        return mSelected;
    }

    public void setSelected(int selected) {
        this.mSelected = selected;
    }

}
