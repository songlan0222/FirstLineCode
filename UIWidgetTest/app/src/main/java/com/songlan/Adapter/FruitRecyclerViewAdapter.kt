package com.songlan.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.songlan.Model.Fruit
import com.songlan.uiwidgettest.R
import kotlinx.android.synthetic.main.fruit_item.view.*
import org.w3c.dom.Text

class FruitRecyclerViewAdapter(val fruitList: List<Fruit>):
    RecyclerView.Adapter<FruitRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName : TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name

        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, "This is ${fruit.name}'s itemView", Toast.LENGTH_SHORT).show()
        }
        holder.fruitImage.setOnClickListener {
            Toast.makeText(it.context, "This is ${fruit.name}'s fruitImage", Toast.LENGTH_SHORT).show()
        }
    }
}