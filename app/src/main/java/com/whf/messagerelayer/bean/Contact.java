package com.whf.messagerelayer.bean;

/**
 * Created by WHF on 2017/3/28.
 */
public class Contact {
    private String mContactId;
    private String mContactName;
    private String mContactNum;
    private boolean mSelected = false;

    public Contact(String contactId,String contactName, String contactNum) {
        this.mContactId = contactId;
        this.mContactName = contactName;
        this.mContactNum = contactNum;
    }

    public String getContactId(){
        return mContactId;
    }

    public String getContactName() {
        return mContactName;
    }

    public String getContactNum() {
        return mContactNum;
    }

    public void setSelected(Boolean b){
        this.mSelected = b;
    }

    public Boolean getSelected(){
        return mSelected;
    }


}
