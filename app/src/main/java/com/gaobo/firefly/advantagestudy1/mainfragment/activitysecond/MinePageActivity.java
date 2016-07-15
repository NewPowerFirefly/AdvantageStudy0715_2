package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.CollectFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.HisScoreFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.PerCenterFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.PerSettingFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.ShopFragment;

public class MinePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page);
    //    FrameLayout fl_mineactivity = (FrameLayout) findViewById(R.id.fl_mineactivity);
        Intent intent = getIntent();
        int value = intent.getIntExtra("value",0);
        System.out.println("value========="+value);
 //       getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new Fragment(),null).commit();

        switch (value) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new PerCenterFragment(),null).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new HisScoreFragment(),null).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new CollectFragment(),null).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new ShopFragment(),null).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_mineactivity,new PerSettingFragment(),null).commit();
                break;
        }

    }
}
