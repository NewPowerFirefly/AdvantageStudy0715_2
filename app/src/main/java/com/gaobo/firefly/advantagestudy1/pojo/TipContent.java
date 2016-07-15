package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gy on 2016/7/4.
 */
public class TipContent implements Serializable {
    private Integer id;
    private String youNumber;
    private String photo;
    private String petName;
    private String content;
    private Date date;

    public TipContent(String petName, String content, Date date) {
        this.petName = petName;
        this.content = content;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TipContent() {
    }

    public TipContent(Integer id, String youNumber, String photo, String petName, String content) {
        this.id = id;
        this.youNumber = youNumber;
        this.photo = photo;
        this.petName = petName;
        this.content = content;
    }

    public TipContent(Integer id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
