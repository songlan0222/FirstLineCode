package com.songlan.activityproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        button3.setOnClickListener {
            println("【click on Button 3!!!!!!!!】")
            ActivityCollector.finishAll()
        }
    }
}