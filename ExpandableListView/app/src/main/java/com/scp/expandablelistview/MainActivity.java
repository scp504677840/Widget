package com.scp.expandablelistview;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {

    private ExpandableListView elv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //第一步：获取ExpandableListView
        elv = (ExpandableListView) findViewById(R.id.main_elv);
        //第二步：准备数据源
        List<String> parentList = new ArrayList<String>();
        parentList.add("北京");
        parentList.add("上海");
        parentList.add("湖北");

        Map<String, List<String>> childrenMap = new HashMap<String, List<String>>();

        List<String> oneList = new ArrayList<String>();
        oneList.add("朝阳区");
        oneList.add("海淀区");
        oneList.add("通州区");
        childrenMap.put("北京", oneList);

        List<String> twoList = new ArrayList<String>();
        twoList.add("徐汇区");
        twoList.add("闵行区");
        twoList.add("松江区");
        childrenMap.put("上海", twoList);

        List<String> threeList = new ArrayList<String>();
        threeList.add("武汉市");
        threeList.add("孝感市");
        threeList.add("安陆市");
        childrenMap.put("湖北", threeList);

        //第三步：准备适配器
        MyAdapter adapter = new MyAdapter(MainActivity.this, parentList, childrenMap,elv);
        //第四步：设置适配器
        elv.setAdapter(adapter);

        //设置子item之间的分隔物
        //elv.setChildDivider(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        //设置子item前面的指示器
        //elv.setChildIndicator(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));
        //设置子item前面的指示器的范围
        elv.setChildIndicatorBounds(0, 30);
        //
        elv.setGroupIndicator(null);

        elv.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.e("MainActivity","groupPosition:" + groupPosition + "childPosition:" + childPosition + "id:" + id);
        return true;
    }
}
