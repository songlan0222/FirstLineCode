package com.songlan.dataapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = load()
        textView.setText(text)
        textView.setSelection(text.length)

        saveBtn.setOnClickListener(this)
        loadBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        val text = textView.text.toString()
        save(text)
    }

    private fun save(text: String) {
        try {
            val output = openFileOutput("songlan_data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(text)
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("songlan_data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it).append('\n')
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return content.toString()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.saveBtn -> {
                val editor = getSharedPreferences("songlan_data", Context.MODE_PRIVATE).edit()
                editor.putString("name", "Tom")
                editor.putInt("age", 28)
                editor.putBoolean("married", false)
                editor.apply()
            }
            R.id.loadBtn -> {
                val prefs = getSharedPreferences("songlan_data", Context.MODE_PRIVATE)
                val name = prefs.getString("name", "")
                val age = prefs.getInt("age", 0)
                val isMarried = prefs.getBoolean("married", false)
                val content = StringBuilder()
                content.append("name: ${name}\n")
                       .append("age: ${age}\n")
                    .append("isMarried: ${isMarried}")
                textView.setText(content.toString())
            }
        }
    }
}