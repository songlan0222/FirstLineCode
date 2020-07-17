package com.songlan.activityproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // 获取传递的值
        val extraMsg = intent.getStringExtra("first2Second")
        text_view.text = extraMsg

        // 配置button2
        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "the return value!")
            setResult(Activity.RESULT_OK, intent)
            finish()
            // 返回到上个Activity的onActivityResult方法中
        }
    }

    override fun onBackPressed() {
        // 重写向上返回方法，达到返回时传递参数的目的
    }
}