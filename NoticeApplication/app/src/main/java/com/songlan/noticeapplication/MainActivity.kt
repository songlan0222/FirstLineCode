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
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            val normalChannel = NotificationChannel("normal", "普通通知", NotificationManager.IMPORTANCE_DEFAULT)
            val importantChannel = NotificationChannel("important", "重要通知", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(normalChannel)
            manager.createNotificationChannel(importantChannel)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.noticeBtn -> {
                val intent = Intent(this, NotificationActivity::class.java)
                intent.putExtra("text", "随便发送点内容，象征性的展示一下")
                val pi = PendingIntent.getActivity(this, 0, intent, 0)
                val notification = NotificationCompat.Builder(this, "normal").apply {
                    setContentTitle("标准通知")
                    setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                    setAutoCancel(true)
                    setContentIntent(pi)
                    setSmallIcon(R.drawable.small_icon)
                    setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                }.build()
                manager.notify(1, notification)
            }
        }
    }
}