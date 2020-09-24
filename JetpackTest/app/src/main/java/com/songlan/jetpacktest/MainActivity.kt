package com.songlan.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Secure.putInt
import android.view.View
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusOneBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)

        sp = getPreferences(Context.MODE_PRIVATE)
        val counterReserved = sp.getInt(MainViewModel.COUNTER_RESERVED, 0)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(counterReserved))
            .get(MainViewModel::class.java)

        viewModel.counter.observe(this, Observer {
            counterTextView.text = viewModel.counter.value.toString()
        })

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.plusOneBtn -> {
                viewModel.plusOne()
            }
            R.id.clearBtn -> {
                viewModel.clear()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(MainViewModel.COUNTER_RESERVED, viewModel.counter.value ?: 0)
        }
    }
}