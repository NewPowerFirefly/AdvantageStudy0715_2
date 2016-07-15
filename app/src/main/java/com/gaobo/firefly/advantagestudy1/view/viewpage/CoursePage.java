package com.gaobo.firefly.advantagestudy1.view.viewpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommPage;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by gy on 2016/7/11.
 */
public class CoursePage extends CommPage {

    private ListView lv_notice;

    public CoursePage(Context context) {
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

  //              System.out.println(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

        lv_notice.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
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
                View view = View.inflate(getContext(),R.layout.single_notice_lv,null);
                return view;
            }
        });
    }
}
