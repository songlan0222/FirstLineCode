package com.songlan.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(private val reservedCounter: Int) : ViewModel() {

    companion object {
        const val RESERVED_COUNTER = "reserved_counter"
    }

    val user: LiveData<User>
        get() = _user
    private val userIdLiveData = MutableLiveData<Int>()
    private val _user = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it.toString())
    }

    fun changeUser(uId: Int): LiveData<User> {
        userIdLiveData.value = uId
        return _user
    }


    val counter: MutableLiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = reservedCounter
    }

    fun plusOne() {
        val value = _counter.value ?: 0
        _counter.value = value + 1
    }

    fun clear() {
        _counter.value = 0
    }


}