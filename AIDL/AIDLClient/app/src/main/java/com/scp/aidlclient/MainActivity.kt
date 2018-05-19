package com.scp.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.scp.aidlservice.IStudentService
import com.scp.aidlservice.Person
import com.scp.aidlservice.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var conn: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //初始化View
        initView()
    }

    /**
     * 初始化View
     */
    private fun initView() {
        main_bind_remote_service_tv.setOnClickListener { bindRemoteService() }
        main_un_bind_remote_service_tv.setOnClickListener { unBindRemoteService() }
        main_call_remote_service_tv.setOnClickListener { callRemoteService() }
    }

    private var studentService: IStudentService? = null

    /**
     * 绑定远程服务
     */
    private fun bindRemoteService() {
        val intent = Intent("com.scp.aidlservice.RemoteService.action")
        intent.`package` = "com.scp.aidlservice"
        if (conn == null) {
            conn = object : ServiceConnection {
                override fun onServiceDisconnected(name: ComponentName?) {
                }

                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    studentService = IStudentService.Stub.asInterface(service)
                }
            }
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
            Toast.makeText(this, "绑定远程服务!!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "已经绑定远程服务!!!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 解绑远程服务
     */
    private fun unBindRemoteService() {
        if (conn != null) {
            unbindService(conn)
            conn = null
        }
    }

    /**
     * 调用远程服务
     */
    private fun callRemoteService() {
        //获取内容
        val content = main_message_et.text.toString()
        //当内容为空时
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "内容不能为空!!!", Toast.LENGTH_SHORT).show()
            return
        }
        if (studentService != null) {
            val student: Student = studentService!!.student
            val person: Person = studentService!!.person
            val xy = content.split(" ")
            val result = studentService!!.add(xy[0].toInt(), xy[1].toInt())
            main_result_tv.text = "${main_result_tv.text}\n$result--${student.id}:${student.name}:${student.price}--${person.left}"
            Toast.makeText(this, "调用远程服务!!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "远程服务未启动!!!", Toast.LENGTH_SHORT).show()
        }
    }
}
