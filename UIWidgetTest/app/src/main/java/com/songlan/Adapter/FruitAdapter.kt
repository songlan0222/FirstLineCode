package com.songlan.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.songlan.Model.Fruit
import com.songlan.uiwidgettest.ListViewActivity
import com.songlan.uiwidgettest.R
import kotlinx.android.synthetic.main.fruit_item.view.*
import org.w3c.dom.Text

class FruitAdapter(val activity: Activity, val resourceId: Int, val data: List<Fruit>):ArrayAdapter<Fruit>(activity, resourceId, data) {

    inner class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        // val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        // val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        // val fruitName: TextView = view.findViewById(R.id.fruitName)
        val fruit = getItem(position)
        if(fruit != null){
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }
        return view
    }
}