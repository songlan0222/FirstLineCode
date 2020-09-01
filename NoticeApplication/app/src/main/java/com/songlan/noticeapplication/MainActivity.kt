package com.songlan.noticeapplication

import android.app.Notification
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.noticeBtn -> {
                val intent = Intent(this, NotificationActivity::class.java)
                intent.putExtra("text", "在新Activity中响应的内容")
                val pi = PendingIntent.getActivity(this, 0, intent, 0)
                val notification = NotificationCompat.Builder(this, "normal").apply {
                    setContentTitle("通知的标题")
                    setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(
                            BitmapFactory.decodeResource(
                                resources,
                                R.drawable.big_image
                            )
                        )
                    )
                    setSmallIcon(R.drawable.small_icon)
                    setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                    setAutoCancel(true)
                    setContentIntent(pi)
                }.build()
                manager.notify(1, notification)
            }
        }
    }

    private fun createNoticeChannel(){
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val normalChannel = NotificationChannel("normal", "普通通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(normalChannel)
        }
    }
}