package com.scp.topbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setTitleSize(20);
        topBar.setTitleVisible(false);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "标题为：" + topBar.getLeftTitle().toString().trim(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "标题为：" + topBar.getRightTitle().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(MainActivity.this, "标题为：" + topBar.getTitle().toString().trim(), Toast.LENGTH_SHORT).show();
    }
}
