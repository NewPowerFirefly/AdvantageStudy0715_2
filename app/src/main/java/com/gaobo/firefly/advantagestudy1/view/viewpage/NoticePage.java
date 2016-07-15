package com.gaobo.firefly.advantagestudy1.view.viewpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment.NoticeActivity;
import com.gaobo.firefly.advantagestudy1.pojo.Notice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by gy on 2016/7/11.
 */
public class NoticePage extends CommPage {

    private ListView lv_notice;

    public NoticePage(Context context) {
        super(context);
    }

    @Override
    public View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.lv_notice,null);
        lv_notice = ((ListView) view.findViewById(R.id.lv_notice));
        return view;
    }

    @Override
    public void initdata() {
        //获取通告信息
        String path = "http://192.168.23.1:8080/Android1/GetNotice";
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("state","all");
        httpUtils.send(HttpRequest.HttpMethod.POST, path,params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("==onSuccess==");
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Notice>>() {
                }.getType();

                final ArrayList<Notice> notices = gson.fromJson(responseInfo.result,listType);

                for (Notice notice : notices) {
                    System.out.println(notice);
                }
                setLv_notice(notices);

                lv_notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("position:"+position+"id:"+id);
                        Intent intent = new Intent(getContext(), NoticeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("notice",notices.get(position));
                        intent.putExtras(bundle);
                        intent.putExtra("position",position);
                        getContext().startActivity(intent);
                    }
                });


            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("onFailure");
            }
        });



    }

    public void setLv_notice(final ArrayList<Notice> no){
        lv_notice.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return no.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(getContext(),R.layout.single_notice_lv,null);
                TextView teaName = ((TextView) view.findViewById(R.id.tv_notice_tea_name));
                TextView notTitle = (TextView)view.findViewById(R.id.tv_notice_title);
                TextView notContent = (TextView)view.findViewById(R.id.tv_notice_content);
                TextView notDate = (TextView)view.findViewById(R.id.tv_notice_date);
                teaName.setText(no.get(position).getNotTeacher()+":");
                notTitle.setText(no.get(position).getTitle()+"");

                notContent.setSingleLine(false);
                notContent.setMaxLines(3);
                notContent.setEllipsize(android.text.TextUtils.TruncateAt.END);
                notContent.setText("      "+no.get(position).getContent());

                notDate.setText(no.get(position).getDate()+"");
                return view;
            }
        });



    }

}
