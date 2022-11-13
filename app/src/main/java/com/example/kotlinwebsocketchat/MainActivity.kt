package com.example.kotlinwebsocketchat


import android.content.Intent
import android.os.Bundle
import android.widget.*

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    lateinit var buttonRegister: Button
    lateinit var textName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonRegister  = findViewById(R.id.button_register)
        textName = findViewById(R.id.text_name)
        buttonRegister.setOnClickListener{
//            val loginIntent = Intent(this, LoginActivity::class.java)
            val loginIntent = Intent(this, ChatActivity::class.java)
            loginIntent.putExtra("user", textName.text.toString());
            startActivity(loginIntent)
        }
    }
}

