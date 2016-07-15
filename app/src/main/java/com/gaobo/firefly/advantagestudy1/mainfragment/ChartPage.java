package com.gaobo.firefly.advantagestudy1.mainfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.autoscroll.AutoScrollViewPager;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.chartpage.AskQuestion;
import com.gaobo.firefly.advantagestudy1.mainfragment.chartpage.GoodTip;
import com.gaobo.firefly.advantagestudy1.mainfragment.chartpage.ParentCommunication;
import com.gaobo.firefly.advantagestudy1.mainfragment.chartpage.TeacherCommunication;

import java.util.ArrayList;


/**
 * Created by gy on 2016/6/24.
 */
public class ChartPage extends CommPage {

    private RadioGroup rg_chart;
    private ViewPager vp_chart;
    private ArrayList<CommPage> pageArrayList;
    private int curPage = 0;

    public ChartPage(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.chart_main_page, null);
        rg_chart = ((RadioGroup) view.findViewById(R.id.rg_chart_homepage));
        vp_chart = ((ViewPager) view.findViewById(R.id.asvp_chart_homepage));


        pageArrayList = new ArrayList<CommPage>();
        pageArrayList.add(new AskQuestion(this.getContext()));
        pageArrayList.add(new GoodTip(this.getContext()));
        pageArrayList.add(new ParentCommunication(this.getContext()));
        pageArrayList.add(new TeacherCommunication(this.getContext()));
        vp_chart.setCurrentItem(curPage);
        vp_chart.setAdapter(new MyPageAdapter());
        rg_chart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_chart_tw:
                        curPage = 0;
                        break;
                    case R.id.rb_chart_jptz:
                        curPage = 1;
                        break;
                    case R.id.rb_chart_jzjl:
                        curPage = 2;
                        break;
                    case R.id.rb_chart_jsjl:
                        curPage = 3;
                        break;
                }
                vp_chart.setCurrentItem(curPage);
            }
        });


        return view;
    }

    @Override
    public void initdata() {

    }


   private class MyPageAdapter extends PagerAdapter{

       @Override
       public int getCount() {
           return pageArrayList.size();
       }

       @Override
       public boolean isViewFromObject(View view, Object object) {
           return view == object;
       }

       @Override
       public Object instantiateItem(ViewGroup container, int position) {

           View view = pageArrayList.get(position%4).getRoot_view();
           container.addView(view);
           return view;
       }

       @Override
       public void destroyItem(ViewGroup container, int position, Object object) {

           container.removeView((View)object);
       }
   }
}