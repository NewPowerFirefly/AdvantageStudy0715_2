package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.view.viewpage.CoursePage;
import com.gaobo.firefly.advantagestudy1.view.viewpage.NoticePage;

import java.util.ArrayList;

/**
 * Created by gy on 2016/7/5.
 */
public class NoticeFragment extends CommFragment {
    private View view;
    private ImageView noticeBack;
    private Button btNotice;
    private Button btCourse;
    private ViewPager vp_notice;
    private ArrayList<CommPage> pages;
    private Integer currentPage = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    btNotice.setBackgroundColor(getResources().getColor(R.color.buttonChecked));
                    btCourse.setBackgroundColor(getResources().getColor(R.color.buttonUnChecked));
                    vp_notice.setCurrentItem(0);
                    break;
                case 1:
                    btCourse.setBackgroundColor(getResources().getColor(R.color.buttonChecked));
                    btNotice.setBackgroundColor(getResources().getColor(R.color.buttonUnChecked));
                    vp_notice.setCurrentItem(1);
                    break;
            }

        }
    };

    @Override
    public View initview(LayoutInflater inflater) {

        view = inflater.inflate(R.layout.fragment_class_notice, null);
        noticeBack = ((ImageView) view.findViewById(R.id.iv_contact_notice_back));
        btNotice = ((Button) view.findViewById(R.id.bt_class_notice));
        btCourse = ((Button) view.findViewById(R.id.bt_class_timetable));
        vp_notice = ((ViewPager) view.findViewById(R.id.vp_notice));
        vp_notice.setCurrentItem(currentPage);
        btNotice.setBackgroundColor(getResources().getColor(R.color.buttonChecked));
        btCourse.setBackgroundColor(getResources().getColor(R.color.buttonUnChecked));
        return view;
    }

    @Override
    public void initdata() {
        pages = new ArrayList<CommPage>();
        pages.add(new NoticePage(getContext()));
        pages.add(new CoursePage(getContext()));
        noticeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        btNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                Message msg = handler.obtainMessage();
                msg.what = currentPage;
                handler.sendMessage(msg);
            }
        });
        btCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 1;
                Message msg = handler.obtainMessage();
                msg.what = currentPage;
                handler.sendMessage(msg);
            }
        });

        vp_notice.setAdapter(new MyPageAdapter());
    }

    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = pages.get(position).getRoot_view();
            Message msg = handler.obtainMessage();
            System.out.println("position===="+position);
            msg.what = position;
            handler.sendMessage(msg);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //           super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
