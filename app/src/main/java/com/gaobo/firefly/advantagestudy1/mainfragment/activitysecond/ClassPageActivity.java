package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment.ContactFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment.NewsFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment.NoticeFragment;

public class ClassPageActivity extends AppCompatActivity {
    private int value;
    private FrameLayout fl_class_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);

        fl_class_activity = ((FrameLayout) findViewById(R.id.fl_class_activity));

        Intent intent = getIntent();
        value  = intent.getIntExtra("value",0);
        disView();

    }
    public void disView(){
        Fragment fragment = null;
        switch (value) {
            case 1:
                fragment = new NewsFragment();
                break;
            case 2:
                fragment  = new ContactFragment();
                break;
            case 3:
                fragment = new NoticeFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_class_activity,fragment,null).commit();
  //      getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new PerCenterFragment(),null).commit();
    }
}
