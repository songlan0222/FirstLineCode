package com.songlan.noticeapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.songlan.noticeapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val text = intent.getStringExtra("text").toString()
        noticeTextView.setText(text)
    }
}