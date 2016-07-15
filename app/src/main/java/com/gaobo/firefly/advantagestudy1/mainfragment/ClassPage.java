package com.gaobo.firefly.advantagestudy1.mainfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.ClassPageActivity;


/**
 * Created by gy on 2016/6/24.
 */
public class ClassPage extends CommPage {

    private RadioGroup rg_class;
    private FrameLayout fl_class;
    private ListView lv_class_main;
    private RadioButton rb_news;
    private RadioButton rb_contact;
    private RadioButton rb_notice;

    public ClassPage(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.class_main_page,null);
        rg_class = ((RadioGroup) view.findViewById(R.id.rg_class));
        lv_class_main = ((ListView) view.findViewById(R.id.lv_class_main));
        rb_news = ((RadioButton) view.findViewById(R.id.rb_class_mes));
        rb_contact = ((RadioButton) view.findViewById(R.id.rb_class_con));
        rb_notice = ((RadioButton) view.findViewById(R.id.rb_class_notice));

        return view;
    }

    @Override
    public void initdata() {
        //此处获取消息的内容，进行展示
        lv_class_main.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        });



        rg_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent = new Intent(getContext(), ClassPageActivity.class);
                switch (checkedId) {
                    case R.id.rb_class_mes:
                        intent.putExtra("value",1);
                        rb_news.setChecked(false);
                        break;
                    case R.id.rb_class_con:
                        intent.putExtra("value",2);
                        rb_contact.setChecked(false);
                        break;
                    case R.id.rb_class_notice:
                        intent.putExtra("value",3);
                        rb_notice.setChecked(false);
                        break;
                }
                getContext().startActivity(intent);
            }
        });



    }


}