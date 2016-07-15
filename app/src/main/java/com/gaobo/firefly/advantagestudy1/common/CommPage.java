package com.gaobo.firefly.advantagestudy1.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by gy on 2016/6/22.
 */
public abstract class CommPage {
    private Context context;
    private View root_view;

    public CommPage(Context context) {
        this.context = context;
        LayoutInflater inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.root_view = initview(inflater);
        initdata();
    }

    public Context getContext() {
        return context;
    }

    public View getRoot_view() {
        return root_view;
    }

    public abstract View initview(LayoutInflater inflater);
    public abstract void initdata();
}
