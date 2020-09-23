package com.songlan.mdtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {

    companion object{
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        setSupportActionBar(fruitToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fruitName = intent.getStringExtra(FRUIT_NAME)?:""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        collapsingToolbarLayout.title = fruitName
        fruitContentText.text = fruitName.repeat(500)
        Glide.with(this).load(fruitImageId).into(fruitImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}