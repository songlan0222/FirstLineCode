package com.songlan.selfwidget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.songlan.uiwidgettest.R
import kotlinx.android.synthetic.main.title.view.*

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init{
        LayoutInflater.from(context).inflate(R.layout.title, this)

        backBtn.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        editBtn.setOnClickListener {
            Toast.makeText(context, "写了Edit你就随便点？", Toast.LENGTH_SHORT).show()
        }
    }
}