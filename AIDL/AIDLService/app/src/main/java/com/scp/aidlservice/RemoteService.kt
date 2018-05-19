package com.scp.aidlservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * 提供远程服务
 */
class RemoteService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return StudentService()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    /**
     * 学生业务类
     * 具体是什么业务类，大家可以按照自己的需求而定。
     */
    private class StudentService : IStudentService.Stub() {
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
        }

        override fun add(x: Int, y: Int): Int {
            return x + y
        }

        override fun getPerson(): Person {
            return Person()
        }

        override fun getStudent(): Student {
            return Student(1,"jay",1000.00)
        }

    }
}