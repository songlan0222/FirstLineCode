package com.songlan.contactstest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.songlan.contactstest.Model.User
import com.songlan.contactstest.R
import java.util.zip.Inflater

class UserAdapter(val userList: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val userName: TextView = view.findViewById(R.id.userName)
        val userTel: TextView = view.findViewById(R.id.userTel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.setText(user.useName)
        holder.userTel.setText(user.userTel)
    }

    override fun getItemCount(): Int = userList.size



}