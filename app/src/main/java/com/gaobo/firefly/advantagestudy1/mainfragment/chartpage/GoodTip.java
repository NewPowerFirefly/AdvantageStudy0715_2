package com.gaobo.firefly.advantagestudy1.mainfragment.chartpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;


/**
 * Created by gy on 2016/6/24.
 */
public class GoodTip extends CommPage{

    public GoodTip(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.goodtip,null);


        return view;
    }

    @Override
    public void initdata() {

    }
}