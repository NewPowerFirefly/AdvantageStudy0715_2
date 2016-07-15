package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;
import com.gaobo.firefly.advantagestudy1.pojo.UserInfo;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by gy on 2016/6/29.
 */
public class PerCenterFragment extends CommFragment {
    private ImageView headPic;
    private TextView petName;
    private TextView youNumber;
    private ImageView cardCode;
    private TextView qm;
    private TextView realname;
    private TextView phonenumber;
    private ImageView back;
    private RelativeLayout rl_qm_headpic;
    private RelativeLayout rl_qm_petname;
    private RelativeLayout rl_qm_youhao;
    private RelativeLayout rl_qm_realname;
    private RelativeLayout rl_qm_qm;
    private RelativeLayout rl_qm_cardcode;
    private RelativeLayout rl_qm_phoneNum;
    UserInfo userInfo;

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.activity_mine_page_qm, null);

        Intent intent = getActivity().getIntent();
        userInfo = (UserInfo) intent.getExtras().get("userInfo");
  //      System.out.println(userInfo + "得到个人中心数据···");
        findId(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        update();
        System.out.println("PerCenterFragment进入到onStart");
//        Intent intent =getActivity().getIntent();
    }

    @Override
    public void initdata() {

        realname.setText(userInfo.getRealName());
        phonenumber.setText(userInfo.getPhoneNumber());
        petName.setText(userInfo.getPetName());
        youNumber.setText(userInfo.getYouNumber());
        qm.setText(userInfo.getMotto());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        /**
         *      点击，修改昵称跳转到输入页面
         */
        final Intent intent = new Intent(getContext(), ModPersonInfo.class);
        intent.putExtra("youNumber", userInfo.getYouNumber());

        rl_qm_headpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 1);
                System.out.println("调用相册，选取图片···实现待定···");
                startActivity(intent);

            }
        });
        rl_qm_realname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 2);
                intent.putExtra("realName", userInfo.getRealName());
                startActivity(intent);
            }
        });
        rl_qm_phoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 3);
                intent.putExtra("phoneNumber", userInfo.getPhoneNumber());
                startActivity(intent);
            }
        });
        rl_qm_petname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 4);
                intent.putExtra("petName", userInfo.getPetName());
                startActivity(intent);
            }
        });
        rl_qm_youhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 5);
                intent.putExtra("youNumber", userInfo.getYouNumber());
                startActivity(intent);
            }
        });
        rl_qm_cardcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 6);
                intent.putExtra("cardCode", userInfo.getCardCode());
                startActivity(intent);
            }
        });
        rl_qm_qm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value", 7);
                intent.putExtra("motto", userInfo.getMotto());
                startActivity(intent);
            }
        });


    }

    public void findId(View view) {

        back = ((ImageView) view.findViewById(R.id.iv_qm_back));
        headPic = ((ImageView) view.findViewById(R.id.iv_qm_headpic));
        petName = ((TextView) view.findViewById(R.id.tv_qm_petname));
        youNumber = ((TextView) view.findViewById(R.id.tv_qm_younumber));
        cardCode = ((ImageView) view.findViewById(R.id.iv_qm_cardcode));
        qm = ((TextView) view.findViewById(R.id.tv_qm_qm));
        realname = ((TextView) view.findViewById(R.id.tv_qm_realname));
        phonenumber = ((TextView) view.findViewById(R.id.tv_qm_phonenumber));

        rl_qm_headpic = ((RelativeLayout) view.findViewById(R.id.rl_qm_headpic));
        rl_qm_petname = ((RelativeLayout) view.findViewById(R.id.rl_qm_petname));
        rl_qm_youhao = ((RelativeLayout) view.findViewById(R.id.rl_qm_youhao));
        rl_qm_cardcode = ((RelativeLayout) view.findViewById(R.id.rl_qm_cardcode));
        rl_qm_qm = ((RelativeLayout) view.findViewById(R.id.rl_qm_qm));
        rl_qm_realname = ((RelativeLayout) view.findViewById(R.id.rl_qm_realname));
        rl_qm_phoneNum = ((RelativeLayout) view.findViewById(R.id.rl_qm_phoneNum));

    }

    public void update() {
        HttpUtils conn = new HttpUtils();
        String num = "121212";
        conn.send(HttpRequest.HttpMethod.GET, "http://192.168.23.1:8080/Android1/CheckUserInfo?youNumber=" + num, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("访问网路成功");
                Gson gson = new Gson();
                UserInfo info = (UserInfo)gson.fromJson(responseInfo.result, UserInfo.class);
                System.out.println(info.getPetName()+info.getYouNumber()+info.getMotto()+info.getRealName()+info.getPhoneNumber());
                System.out.println("此处设置头像处理update");
                petName.setText(info.getPetName().toString());
                youNumber.setText(info.getYouNumber().toString());
                qm.setText(info.getMotto().toString());
                realname.setText(info.getRealName().toString());
                phonenumber.setText(info.getPhoneNumber().toString());
                System.out.println("此处二维码");
 //               System.out.println(info.getPetName() + info.getHeadPicture() + info.getYouNumber() + info.getMotto());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("访问网路失败");
            }
        });
    }
}
