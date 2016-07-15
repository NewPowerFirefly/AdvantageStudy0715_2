package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaobo.firefly.advantagestudy1.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class ModPersonInfo extends AppCompatActivity {

    private TextView qm_petName;
    private TextView tv_mod_save;
    private ImageView tv_mod_back;
    private EditText et_mod_info;
    private Button save;
    private String youNumber;
    private String name;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_person_info);
        findId();
        tv_mod_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_mod_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = et_mod_info.getText().toString();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = et_mod_info.getText().toString();
                saveData(youNumber,name,value);
               /*   Intent intent = new Intent();
              Bundle bundle = new Bundle();

                bundle.putString("key",name);
                bundle.putString("value",et_mod_info.getText().toString());
                intent.putExtra("data",bundle);

                intent.putExtra("key",name);
                intent.putExtra("value",et_mod_info.getText().toString());
                startActivity(intent);*/
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_mod_save.setTextSize(25);
        Intent intent = getIntent();
        //    UserInfo info = (UserInfo)getIntent().getSerializableExtra("userInfo");

        youNumber = intent.getStringExtra("youNumber");
        int valueIntent = intent.getIntExtra("value", 0);

        switch (valueIntent) {

            case 1:
                tv_mod_save.setText("头像");
                name = "headPicture";
                //              et_mod_info.setText(intent.getStringExtra("headPicture"));

                //               saveData(info.getYouNumber(),info.getHeadPicture(),save.getText().toString());
                break;
            case 2:
                name = "realName";
                tv_mod_save.setText("真实姓名");
                et_mod_info.setText(intent.getStringExtra("realName"));
                //               saveData(info.getYouNumber(),info.getRealName(),save.getText().toString());
                break;
            case 3:
                name = "phoneNumber";
                tv_mod_save.setText("手机号");
                et_mod_info.setText(intent.getStringExtra("phoneNumber"));
                //               saveData(info.getYouNumber(),info.getPhoneNumber(),save.getText().toString());
                break;
            case 4:
                name = "petName";
                tv_mod_save.setText("昵称");
                et_mod_info.setText(intent.getStringExtra("petName"));
                //               saveData(info.getYouNumber(),info.getPetName(),save.getText().toString());
                break;
            case 5:
                name = "youNumber";
                tv_mod_save.setText("优号");
                et_mod_info.setText(intent.getStringExtra("youNumber"));
                //             saveData(info.getYouNumber(),info.getYouNumber(),save.getText().toString());
                break;
            case 6:
                name = "cardCode";
                tv_mod_save.setText("二维码名片");
                et_mod_info.setText(intent.getStringExtra("cardCode"));
                //             saveData(info.getYouNumber(),info.getCardCode(),save.getText().toString());
                break;
            case 7:
                name = "motto";
                tv_mod_save.setText("签名");
                et_mod_info.setText(intent.getStringExtra("motto"));
                //            saveData(info.getYouNumber(),info.getMotto() ,save.getText().toString());
                break;
        }
    }

    public void findId(){
        tv_mod_back = ((ImageView) findViewById(R.id.iv_mod_back));
        tv_mod_save = ((TextView) findViewById(R.id.tv_mod_save));
        et_mod_info = ((EditText) findViewById(R.id.et_mod_info));
        save = ((Button) findViewById(R.id.bt_qm_save));
    }
    public void saveData(String youNumber,String name,String value){

        System.out.println("youNumber="+youNumber+"name="+name+"value="+value);
        HttpUtils conn = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/ModUserInfo?youNumber="+youNumber+"&name="+name+"&value="+value;
        conn.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("--------------------success-----------------------");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
                System.out.println("网络连接失败++++++++++++++++++++++++");
            }
        });
    }
}
