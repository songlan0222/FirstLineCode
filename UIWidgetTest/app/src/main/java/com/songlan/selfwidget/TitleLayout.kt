package com.songlan.selfwidget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.songlan.uiwidgettest.ListViewActivity
import com.songlan.uiwidgettest.R
import com.songlan.uiwidgettest.RecyclerActivity
import kotlinx.android.synthetic.main.title.view.*

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init{
        LayoutInflater.from(context).inflate(R.layout.title, this)

        backBtn.setOnClickListener {
//            val activity = context as Activity
//            activity.finish()
            val intent = Intent(context, RecyclerActivity::class.java)
            context.startActivity(intent)
        }
        listViewBtn.setOnClickListener {
            val intent = Intent(context, ListViewActivity::class.java)
            context.startActivity(intent)

        }
    }
}