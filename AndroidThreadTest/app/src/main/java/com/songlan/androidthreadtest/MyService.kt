package com.songlan.androidthreadtest

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    lateinit var binder : MyBinder

    class MyBinder:Binder(){
        fun startDownload(){
            Log.d("MyService", "Binding, startDownload() executed")
        }

        fun updateProgress(){
            Log.d("MyService", "Binding, updateProgress() executed")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate() executed")
        binder = MyBinder()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            val channel = NotificationChannel("service", "前台Service", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MyService::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "service")
            .setContentTitle("下载服务")
            .setContentText("正在下载……")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        Log.d("MyService", "onStartCommand() executed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy() executed")
    }
}
