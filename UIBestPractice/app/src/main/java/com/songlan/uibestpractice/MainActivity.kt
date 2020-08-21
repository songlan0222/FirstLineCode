package com.songlan.uibestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.songlan.uibestpractice.Adapter.MsgAdapter
import com.songlan.uibestpractice.Model.Msg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()
    private lateinit var adapter: MsgAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        chatList.layoutManager = layoutManager
        if(!::adapter.isInitialized)
            adapter = MsgAdapter(msgList)
        chatList.adapter = adapter
        msgSendBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            msgSendBtn -> {
                val content = chatEditText.text.toString()
                if(content.isNotEmpty()){
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size - 1)
                    chatList.scrollToPosition(msgList.size - 1)
                    chatEditText.setText("")
                }
            }
        }
    }

    private fun initMsg(){
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        val msg2 = Msg("Hello~This is Tom.", Msg.TYPE_SENT)
        val msg3 = Msg("Are you a gay?", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        msgList.add(msg2)
        msgList.add(msg3)
    }
}