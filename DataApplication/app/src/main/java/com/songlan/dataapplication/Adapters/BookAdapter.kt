package com.songlan.dataapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.songlan.dataapplication.Models.Book
import com.songlan.dataapplication.R
import kotlinx.android.synthetic.main.books_rv_items.view.*

class BookAdapter(val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id : TextView = view.findViewById(R.id.bookID)
        val name : TextView = view.findViewById(R.id.bookName)
        val author : TextView = view.findViewById(R.id.bookAuthor)
        val page : TextView = view.findViewById(R.id.bookPage)
        val price : TextView = view.findViewById(R.id.bookPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_rv_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.id.setText(book.id.toString())
        holder.name.setText(book.name)
        holder.author.setText(book.author)
        holder.price.setText(book.price.toString())
        holder.page.setText(book.page.toString())
    }

    override fun getItemCount(): Int = bookList.size
}