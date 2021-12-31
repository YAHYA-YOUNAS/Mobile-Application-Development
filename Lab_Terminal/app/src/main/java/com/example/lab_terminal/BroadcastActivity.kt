package com.example.lab_terminal

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BroadcastActivity : AppCompatActivity() {
    lateinit var receiver: MyReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        receiver = MyReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }
    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}