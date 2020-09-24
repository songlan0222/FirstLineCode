package com.songlan.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(counterReserved: Int) : ViewModel() {

    private val userIdLiveData = MutableLiveData<String>()

    val user : LiveData<User> = Transformations.switchMap(userIdLiveData){
        Repository.getUser(it)
    }

    fun getUser(userId: String){
        userIdLiveData.value = userId
    }

    companion object {
        const val COUNTER_RESERVED = "counter_reserved"
    }

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = counterReserved
    }

    fun plusOne() {
        val counter = _counter.value ?: 0
        _counter.value = counter + 1
    }

    fun clear(){
        _counter.value = 0
    }

}