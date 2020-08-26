package com.songlan.dataapplication

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.songlan.dataapplication.Adapters.BookAdapter
import com.songlan.dataapplication.Models.Book
import com.songlan.dataapplication.Utils.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val content = load()
        editTextContent.setText(content)
        editTextContent.setSelection(content.length)

        // 点击事件
        saveXMLBtn.setOnClickListener(this)
        loadXMLBtn.setOnClickListener(this)
        createBookBtn.setOnClickListener(this)
        createCategoryBtn.setOnClickListener(this)
        addBtn.setOnClickListener(this)
        deleteBtn.setOnClickListener(this)
        updateBtn.setOnClickListener(this)
        queryBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        val content = editTextContent.text.toString()
        save(content)
    }

    private fun save(text: String) {
        try {
            val output = openFileOutput("data", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(text)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                it.forEachLine {
                    content.append(it).append('\n')
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

    override fun onClick(view: View) {
        val helper = MyDatabaseHelper(this, "BookStore.db", 2)
        when (view.id) {
            R.id.saveXMLBtn -> {
                val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
                editor.apply {
                    putString("name", "Tom")
                    putInt("age", 13)
                    putBoolean("isMarried", false)
                }
                editor.apply()
                Toast.makeText(this, "save xml success!", Toast.LENGTH_SHORT).show()
            }
            R.id.loadXMLBtn -> {
                val prefs = getSharedPreferences("data", MODE_PRIVATE)
                val name = prefs.getString("name", "Tomas")
                val age = prefs.getInt("age", 20)
                val isMarried = prefs.getBoolean("isMarried", true)
                val content = StringBuilder().apply {
                    append(name)
                    append('\n')
                    append(age)
                    append('\n')
                    append(isMarried)
                }
                editTextContent.setText(content.toString())
                editTextContent.setSelection(content.length)


            }
            R.id.createBookBtn -> {
                val helper = MyDatabaseHelper(this, "BookStore.db", 2)
                helper.writableDatabase
            }
            R.id.createCategoryBtn -> {
                val helper = MyDatabaseHelper(this, "BookStore.db", 2)
                helper.writableDatabase
            }
            R.id.addBtn -> {
                val db = helper.writableDatabase
                val contentValue = ContentValues().apply {
                    put("name", "老娘与海")
                    put("author", "范思哲")
                    put("page", "300")
                    put("price", "45.98")
                }
                db.insert("book", null, contentValue)
                val contentValue2 = ContentValues().apply {
                    put("name", "老衲与法海")
                    put("author", "许仙")
                    put("page", "800")
                    put("price", "99.9")
                }
                db.insert("book", null, contentValue2)
            }
            R.id.deleteBtn -> {
                val db = helper.writableDatabase
                val cursor = db.query("book", null, null, null, null, null, null)
                val count = cursor.count
                val randomNum = (1..count).random()
                Toast.makeText(this, "被随机抽到的id为：${randomNum}", Toast.LENGTH_SHORT).show()
                db.delete("book", "id = ?", arrayOf("${randomNum}"))
            }
            R.id.updateBtn -> {
                val db = helper.writableDatabase
                val contentVale = ContentValues().apply {
                    put("price", 0)
                }
                val cursor = db.query("book", null, null, null, null, null, null)
                val count = cursor.count
                val randomNum = (1..count).random()
                Toast.makeText(this, "被随机抽到的id为：${randomNum}", Toast.LENGTH_SHORT).show()
                db.update("book", contentVale, "id = ?", arrayOf("${randomNum}"))
            }
            R.id.queryBtn -> {
                val db = helper.writableDatabase
                val cursor = db.query("book", null, null, null, null, null, null)

                if(cursor.moveToFirst()){
                    val bookList = ArrayList<Book>()
                    do{
                        val id = cursor.getInt(cursor.getColumnIndex("id"))
                        val name = cursor.getString(cursor.getColumnIndex("name"))
                        val author = cursor.getString(cursor.getColumnIndex("author"))
                        val page = cursor.getInt(cursor.getColumnIndex("page"))
                        val price = cursor.getDouble(cursor.getColumnIndex("price"))
                        val book = Book(id, name, author, page, price)
                        bookList.add(book)
                    }while(cursor.moveToNext())
                    cursor.close()

                    // 配置RecyclerView
                    val layoutManager = LinearLayoutManager(this)
                    booksRV.layoutManager = layoutManager
                    val adapter = BookAdapter(bookList)
                    booksRV.adapter = adapter
                }


            }
        }
    }
}