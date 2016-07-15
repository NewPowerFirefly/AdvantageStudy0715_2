package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

/**
 * Created by gy on 2016/6/29.
 */
public class PersonInfo implements Serializable {
    private Integer id;
    private String portrait;
    private String realName;
    private String phoneNumber;
    private String youNumber;
    private String petName;
    private String cardCord;
    private String motto;
    private String token;
    private String psw;

    public PersonInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PersonInfo(Integer id, String portrait, String realName,
                      String phoneNumber, String youNumber, String petName,
                      String cardCord, String motto, String token, String psw) {
        super();
        this.id = id;
        this.portrait = portrait;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.youNumber = youNumber;
        this.petName = petName;
        this.cardCord = cardCord;
        this.motto = motto;
        this.token = token;
        this.psw = psw;
    }

    public PersonInfo(String portrait, String realName, String phoneNumber,
                      String youNumber, String petName, String cardCord, String motto,
                      String token) {
        super();
        this.portrait = portrait;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.youNumber = youNumber;
        this.petName = petName;
        this.cardCord = cardCord;
        this.motto = motto;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getYouNumber() {
        return youNumber;
    }

    public void setYouNumber(String youNumber) {
        this.youNumber = youNumber;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getCardCord() {
        return cardCord;
    }

    public void setCardCord(String cardCord) {
        this.cardCord = cardCord;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "PersonInfo [cardCord=" + cardCord + ", id=" + id + ", motto="
                + motto + ", petName=" + petName + ", phoneNumber="
                + phoneNumber + ", portrait=" + portrait + ", psw=" + psw
                + ", realName=" + realName + ", token=" + token
                + ", youNumber=" + youNumber + "]";
    }

}
