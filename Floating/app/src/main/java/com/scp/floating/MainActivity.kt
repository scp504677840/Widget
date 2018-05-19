package com.scp.floating

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 本例子参考引用：
 * https://www.jianshu.com/p/ac63c57d2555
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        main_start_tv.setOnClickListener { start() }
    }

    private fun start() {
        if (Settings.canDrawOverlays(this)) {
            val intent = Intent(this, MainService::class.java)
            startService(intent)
            finish()
        } else {
            //获取悬浮窗权限
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivity(intent)
        }
    }
}
