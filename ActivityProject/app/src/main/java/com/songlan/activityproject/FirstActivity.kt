package com.songlan.activityproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        //val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener{
            Toast.makeText(this, "你再点！", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 最好进行一次判空
        when(item?.itemId){
            R.id.add_item -> Toast.makeText(this, "随便加", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "拿去爬", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}