package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.classfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.pojo.Notice;
import com.gaobo.firefly.advantagestudy1.pojo.NoticeComment;
import com.gaobo.firefly.advantagestudy1.pojo.NoticeCommentBean;
import com.gaobo.firefly.advantagestudy1.pojo.NoticeGood;
import com.gaobo.firefly.advantagestudy1.pojo.NoticeGoodBean;
import com.gaobo.firefly.advantagestudy1.pojo.PersonInfo;
import com.gaobo.firefly.advantagestudy1.utils.DateUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.Date;

public class NoticeActivity extends AppCompatActivity {

    private ListView lv_body;
    private int value;
    private Notice notice;
    private LinearLayout inputBox;
    private Button sendReply;
    private EditText replyContent;
    private int count = 0;
    private boolean zan = false;
    private int goodIndex = -1;
    private ImageView mark;
    private TextView tvReply;
    private MyBaseAdapter myBaseAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


    }


    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.iv_notice_body_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        notice = (Notice) intent.getSerializableExtra("notice");
//        System.out.println("===notice" + notice);
        value = intent.getIntExtra("position", -2) + 1;

        lv_body = ((ListView) findViewById(R.id.lv_notice_body));
        inputBox = ((LinearLayout) findViewById(R.id.ll_comment_input));
        replyContent = ((EditText) findViewById(R.id.et_body_reply));
        sendReply = ((Button) findViewById(R.id.btn_body_reply));





//        System.out.println("value-----:" + value);
        //      initHeader();
        getDataFromServlet(value);
    }


    public void getDataFromServlet(int value) {
        HttpUtils httpUtils = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/GetNoticeComment";
        RequestParams params = new RequestParams();
        params.addBodyParameter("noticeId", value + "");
        httpUtils.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("onSuccess=NoticeActivity");
                Log.e("NoticeActivity", "notice success");
//                Type type = new TypeToken<ArrayList<NoticeComment>>(){}.getType();
                Gson gson = new Gson();
//                ArrayList<NoticeComment> comments = new ArrayList<NoticeComment>();
//                comments = gson.fromJson(responseInfo.result,type);
                NoticeCommentBean noticeCommentBean = gson.fromJson(responseInfo.result, NoticeCommentBean.class);
//                System.out.println("responseInfo.result:" + responseInfo.result);

                final ArrayList<NoticeComment> comments = noticeCommentBean.getData();
                myBaseAdapter = new MyBaseAdapter(comments);

                if (comments.size() == 0) {

                } else {
//                    for (NoticeComment comment : comments) {
//                        System.out.println(comment);
//                    }
                    setListView(myBaseAdapter);
                    initHeader(comments);
                }



                sendReply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputBox.setVisibility(View.GONE);
                        InputMethodManager soft = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        soft.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        if (TextUtils.isEmpty(replyContent.getText())){
                            Toast.makeText(getApplicationContext(),"未做评论",Toast.LENGTH_SHORT).show();

                        }else{
                            String reply = replyContent.getText()+"";
                            //向网络提交评论数据，并刷新页面（再次获取数据，并加载）此处默认用户Id为1。
                            String time = DateUtil.dateToStringFull(new Date());
                            addComment(1,notice.getId(),reply,time );
                            Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_SHORT).show();

                            //获取联系人
                            PersonInfo per = new PersonInfo();
                            for (NoticeComment comment : comments) {
                                if(comment.getPerId().getId()==1){
                                    per=comment.getPerId();
                                    break;
                                }
                            }
                            comments.add(new NoticeComment(time,reply,notice,per));
                            tvReply.setText(comments.size()+"");
                            MyBaseAdapter my = new MyBaseAdapter(comments);
                            lv_body.setAdapter(my);
//                            myBaseAdapter.notifyDataSetChanged();

                        }
                    }
                });





            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e("NoticeActivity", "notice onFailure");
                System.out.println("onFailure=NoticeActivity");
            }
        });
    }

    public void initHeader(final ArrayList<NoticeComment> comments) {

        View view = View.inflate(getApplicationContext(), R.layout.notice_header_view, null);
        view.setPadding(10, 10, 10, 10);
        TextView teaName = ((TextView) view.findViewById(R.id.tv_header_tea_name));
        TextView title = ((TextView) view.findViewById(R.id.tv_header_title));
        TextView content = ((TextView) view.findViewById(R.id.tv_header_content));
        TextView date = ((TextView) view.findViewById(R.id.tv_header_date));

        final ImageView good = ((ImageView) view.findViewById(R.id.iv_header_good));
        final TextView tvGood = ((TextView) view.findViewById(R.id.tv_header_good_count));


        mark = ((ImageView) view.findViewById(R.id.iv_header_mark));
        tvReply = ((TextView) view.findViewById(R.id.tv_header_reply_count));
        tvReply.setText("" + comments.size());
        teaName.setTextColor(getResources().getColor(R.color.blue));



        //获取所有点赞数
        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("value",3+"");
        params.addBodyParameter("noticeId",notice.getId()+"");
        String path = "http://192.168.23.1:8080/Android1/NoticeGoodController";
        http.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("获取点赞数成功");
                Gson gson = new Gson();
                NoticeGoodBean goods = gson.fromJson(responseInfo.result,NoticeGoodBean.class);
                System.out.println("success:"+goods.isSuccess());
                final ArrayList<NoticeGood> noticeGoods = goods.getData();
                for (int i = 0 ; i<noticeGoods.size() ; i++) {
                    //获取点赞总数
                    if(noticeGoods.get(i).isGood()){
                        count+=1;
                    }
                    //判断当前用户是否点赞
                    System.out.println("当前用户优号："+noticeGoods.get(i).getPerId().getId()+"===11");
                    if((1==noticeGoods.get(i).getPerId().getId())&&noticeGoods.get(i).isGood()){
                        zan = true;
                        goodIndex = i;
                    }
                }
                if(zan){
                    //设置已赞背景图
                    good.setImageResource(R.drawable.good2);
                }else{
                    //设置未赞背景图
                    good.setImageResource(R.drawable.good1);
                }
                tvGood.setText("" + count);

                good.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //取消赞
                        if(good.getDrawable().getCurrent().getConstantState().equals(getResources().getDrawable(R.drawable.good2).getConstantState())){
                            good.setImageResource(R.drawable.good1);



                            delGood(noticeGoods.get(goodIndex).getGoodId());
                            count = count -1;
                            tvGood.setText("" + count);
                            zan=false;
                        }else{//添加赞
                            good.setImageResource(R.drawable.good2);
                            addNoticeGood(notice.getId(),1,true);
                            count = count +1;
                            tvGood.setText("" + count);
                            zan = true;
                        }
                    }
                });
            }
            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("获取点赞数失败");
            }
        });

        mark.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    inputBox.setVisibility(View.VISIBLE);
                }
            }
        );
        teaName.setText(notice.getNotTeacher() + ":");
        title.setText(notice.getTitle() + "");
        content.setText("      " + notice.getContent() + "");
        date.setText(notice.getDate() + "");
        lv_body.addHeaderView(view);
    }

    public void setListView(MyBaseAdapter myBaseAdapter1) {
        lv_body.setAdapter(myBaseAdapter1);
    }

    public void addComment(int personId,int noticeId,String comContent,String date) {


        HttpUtils httpUtils = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/AddNoticeComment";
        RequestParams params = new RequestParams();
        params.addBodyParameter("personId",personId+"");
        params.addBodyParameter("noticeId",noticeId+"");
        params.addBodyParameter("comContent",comContent);
//        params.addBodyParameter("comGood",no.isComGood()+"");
        params.addBodyParameter("date",date);
        httpUtils.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void modCommment(int comId,String position,String value) {
        HttpUtils httpUtils = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/ModNoticeComment";
        RequestParams params = new RequestParams();
        params.addBodyParameter("comId",goodIndex+"");
        params.addBodyParameter("position",position+"");
        params.addBodyParameter("value",value);
        httpUtils.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void delGood(int goodId){

        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("value",2+"");
        params.addBodyParameter("goodId",goodId+"");
        String path = "http://192.168.23.1:8080/Android1/NoticeGoodController";
        http.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>(){

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(getApplicationContext(),"点赞已被取消",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"取消点赞失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addNoticeGood(int noticeId,int  personId,boolean state){
        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("value",1+"");
        params.addBodyParameter("noticeId",noticeId+"");
        params.addBodyParameter("personId",personId+"");
        params.addBodyParameter("state",state+"");
        String path = "http://192.168.23.1:8080/Android1/NoticeGoodController";
        http.send(HttpRequest.HttpMethod.POST, path, params, new RequestCallBack<String>(){

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(getApplicationContext(),"点赞成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"点赞失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int getPosition(int personId,int noticeId){
        int i=0;
        HttpUtils httpUtils = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/ModNoticeComment";
        RequestParams params = new RequestParams();
        params.addBodyParameter("personId",personId+"");
        params.addBodyParameter("noticeId",noticeId+"");
//        params.addBodyParameter("value",value);
        return i;
    }

    public class MyBaseAdapter extends BaseAdapter{
        private ArrayList<NoticeComment> com;

        public MyBaseAdapter(ArrayList<NoticeComment> com) {
            this.com = com;
        }
        @Override
        public int getCount() {
            return com.size();
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
            View view = View.inflate(getApplicationContext(), R.layout.lv_notice_body, null);
            TextView replyAuthor = ((TextView) view.findViewById(R.id.tv_notice_reply_name));
            TextView replyContent = ((TextView) view.findViewById(R.id.tv_notice_reply_content));
            replyAuthor.setText(com.get(position).getPerId().getPetName() + ": ");
            replyContent.setText(" " + com.get(position).getComContent());
            return view;
        }
    }
}
