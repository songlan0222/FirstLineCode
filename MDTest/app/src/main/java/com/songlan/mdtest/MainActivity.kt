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

    private val fruit = mutableListOf<Fruit>(
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

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigationView.setCheckedItem(R.id.navFriends)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        mainFloatingActionBtn.setOnClickListener {
            Snackbar.make(it, "Oh my God!", Snackbar.LENGTH_SHORT)
                .setAction("Ah?"){
                    Toast.makeText(this, "A pig fly in sky!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = FruitAdapter(this, fruitList)
        mainRecyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            thread {
                Thread.sleep(2000)
                runOnUiThread {
                    initFruits()
                    adapter.notifyDataSetChanged()
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50){
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
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }
}