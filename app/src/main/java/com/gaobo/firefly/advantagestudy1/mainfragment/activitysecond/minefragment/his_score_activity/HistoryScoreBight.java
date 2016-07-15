package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.his_score_activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.pojo.Score;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoryScoreBight extends AppCompatActivity {

    private TextView barName;
    private ImageView hisScore_back;
    private LineChart mLineChart;
    private int id;
    private String name;
    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<Float> scores = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_score_bight_activity);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        System.out.println("id=" + id);
        name = intent.getStringExtra("position");
        System.out.println("position:" + name);
        barName = ((TextView) findViewById(R.id.tv_hisScore_save));
        barName.setText(name);
        mLineChart = ((LineChart) findViewById(R.id.spread_line_chart));
        darwBight("11111111",name);
//        switch (id) {
//            case 0:
//                darwBight("111111","语文");
//                break;
//            case 1:
//                darwBight("111111","数学");
//                break;
//            case 2:
//                darwBight("111111","英语");
//                break;
//            case 3:
//                darwBight("111111","物理");
//                break;
//            case 4:
//                darwBight("111111","化学");
//                break;
//            case 5:
//                darwBight("111111","生物");
//                break;
//
//
//        }
        hisScore_back = ((ImageView) findViewById(R.id.iv_hisScore_back));


        hisScore_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }





    public void darwBight(String youNumber,String subject) {
        HttpUtils conn = new HttpUtils();
        String path = "http://192.168.23.1:8080/Android1/SearchScore?youNumber="+youNumber+"&subject="+subject;
        conn.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("获取连接成功");
                Type listType = new TypeToken<ArrayList<Score>>() {
                }.getType();
                Gson gson = new Gson();
                ArrayList<Score> bean = gson.fromJson(responseInfo.result, listType);
                if(bean.size()==0){
                    Toast.makeText(getApplicationContext(),"未考试",Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i = 0; i < bean.size(); i++) {
                    days.add( bean.get(i).getDay());
                    scores.add(bean.get(i).getScore());
                    System.out.println("bean.get(i).getDay():"+bean.get(i).getDay()+"string[i]="+days.get(i));
                }
                for (Float score : scores) {
                    System.out.println(score);
                }
                LineData mLineData = getLineData(scores.size());
                showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));
            }
            @Override
            public void onFailure(HttpException e, String s) {

                System.out.println("连接失败···");
            }
        });
    }

    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

    private LineData getLineData(int count) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count ; i++) {
            xValues.add(days.get(i));
        }
//        for (int i = 0; i < count; i++) {
//            // x轴显示的数据，这里默认使用数字下标显示
//            xValues.add("" + i);
//        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {

            yValues.add(new Entry(scores.get(i), i));
        }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "成绩走势图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets


        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }
}
