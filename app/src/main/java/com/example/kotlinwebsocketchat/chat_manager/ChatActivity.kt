package com.example.kotlinwebsocketchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatActivity : AppCompatActivity() {
    lateinit var txtMessage: TextView
    lateinit var buttonSend: Button
    private var adapterRecyclerViewMessage: AdapterRecyclerViewMessage? = null
    private var messages: ArrayList<Message> = ArrayList()
    private var client: OkHttpClient = OkHttpClient()
    private var ws: WebSocket? = null
    private var recycleView_Messages: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main2)
        txtMessage = findViewById(R.id.text_message)
        buttonSend = findViewById(R.id.button_send)
        recycleView_Messages = findViewById(R.id.recycleView_Messages)

        buttonSend.setOnClickListener {
            ws?.apply {
                var text = txtMessage.text.toString()
                send("você disse: $text")
                txtMessage.setText("")
            }
        }
        initRecyclerView()
    }

    fun initRecyclerView() {
        adapterRecyclerViewMessage = AdapterRecyclerViewMessage(this, messages)
        recycleView_Messages?.layoutManager = LinearLayoutManager(this)
        recycleView_Messages?.adapter = adapterRecyclerViewMessage

    }

    override fun onResume() {
        super.onResume()
        start()
    }
    private fun start() {
        val user = intent.getStringExtra("user")
        val websocket_endpoint = ""
        val request: Request = Request
            .Builder()
            .url("$websocket_endpoint$user")
            .build()
        val listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                runOnUiThread {
                    adapterRecyclerViewMessage?.addMessage(Message("asdasdas", text, text.contains("você")))
                    // scroll the RecyclerView to the last added element
                    recycleView_Messages?.scrollToPosition(adapterRecyclerViewMessage?.itemCount!!-1)
                }
            }

        }
        ws = client.newWebSocket(request, listener)
    }

}