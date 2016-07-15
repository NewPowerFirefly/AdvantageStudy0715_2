package com.gaobo.firefly.advantagestudy1.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaobo.firefly.advantagestudy1.MainActivity;
import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.pojo.User;
import com.gaobo.firefly.advantagestudy1.utils.StreamUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity {
    private String LOGIN = "Login";
    private EditText userName;
    private EditText userPsw;
    private TextView findPsw;
    private CheckBox remPsw;
    private TextView skip;
    private TextView register;
    private Button login;
    private static String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = ((EditText) findViewById(R.id.et_userName));
        userPsw = ((EditText) findViewById(R.id.et_userPsw));

        login = ((Button) findViewById(R.id.bt_login));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String psw = userPsw.getText().toString();
                checkLogin(name, psw);
            }
        });

        findPsw = ((TextView) findViewById(R.id.tv_findPsw));
        findPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, FindPsw.class);
                startActivity(intent);
            }
        });
        remPsw = ((CheckBox) findViewById(R.id.cb_remember));

        skip = ((TextView) findViewById(R.id.tv_skip));
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        register = ((TextView) findViewById(R.id.tv_register));
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

    }

    public String checkLogin(String name, String psw) {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(Login.this, "用户名不得为空", Toast.LENGTH_LONG).show();
            System.out.println(name + psw + "+++++++++++++++++++++++3");
            return null;
        } else if (TextUtils.isEmpty(psw)) {
            Toast.makeText(Login.this, "密码错误", Toast.LENGTH_LONG).show();
            System.out.println(name + psw + "+++++++++++++++++++++++3");
            return null;
        } else {
            //"http://10.40.8.23:8080/Android1/CheckLog?name=Firefly&psw=123123"
            path = "http://192.168.23.1:8080/Android1/CheckLog?name=" + name + "&psw=" + psw;
            HttpUtils conn = new HttpUtils();
            conn.send(HttpRequest.HttpMethod.GET,path , new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    Gson gson = new Gson();
                    User user = gson.fromJson(responseInfo.result, User.class);
                    System.out.println(user.getUserName()+"==================================");
                    if(TextUtils.isEmpty(user.getUserName())){
                        Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                        return;
                    }else{
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                @Override
                public void onFailure(HttpException e, String s) {
                    System.out.println("连接失败+++++++++++++++++++++++++");
                }
            });
        }
        return null;
    }

    public void userRegister(){
        Intent intent = new Intent(Login.this,UserRegister.class);
        startActivity(intent);
    }

}
