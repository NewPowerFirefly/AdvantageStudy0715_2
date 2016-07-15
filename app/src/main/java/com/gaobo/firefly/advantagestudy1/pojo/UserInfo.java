package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

/**
 * Created by gy on 2016/6/29.
 */
public class UserInfo implements Serializable {
    private Integer id;
    private String youNumber;
    private String realName;
    private String headPicture;
    private String cardCode;
    private String motto;
    private String phoneNumber;
    private String petName;

    public UserInfo(){

    }

    public UserInfo(Integer id, String youNumber, String realName, String headPicture, String cardCode, String motto, String phoneNumber, String petName) {
        this.id = id;
        this.youNumber = youNumber;
        this.realName = realName;
        this.headPicture = headPicture;
        this.cardCode = cardCode;
        this.motto = motto;
        this.phoneNumber = phoneNumber;
        this.petName = petName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYouNumber() {
        return youNumber;
    }

    public void setYouNumber(String youNumber) {
        this.youNumber = youNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", youNumber='" + youNumber + '\'' +
                ", realName='" + realName + '\'' +
                ", headPicture='" + headPicture + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", motto='" + motto + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", petName='" + petName + '\'' +
                '}';
    }
}
