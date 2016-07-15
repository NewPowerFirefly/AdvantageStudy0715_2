package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;

/**
 * Created by gy on 2016/6/27.
 */
public class User implements Serializable{

    private Integer userId;
    private String userName;
    private String userPsw;
    private boolean userType;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, Integer userId, String userPsw) {
        this.userName = userName;
        this.userId = userId;
        this.userPsw = userPsw;
    }

    public User(String userName, String userPsw, boolean userType) {
        super();
        this.userName = userName;
        this.userPsw = userPsw;
        this.userType = userType;
    }

    public User(String userName, String userPsw) {
        super();
        this.userName = userName;
        this.userPsw = userPsw;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(Integer userId, String userName, String userPsw,
                boolean userType) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPsw = userPsw;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName
                + ", userPsw=" + userPsw + ", userType=" + userType + "]";
    }

}

