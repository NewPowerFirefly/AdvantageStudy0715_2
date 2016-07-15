package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;

/**
 * Created by gy on 2016/7/5.
 */
public class ContactFragment extends CommFragment {
    private View view;
    private ImageView contact_back;
    private ImageView addContact;

    @Override
    public View initview(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_class_contact,null);
        contact_back = ((ImageView) view.findViewById(R.id.iv_contact_back));
        addContact = ((ImageView) view.findViewById(R.id.iv_contact_add));
        return view;
    }

    @Override
    public void initdata() {
        contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
