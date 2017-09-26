package com.bawei.lcy.liuchunyu20170922;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by LCY on 2017/9/22.
 * 用途：解析json串，对PullToReFreshListView进行上拉刷新，下拉加载的操作
 */

public class ChannelFragment extends Fragment {

    private String name;
    private String news_url;
    private PullToRefreshListView pullToRefreshListView;
    private  List<DataBean.ResultsBean> results;
    private  MyAdapter adapter;
    private DataBean dataBean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接受传过来的值
        Bundle bundle = getArguments();
        name = (String) bundle.get("name");
        news_url = (String) bundle.get("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment01,null);
        //获取资源ID
        pullToRefreshListView=(PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
        pullToRefreshData();
        //刷新
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                pullToRefreshData();
            }
        });
        //加载
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                LoadMoreData();
            }
        });
        return view;
    }
    //刷新
    public void pullToRefreshData(){
            new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... strings) {
                //解析
                String str=new NetWorksUtils().getJsonUrlJiexi(news_url+"1");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                dataBean = gson.fromJson(s, DataBean.class);
                results = dataBean.getResults();
                //关联适配器
                adapter=new MyAdapter(results,getActivity());
                pullToRefreshListView.setAdapter(adapter);
                //停止刷新
                pullToRefreshListView.onRefreshComplete();
            }
        }.execute(news_url);
    }
    //加载
    public void LoadMoreData(){

        new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... strings) {
                //解析
                String str=new NetWorksUtils().getJsonUrlJiexi(news_url+"1");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                dataBean = gson.fromJson(s, DataBean.class);
                //新集合
                List<DataBean.ResultsBean> results2 = dataBean.getResults();
                //上拉添加一遍
                results.addAll(results2);
                //刷新适配器
                adapter.notifyDataSetChanged();
            }
        }.execute(news_url);
    }
}
