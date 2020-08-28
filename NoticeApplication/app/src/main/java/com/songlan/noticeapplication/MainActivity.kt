package com.songlan.noticeapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import com.songlan.noticeapplication.Activity.NotificationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var manager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNoticeChannel()

        noticeBtn.setOnClickListener(this)
    }

    private fun createNoticeChannel(){
        if(!::manager.isInitialized){
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val normalChannel = NotificationChannel("normal", "普通通知", NotificationManager.IMPORTANCE_DEFAULT)
            val importantChannel = NotificationChannel("important", "重要通知", NotificationManager.IMPORTANCE_HIGH)
            manager.apply {
                createNotificationChannel(normalChannel)
                createNotificationChannel(importantChannel)
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.noticeBtn -> {
                val intent = Intent(this, NotificationActivity::class.java)
                intent.putExtra("text", "阿巴阿巴阿巴阿巴阿巴把阿巴阿巴把阿坝的金发射流风机埃里克森的房间埃里克森的解放路卡机的理发卡建设路口的房间爱斯达克了房间埃里克森的聚隆科技SDK了几分")
                val pi = PendingIntent.getActivity(this, 0, intent, 0)
                val notification = NotificationCompat.Builder(this, "important").apply {
                    setContentTitle("消息通知")
                    setContentText("消息主体，随便写点什么")
                    setSmallIcon(R.drawable.small_icon)
                    setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                    setContentIntent(pi)
                    setAutoCancel(true)
                }.build()
                manager.notify(1, notification)

            }
        }
    }
}