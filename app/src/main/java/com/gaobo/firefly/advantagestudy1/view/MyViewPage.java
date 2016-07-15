package com.gaobo.firefly.advantagestudy1.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gy on 2016/6/23.
 */
public class MyViewPage extends ViewPager {

    private boolean enableTouch = false;
    public MyViewPage(Context context) {
        super(context);
    }

    public MyViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(enableTouch){
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(enableTouch){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }

    }
}
