package com.scp.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    TabLayout mTabLayout;

    ViewPager mViewPager;

    //适配器
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化View
        initView();
    }

    private void initView() {

        mTabLayout = (TabLayout) findViewById(R.id.main_tl);
        mViewPager = (ViewPager) findViewById(R.id.main_vp);

        //设置TAB未选中和选中时字体的颜色(采用16进制位)
        mTabLayout.setTabTextColors(0xffff0000, 0xff00ff00);
        //设置指示器颜色
        mTabLayout.setSelectedTabIndicatorColor(0xff0000ff);
        //设置指示器的高度
        //mTabLayout.setSelectedTabIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        //设置背景颜色
        mTabLayout.setBackgroundResource(R.color.colorAccent);
        //设置TAB对齐方式
        //mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置TAB模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        TabLayout.Tab tab1 = mTabLayout.newTab().setText("选项1");
        //tab1.setCustomView(R.layout.tab);
        tab1.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab2 = mTabLayout.newTab().setText("选项2");
        tab2.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab3 = mTabLayout.newTab().setText("选项3");
        tab3.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab4 = mTabLayout.newTab().setText("选项4");
        tab4.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab5 = mTabLayout.newTab().setText("选项5");
        tab5.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab6 = mTabLayout.newTab().setText("选项6");
        tab6.setIcon(R.mipmap.ic_launcher);
        TabLayout.Tab tab7 = mTabLayout.newTab().setText("选项7");
        tab7.setIcon(R.mipmap.ic_launcher);

        mTabLayout.addTab(tab1);
        mTabLayout.addTab(tab2);
        mTabLayout.addTab(tab3);
        mTabLayout.addTab(tab4);
        mTabLayout.addTab(tab5);
        mTabLayout.addTab(tab6);
        mTabLayout.addTab(tab7);

        mTabLayout.setOnTabSelectedListener(this);

        //创建适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        //设置适配器
        mViewPager.setAdapter(adapter);

        //创建Fragment
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();

        //添加到Fragment集合中
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(oneFragment);
        fragments.add(twoFragment);

        //创建Titles
        List<String> titles = new ArrayList<String>();
        titles.add("第一个");
        titles.add("第二个");

        //适配器设置Fragment集合
        adapter.setFragments(fragments, titles);

        //关联ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e("MainActivity", "onTabSelected，位置：" + tab.getPosition() + ",文本：" + tab.getText());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.e("MainActivity", "onTabUnselected，位置：" + tab.getPosition() + ",文本：" + tab.getText());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.e("MainActivity", "onTabReselected，位置：" + tab.getPosition() + ",文本：" + tab.getText());
    }
}
