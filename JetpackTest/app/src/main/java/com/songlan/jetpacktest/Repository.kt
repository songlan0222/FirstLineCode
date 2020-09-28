package com.songlan.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(id: String): LiveData<User>{
        val user = MutableLiveData<User>()
        user.value = User(id, id, 10)
        return user
    }
}