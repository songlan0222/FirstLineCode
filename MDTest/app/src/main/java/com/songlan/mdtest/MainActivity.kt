package com.songlan.mdtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val fruit = mutableListOf<Fruit>(
        Fruit("apple", R.drawable.apple),
        Fruit("banana", R.drawable.banana),
        Fruit("orange", R.drawable.orange),
        Fruit("watermelon", R.drawable.watermelon),
        Fruit("grape", R.drawable.grape),
        Fruit("pineapple", R.drawable.pineapple),
        Fruit("strawberry", R.drawable.strawberry),
        Fruit("cherry", R.drawable.cherry),
        Fruit("mango", R.drawable.mango),
        Fruit("pear", R.drawable.pear),
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 绑定toolbar作为工具栏
        setSupportActionBar(mainToolbar)

        // toolbar的home按钮激活
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        // Snackbar的使用
        mainFloatingActionBtn.setOnClickListener {
            Snackbar.make(it, "Floating Bar", Snackbar.LENGTH_SHORT)
                .setAction("Retry") {
                    Toast.makeText(this, "you click [retry]", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        // navigation的弹出
        navigationView.setCheckedItem(R.id.navFriends)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        // 数据展示
        initFruit()
        val manager = GridLayoutManager(this, 2)
        val adapter = FruitAdapter(this, fruitList)
        mainRecyclerView.let {
            it.layoutManager = manager
            it.adapter = adapter
        }

        // 下拉刷新
        mainSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        mainSwipeRefreshLayout.setOnRefreshListener {
            refreshFruit(adapter)
        }
    }

    private fun refreshFruit(adapter: FruitAdapter) {
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                initFruit()
                adapter.notifyDataSetChanged()
                mainSwipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initFruit() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruit.size).random()
            fruitList.add(fruit[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> {
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }
}