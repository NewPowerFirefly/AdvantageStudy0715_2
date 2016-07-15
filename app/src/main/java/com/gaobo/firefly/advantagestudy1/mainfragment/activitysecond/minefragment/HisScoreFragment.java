package com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaobo.firefly.advantagestudy1.R;
import com.gaobo.firefly.advantagestudy1.common.CommFragment;
import com.gaobo.firefly.advantagestudy1.mainfragment.activitysecond.minefragment.his_score_activity.HistoryScoreBight;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by gy on 2016/6/29.
 */
public class HisScoreFragment extends CommFragment {
    View view;
    private PullToRefreshListView plv_hisScore;
    private ImageView hisBack;
    private String[] courses;

    @Override
    public View initview(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.activity_mine_page_lscj, null);
        findId();
        return view;
    }

    @Override
    public void initdata() {

        courses = new String[]{"语文", "数学", "英语", "物理", "化学", "生物", "历史", "地理", "美术", "政治", "劳动", "高数", "科技", "音乐", "数电", "模电", "单片机"};

        plv_hisScore.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return courses.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(getActivity(), R.layout.lv_his_score_bight, null);
                TextView tv = ((TextView) view.findViewById(R.id.tv_single_course));
                ImageView iv_single_flush_flag = ((ImageView) view.findViewById(R.id.iv_single_flush_flag));

                tv.setText(courses[position].toString());
                iv_single_flush_flag.setVisibility(position % 2 == 0 ? View.INVISIBLE : View.VISIBLE);
                return view;
            }
        });

        plv_hisScore.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        plv_hisScore.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String time = DateUtils.formatDateTime(getContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                plv_hisScore.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                plv_hisScore.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                plv_hisScore.getLoadingLayoutProxy().setReleaseLabel("释放刷新");
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后跟新时间" + time);
  //              new MyTask().exwcute();
                System.out.println("此处获取网络数据···");
                plv_hisScore.onRefreshComplete();

            }
        });

        plv_hisScore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:"+position+"========   id:"+id);
                Intent intent = new Intent(getContext(), HistoryScoreBight.class);
                intent.putExtra("id",(int)id);
                intent.putExtra("position",courses[position-1]);
                startActivity(intent);
            }
        });

        hisBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public void findId() {
//        lv_hisScore = ((ListView) view.findViewById(R.id.lv_hisScore));
        plv_hisScore = ((PullToRefreshListView) view.findViewById(R.id.plv_hisScore));
        hisBack = ((ImageView) view.findViewById(R.id.iv_hisScore_back));
    }

  /*  private class MyTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);//睡眠2秒，延迟加载数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> mArrayList = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
                counter++;
                mArrayList.add("-----" + String.valueOf(counter) + "-------");
            }
            return mArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            for (String string : result) {
                adapter.add(string);
            }
            pListView.onRefreshComplete();//数据加载到适配器完成后，刷新完成，
            super.onPostExecute(result);
        }
    }*/

}
