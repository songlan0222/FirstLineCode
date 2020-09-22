package com.songlan.mdtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        floatingActionButton.setOnClickListener {
            Snackbar.make(it, "You submit your homework", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(
                        this,
                        "You hit on your teacher's head, then leave school.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }

        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        initFruit()
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruitList(adapter)
        }
    }

    private fun refreshFruitList(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread{
                initFruit()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
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
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> {
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "setttings", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }
}