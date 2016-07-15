package com.gaobo.firefly.advantagestudy1;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.ChartPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.ClassPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.HomeWorkPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.MinePage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_home;
    private RadioGroup radioGroup;
    private ArrayList<CommPage> pageList;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp_home = ((ViewPager) findViewById(R.id.vp_home));
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        setPage();


    }
    public void setPage(){
        pageList = new ArrayList<CommPage>();
        pageList.add(new HomeWorkPage(this));
        pageList.add(new ChartPage(this));
        pageList.add(new ClassPage(this));
        pageList.add(new MinePage(this));

///        getSupportFragmentManager().beginTransaction().replace(R.id.)
        System.out.println(pageList.size()+"---------------");
        vp_home.setAdapter(new MyPageAdapter());
        vp_home.setCurrentItem(currentPage);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_homework:
                        currentPage = 0;
                        break;
                    case R.id.rb_chat:
                        currentPage = 1;
                        break;
                    case R.id.rb_class:
                        currentPage = 2;
                        break;
                    case R.id.rb_mine:
                        currentPage = 3;
                        break;
                }
                vp_home.setCurrentItem(currentPage);
            }
        });

    }
    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = pageList.get(position).getRoot_view();
            container.addView(view);
            return  view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View)object);
        }
    }


}
