package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gy on 2016/7/14.
 */
public class NoticeGoodBean implements Serializable {

    private boolean success;
    private ArrayList<NoticeGood> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<NoticeGood> getData() {
        return data;
    }

    public void setData(ArrayList<NoticeGood> data) {
        this.data = data;
    }
}
