package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;
import com.gaobo.firefly.advantagestudy1.pojo.DateUtil;
import com.gaobo.firefly.advantagestudy1.pojo.TipContent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gy on 2016/6/29.
 */
public class CollectFragment extends CommFragment {
    private ListView lv_collect;
    private ImageView collect_back;

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.activity_mine_page_wdsc, null);
        lv_collect = ((ListView) view.findViewById(R.id.lv_collect));
        collect_back = ((ImageView) view.findViewById(R.id.iv_collect_back));
        return view;
    }

    @Override
    public void initdata() {

        final ArrayList<TipContent> tips = new ArrayList<TipContent>();
        for (int i = 0; i < 40; i++) {
            TipContent tip = new TipContent("Only One", "温州位于浙江省东南部，瓯江下游南岸：瑞安市、乐清市温州位于浙江省东南部，瓯江下游南岸：瑞安市、乐清市温州位于浙江省东南部，瓯江下游南岸：瑞安市、乐清市", new Date(System.currentTimeMillis() + i * 10000000));
            tips.add(tip);
        }

        lv_collect.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return tips.size();
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
                View view = View.inflate(getContext(), R.layout.lv_collect, null);
                ImageView iv_photo = (ImageView) view.findViewById(R.id.iv_collect_photo);
                iv_photo.setImageResource(R.drawable.tou);

                TextView tv_petName = (TextView) view.findViewById(R.id.tv_collect_petName);
                tv_petName.setText(tips.get(position).getPetName());

                TextView tv_date = (TextView) view.findViewById(R.id.tv_collect_date);
                tv_date.setText(DateUtil.dateToString(tips.get(position).getDate()));

                TextView tv_content = (TextView) view.findViewById(R.id.tv_collect_content);
                tv_content.setText(tips.get(position).getContent());
                return view;
            }
        });


        collect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
