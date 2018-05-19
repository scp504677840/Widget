package com.scp.floating

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast


/**
 * Created by scp on 18-4-16.
 */
class MainService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var params: WindowManager.LayoutParams
    private lateinit var toucherLayout: LinearLayout
    private var statusBarHeight: Int = 0
    private var buttonIV: ImageView? = null

    override fun onCreate() {
        super.onCreate()
        //生成悬浮窗
        createToucher()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        //用imageButton检查悬浮窗还在不在，这里可以不要。优化悬浮窗时要用到。
        if (buttonIV != null) {
            windowManager.removeView(toucherLayout)
        }
        super.onDestroy()
    }

    /**
     * 创建悬浮窗
     */
    private fun createToucher() {
        //配置WindowManager&params
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        params = WindowManager.LayoutParams()
        //设置type.系统提示型窗口，一般都在应用程序窗口之上
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        //设置效果为背景透明
        params.format = PixelFormat.RGBA_8888
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

        //设置窗口初始停靠位置
        params.gravity = Gravity.START or Gravity.TOP
        params.x = 0
        params.y = 0

        //设置悬浮窗口长宽数据
        //注意，这里的width和height均使用px而非dp
        //如果你想完全对应布局设置，需要先获取到机器的dpi
        //px与dp的换算为px = dp * (dpi / 160)
        params.width = 300
        params.height = 300

        val inflater = LayoutInflater.from(application)
        //获取浮动窗口视图所在布局
        toucherLayout = inflater.inflate(R.layout.view_floating, null) as LinearLayout
        //添加toucherlayout
        windowManager.addView(toucherLayout, params)

        Log.e("MainActivity", "toucherlayout-->left:" + toucherLayout.left)
        Log.e("MainActivity", "toucherlayout-->right:" + toucherLayout.right)
        Log.e("MainActivity", "toucherlayout-->top:" + toucherLayout.top)
        Log.e("MainActivity", "toucherlayout-->bottom:" + toucherLayout.bottom)

        //主动计算出当前View的宽高信息
        toucherLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

        //用于检测状态栏高度
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
            Log.e("MainActivity", "statusBarHeight:" + statusBarHeight)
        }

        //配置悬浮按钮
        buttonIV = toucherLayout.findViewById(R.id.view_floating_toucher_iv) as ImageView
        buttonIV!!.setOnClickListener {
            Toast.makeText(application, "我被点击了", Toast.LENGTH_SHORT).show()
            val intent = Intent(application,MainActivity::class.java)
            startActivity(intent)
        }
        buttonIV!!.setOnTouchListener { v, event ->
            //ImageButton我放在了布局中心，布局一共300dp
            params.x = event.rawX.toInt() - 150
            //这就是状态栏偏移量用的地方
            params.y = event.rawY.toInt() - 150 - statusBarHeight
            windowManager.updateViewLayout(toucherLayout, params)
            false
        }

    }
}