package com.songlan.uiwidgettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button -> {
                //val inputText = editText.text.toString()
                //val msg = if(inputText.isEmpty())"点锤子你点" else inputText
                Toast.makeText(this, "点锤子你点", Toast.LENGTH_SHORT).show()
            }
        }
    }


}