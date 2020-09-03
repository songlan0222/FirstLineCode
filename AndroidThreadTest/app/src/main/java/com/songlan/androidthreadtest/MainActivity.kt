package com.songlan.androidthreadtest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val changeText = 1

    val handle = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            textView.text = "Nice to meet you."
        }
    }

    lateinit var downloadBinder: MyService.MyBinder
    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d("MyService", "onServiceConnected executed")
            downloadBinder = binder as MyService.MyBinder
            downloadBinder.startDownload()
            downloadBinder.updateProgress()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeTextBtn.setOnClickListener(this)
        startServiceBtn.setOnClickListener(this)
        stopServiceBtn.setOnClickListener(this)
        bindBtn.setOnClickListener(this)
        unbindBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            // 多线程修改UI
            R.id.changeTextBtn -> {
                thread {
                    val msg = Message()
                    msg.what = changeText
                    handle.sendMessage(msg)
                }
            }

            // 启动和停止service
            R.id.startServiceBtn -> {
                val intent = Intent(this, MyService::class.java)
                startService(intent)

            }
            R.id.stopServiceBtn -> {
                val intent = Intent(this, MyService::class.java)
                stopService(intent)
            }

            // 绑定和解绑service
            R.id.bindBtn -> {
                val intent = Intent(this, MyService::class.java)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)

            }
            R.id.unbindBtn -> {
                val intent = Intent(this, MyService::class.java)
                unbindService(connection)
            }

        }
    }
}