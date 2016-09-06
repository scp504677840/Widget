package com.scp.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTB;
    private DrawerLayout mDL;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTB = (Toolbar) findViewById(R.id.main_tb);
        //设置LOGO
        mTB.setLogo(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        //设置LOGO描述
        mTB.setLogoDescription("我是LOGO");
        //设置主标题
        mTB.setTitle("主标题");
        //设置主标题文本颜色
        mTB.setTitleTextColor(Color.RED);
        //设置子标题
        mTB.setSubtitle("子标题");
        //设置溢出图标
        //mTB.setOverflowIcon(new ColorDrawable(Color.GREEN));

        //设置ActionBar
        setSupportActionBar(mTB);
        //设置home返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.view_back));
        mTB.addView(imageView);
        /*******************setNavigationIcon****************************************/
        //设置导航图标
        mTB.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.view_back));
        //设置导航图标点击监听
        mTB.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "导航图标", Toast.LENGTH_SHORT).show();
            }
        });
        //设置菜单点击事件
        mTB.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.top_search:
                        Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.top_share:
                        Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.top_setiings:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        //DrawerLayout
        mDL = (DrawerLayout) findViewById(R.id.main_dl);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDL, mTB, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDL.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //为了让ToolBar的Menu有作用，这句代码必须写
        getMenuInflater().inflate(R.menu.top, menu);
        //通过反射显示ICON
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return true;
    }
}
