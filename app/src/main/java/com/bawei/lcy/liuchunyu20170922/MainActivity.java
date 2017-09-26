package com.bawei.lcy.liuchunyu20170922;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LCY on 2017/9/22.
 * 用途：用来获得资源Id,对TabLayout进行操作，以及PullToReFreshListView
 *
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout activity_main;

    //获得资源ID
    private ListView lv;
    private List<CelaBean> beanList;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    //tablayout横向拖动
    private String[] channels = {"数据新闻","快讯","头条","精美公告","美股","港股","基金","理财"};
    //网络请求json串
    private String[] Urls={
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",
            "http://gank.io/api/data/Android/10/1",

    };
    private LayoutInflater mInflater;
    private List<ChannelFragment> mViewList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"网络良好",Toast.LENGTH_SHORT).show();

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mInflater = LayoutInflater.from(this);

        activity_main = (DrawerLayout) findViewById(R.id.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        beanList = new ArrayList<>();
        beanList.add(new CelaBean(R.mipmap.ic_launcher,"客服热线"));
        beanList.add(new CelaBean(R.mipmap.ic_launcher,"营业部网点"));
        beanList.add(new CelaBean(R.mipmap.ic_launcher,"系统设置"));
        beanList.add(new CelaBean(R.mipmap.ic_launcher,"换肤"));

        CelaAdapter dapter = new CelaAdapter(beanList,MainActivity.this);
        lv.setAdapter(dapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activity_main.closeDrawer(lv);
            }
        });

        for (int i = 0; i < channels.length; i++) {
            //创建栏目的fragment
            ChannelFragment fragment = new ChannelFragment();
            Bundle b = new Bundle();
            b.putString("name", channels[i]);//传递名字
            b.putString("url", Urls[i]);
            fragment.setArguments(b);
            //收集fragment
            mViewList.add(fragment);
            //给tablayout添加tab选项卡
            mTabLayout.addTab(mTabLayout.newTab().setText(channels[i]));//添加tab选项卡

        }
        FragmentManager fm = getSupportFragmentManager();
        MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(fm,mViewList);
        mViewPager.setAdapter(adapter);//关联适配器
        mTabLayout.setupWithViewPager(mViewPager);//将Tablayout与Viewpager建立连接

    }
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<ChannelFragment> mViewList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<ChannelFragment> mViewList) {
            super(fm);
            this.mViewList = mViewList;
        }

        @Override
        public Fragment getItem(int position) {
            return mViewList.get(position);
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return channels[position];//页卡标题
        }



    }
}
