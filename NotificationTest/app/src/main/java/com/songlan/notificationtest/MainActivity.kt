package com.songlan.notificationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.songlan.notificationtest.R.id.noticeBtn
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.noticeBtn -> {

            }
        }
    }
}