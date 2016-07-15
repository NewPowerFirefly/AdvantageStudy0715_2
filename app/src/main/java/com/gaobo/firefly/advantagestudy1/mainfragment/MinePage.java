package com.gaobo.firefly.advantagestudy1.mainfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.MinePageActivity;
import com.gaobo.firefly.advantagestudy1.pojo.UserInfo;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by gy on 2016/6/24.
 */
public class MinePage extends CommPage {

    private RelativeLayout qm_box;
    private UserInfo userInfo = new UserInfo();
    private TextView tv_minepage_name;
    private TextView tv_minepage_usernumber;
    private TextView tv_minepage_signature;
    private String qm = "                 ";
    private RelativeLayout lscj_box;
    private RelativeLayout wdsc_box;
    private RelativeLayout wjsc_box;
    private RelativeLayout sz_box;
    public MinePage(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.mine_main_page,null);
        findId(view);
        qm_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MinePageActivity.class);
                intent.putExtra("value",1);
                intent.putExtra("userInfo",userInfo);
                getContext().startActivity(intent);
            }
        });

        lscj_box = (RelativeLayout) view.findViewById(R.id.rl_minepage_lscj);
        lscj_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MinePageActivity.class);
                intent.putExtra("value",2);
                intent.putExtra("userInfo",userInfo);
                getContext().startActivity(intent);
            }
        });

        wdsc_box = (RelativeLayout) view.findViewById(R.id.rl_minepage_wdsc);
        wdsc_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MinePageActivity.class);
                intent.putExtra("value",3);
                getContext().startActivity(intent);
            }
        });

        wjsc_box = (RelativeLayout) view.findViewById(R.id.rl_minepage_wjsc);
        wjsc_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MinePageActivity.class);
                intent.putExtra("value",4);
                getContext().startActivity(intent);
            }
        });

        sz_box = (RelativeLayout) view.findViewById(R.id.rl_minepage_sz);
        sz_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MinePageActivity.class);
                intent.putExtra("value",5);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initdata() {
        System.out.println("访问网路开始");
        HttpUtils conn = new HttpUtils();
        String youNumber = "121212";
        conn.send(HttpRequest.HttpMethod.GET, "http://192.168.23.1:8080/Android1/CheckUserInfo?youNumber=" + youNumber, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("访问网路成功");
                Gson gson = new Gson();
                userInfo = gson.fromJson(responseInfo.result,UserInfo.class);
                tv_minepage_name.setText(userInfo.getPetName());
                tv_minepage_usernumber.setText(userInfo.getYouNumber());
                tv_minepage_signature.setText(qm+userInfo.getMotto());
                System.out.println(userInfo.getPetName()+userInfo.getHeadPicture()+userInfo.getYouNumber()+userInfo.getMotto());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("访问网路失败");
            }
        });

    }

    public void findId(View view){
        tv_minepage_name = ((TextView) view.findViewById(R.id.tv_minepage_name));
        tv_minepage_usernumber = ((TextView) view.findViewById(R.id.tv_minepage_usernumber));
        tv_minepage_signature = ((TextView) view.findViewById(R.id.tv_minepage_signature));
        qm_box = ((RelativeLayout) view.findViewById(R.id.rl_minepage_qm));
    }
}