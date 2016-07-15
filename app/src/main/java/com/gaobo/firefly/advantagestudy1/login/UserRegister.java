package com.gaobo.firefly.advantagestudy1.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gaobo.firefly.advantagestudy1.R;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

public class UserRegister extends AppCompatActivity {


    String Key = "14c077198e0a8";
    String Secret = "135c44ece7becfbff4c8a6870143f421";
    private Button btn_getcode;
    private EditText phoneNumber;
    private EditText regCode;
    private EditText psw;
    private Button submit;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        phoneNumber = ((EditText) findViewById(R.id.et_register_phone));
        regCode = ((EditText) findViewById(R.id.et_register_code));
        psw = ((EditText) findViewById(R.id.et_register_psw));
        btn_getcode = ((Button) findViewById(R.id.bt_register_getcode));
        submit = ((Button) findViewById(R.id.bt_register_submit));
        psw = ((EditText) findViewById(R.id.et_register_psw));


        SMSSDK.initSDK(getApplicationContext(),"14c0aa50f4f49","e17a6cee81ba2f279421cc14c581bc9e");


        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);

        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SMSSDK.getVerificationCode("1", "4432487596");

                System.out.println("获取电话，发送验证码");
                SMSSDK.getVerificationCode("86", phoneNumber.getText().toString());

                MyCountTime myCountTime = null;
                if (myCountTime == null) {
                    myCountTime = new MyCountTime(60 * 1000, 1000);
                    myCountTime.start();
                }


                phone = phoneNumber.getText().toString();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" submit.setOnClickListener====开始校验=====1");
                SMSSDK.submitVerificationCode("86",phone,regCode.getText().toString());
                System.out.println(" submit.setOnClickListener=====校验完成====2");
            }
        });




    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            System.out.println("result="+result);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result"+event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_LONG).show();
                    System.out.println("EVENT_SUBMIT_VERIFICATION_CODE======提交验证码成功");

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //已经验证
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                    System.out.println("EVENT_GET_VERIFICATION_CODE======验证码已经发送");

                } else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //已经验证
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                    //                   textV.setText(data.toString());
                    System.out.println("EVENT_GET_SUPPORTED_COUNTRIES==获取国家列表成功");


                }
            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    System.out.println("status====="+status);
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(getApplicationContext(), des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }


        }
    };

    public class MyCountTime extends CountDownTimer {

        public MyCountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_getcode.setEnabled(false);
            btn_getcode.setText(String.valueOf((millisUntilFinished / 1000)));
        }

        @Override
        public void onFinish() {
            btn_getcode.setEnabled(true);
            btn_getcode.setText("获取");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
