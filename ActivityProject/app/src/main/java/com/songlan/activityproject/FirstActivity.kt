package com.songlan.activityproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        // val button1 = findViewById<Button>(R.id.button1)
        // Log.d("First Activity", this.toString())
        button1.setOnClickListener{
            // Toast.makeText(this, "你再点！", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, ThirdActivity::class.java)
            // startActivity(intent)

            //startSecondActivity()
            SecondActivity.actionStart(this, "data1", "data2")
        }
        web_zhihu_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.zhihu.com/hot")
            startActivity(intent)
        }
        call_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:15108433172")
            startActivity(intent)
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

    // 启动第二个Activity
    private fun startSecondActivity(){
        // 显示intent
        // val intent = Intent(this, SecondActivity::class.java)
        // 隐式intent,默认传递的categary为DEFAULT
        val intent = Intent("com.songlan.activityproject.START_ACTION")
        // 额外添加category
        // intent.addCategory("sssss")
        // 添加额外数据
        intent.putExtra("first2Second", "Hello second")
        //
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if(resultCode == Activity.RESULT_OK){
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstAcitivity", "return data is [ ${returnedData} ]")
            }
        }
    }
}