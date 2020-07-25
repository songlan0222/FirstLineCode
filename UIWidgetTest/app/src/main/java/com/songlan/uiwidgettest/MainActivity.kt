package com.songlan.uiwidgettest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(this)
        changeImgBtn.setOnClickListener (this)
        progressBtn.setOnClickListener(this)

        // 隐藏系统标题栏
        supportActionBar?.hide()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                val inputText = editText.text.toString()
                val msg = if(inputText.isEmpty())"点锤子你点" else inputText
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
            R.id.changeImgBtn -> {
                imageView.setImageResource(R.drawable.image_desktop)
            }
            R.id.progressBtn -> {
                if (horizontalProgressBar.progress < 100){
                    horizontalProgressBar.progress = horizontalProgressBar.progress+10
                    if(progressBar.visibility == View.VISIBLE) progressBar.visibility = View.GONE
                    else progressBar.visibility = View.VISIBLE
                } else{
                    AlertDialog.Builder(this).apply {
                        setTitle("重要提示")
                        setMessage("进度条已满，继续点击JJ将会缩短")
                        setCancelable(false)
                        setPositiveButton("确认继续点击"){dialog, which->
                            {
                                Toast.makeText(this@MainActivity, "jj真的短了10cm吧？", Toast.LENGTH_SHORT).show()
                            }
                        }
                        setNegativeButton("算了算了"){dialog, which->
                            {
                                Toast.makeText(this@MainActivity, "幸好怂了，不然真的会短！", Toast.LENGTH_SHORT).show()
                            }

                        }
                        show()
                    }
                    progressBar.visibility = View.GONE
                }



            }
        }
    }


}