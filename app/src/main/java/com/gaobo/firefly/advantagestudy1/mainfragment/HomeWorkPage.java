package com.gaobo.firefly.advantagestudy1.mainfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;


/**
 * Created by gy on 2016/6/24.
 */
public class HomeWorkPage extends CommPage {
    private View view;

    public HomeWorkPage(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
       view =  inflater.inflate(R.layout.homework_main_page,null);
     //   view.findViewById(R.id.)
        return view;
    }

    @Override
    public void initdata() {

    }
}