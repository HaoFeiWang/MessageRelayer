package com.whf.messagerelayer.data.bean;

/**
 * Created by WHF on 2017/3/28.
 */
public class Contact {
    private String mContactName;
    private String mContactNum;
    //仅用于图标的显示
    private int mSelected = 0;

    public Contact() {

    }

    public Contact( String contactName, String contactNum) {
        this.mContactName = contactName;
        this.mContactNum = contactNum;
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
