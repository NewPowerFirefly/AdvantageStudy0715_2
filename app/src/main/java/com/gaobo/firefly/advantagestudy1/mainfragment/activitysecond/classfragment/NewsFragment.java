package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;

/**
 * Created by gy on 2016/7/5.
 */
public class NewsFragment extends CommFragment {
    private View view;
    private ImageView newsBack;

    @Override
    public View initview(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_class_news,null);
        newsBack = ((ImageView) view.findViewById(R.id.iv_contact_news_back));
        return view;
    }

    @Override
    public void initdata() {
        newsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
