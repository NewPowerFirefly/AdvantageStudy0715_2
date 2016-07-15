package com.gaobo.firefly.advantagestudy1.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gy on 2016/7/13.
 */
public class NoticeCommentBean implements Serializable {

    private boolean success;
    private ArrayList<NoticeComment> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<NoticeComment> getData() {
        return data;
    }

    public void setData(ArrayList<NoticeComment> data) {
        this.data = data;
    }
}
