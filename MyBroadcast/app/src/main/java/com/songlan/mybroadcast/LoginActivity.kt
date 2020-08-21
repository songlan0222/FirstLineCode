package com.songlan.mybroadcast

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    companion object{
        fun startAction(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if(isRemember){
            val usernameText = prefs.getString("account", "")
            val passwordText = prefs.getString("password", "")
            userNameEditText.setText(usernameText)
            passwordEditText.setText(passwordText)
            isRememberCheck.isChecked = true
        }

        loginBtn.setOnClickListener {
            val username = userNameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "123456") {
                val editor = prefs.edit()
                if(isRememberCheck.isChecked){
                    editor.apply{
                        putBoolean("remember_password", true)
                        putString("account", username)
                        putString("password", password)
                    }
                } else{
                    editor.clear()
                }
                editor.apply()

                MainActivity.startAction(this)
                finish()
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
            }
        }


    }
}