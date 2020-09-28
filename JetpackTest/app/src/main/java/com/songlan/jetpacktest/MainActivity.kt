package com.songlan.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Secure.putInt
import android.util.Log
import android.view.View
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.songlan.jetpacktest.Skill.MyLogger
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var sp: SharedPreferences
    private lateinit var userDao: UserDao

    private val uIds = ArrayList<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusOneBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)
        getUserBtn.setOnClickListener(this)

        insertUser.setOnClickListener(this)
        deleteUser.setOnClickListener(this)
        updateUser.setOnClickListener(this)
        queryAllUser.setOnClickListener(this)
        deleteAllUser.setOnClickListener(this)

        sp = getPreferences(Context.MODE_PRIVATE)
        val reservedCounter = sp.getInt(MainViewModel.RESERVED_COUNTER, 0)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(reservedCounter))
            .get(MainViewModel::class.java)

        viewModel.counter.observe(this, Observer {
            counterTextView.text = viewModel.counter.value.toString()
        })

        viewModel.user.observe(this, Observer {
            counterTextView.text = "用户Id:   " + viewModel.user.value?.firstName
        })

        thread {
            userDao = AppDatabase.getDatabase().userDao()
            val allUser = userDao.getAllUser()
            for (user in allUser){
                uIds.add(user.id)
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.plusOneBtn -> {
                viewModel.plusOne()
            }
            R.id.clearBtn -> {
                viewModel.clear()
            }
            R.id.getUserBtn -> {
                val uId = (0..10000).random()
                viewModel.changeUser(uId)
            }
            R.id.insertUser -> {
                thread {
                    val index = (0..10000).random()
                    val user = User(index.toString(), index.toString(), 10)
                    user.id = userDao.addUser(user)
                }
            }
            R.id.deleteUser -> {
                thread {
                    val index = (0 until uIds.size).random()
                    val uId = uIds.removeAt(index)
                    val delUser = userDao.getUserById(uId)
                    userDao.deleteUser(delUser)
                }
            }
            R.id.updateUser -> {
                thread {
                    val index = (0 until uIds.size).random()
                    val uId = uIds[index]
                    val user = userDao.getUserById(uId)
                    user.age = 66
                    userDao.updateUser(user)
                }
            }
            R.id.queryAllUser -> {
                thread{
                    val allUser = userDao.getAllUser()
                    for(user in allUser){
                        MyLogger.d("MyLogger", user.toString()+" [id]=${user.id}")
                    }
                }
            }
            R.id.deleteAllUser -> {
                thread{
                    userDao.deleteAllUser()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(MainViewModel.RESERVED_COUNTER, viewModel.counter.value ?: 0)
        }
    }
}