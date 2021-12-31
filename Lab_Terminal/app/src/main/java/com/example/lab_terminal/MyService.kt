package com.example.lab_terminal

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("Msg", "I am started")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Msg", "I am destroyed")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}