package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;

/**
 * Created by gy on 2016/6/29.
 */
public class ShopFragment extends CommFragment {
    private ImageView shop_back;

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.activity_mine_page_wjsc,null);
        shop_back = ((ImageView) view.findViewById(R.id.iv_shop));

        return view;
    }

    @Override
    public void initdata() {
        shop_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
